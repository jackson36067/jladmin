package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.jackson.Repository.QuartzJobRepository;
import com.jackson.constant.TaskConstant;
import com.jackson.constant.UserConstant;
import com.jackson.dto.AddTaskDTO;
import com.jackson.dto.TaskDTO;
import com.jackson.dto.UpdateTaskDTO;
import com.jackson.entity.QuartzJob;
import com.jackson.exception.JobClassNotFoundException;
import com.jackson.exception.JobNameExistException;
import com.jackson.utils.MailManagement;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.QuartzJobService;
import com.jackson.utils.DateTimeUtils;
import com.jackson.vo.QuartzJobExportDataVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuartzJobServiceImpl implements QuartzJobService {

    @Resource
    private Scheduler scheduler;
    @Resource
    private QuartzJobRepository quartzJobRepository;
    @Resource
    private MailManagement mailManagement;

    // 添加任务
    public void addJob(AddTaskDTO addTaskDTO) throws Exception {
        String className = addTaskDTO.getClassName();
        String jobName = addTaskDTO.getJobName();
        // 判断任务名称是否已经存在
        if (quartzJobRepository.findByJobName(jobName) != null) {
            throw new JobNameExistException(TaskConstant.JOB_NAME_EXIST);
        }
        String jobGroup = addTaskDTO.getJobGroup();
        String cronExpression = addTaskDTO.getCronExpression();
        // 拼接完整的类路径
        String fullClassName = TaskConstant.BASE_PACKAGE + className;
        // 使用反射加载类
        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(fullClassName);
        // 创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, jobGroup)
                .build();
        // 配置 CronTrigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, jobGroup)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        // 调度任务
        scheduler.scheduleJob(jobDetail, trigger);
        // 如果任务状态为停用,那么先设置停用 (true: 启用 false: 暂停)
        if (addTaskDTO.getisPause()) {
            scheduler.pauseJob(new JobKey(jobName, jobGroup));
        }
        // 新增任务
        QuartzJob quartzJob = BeanUtil.copyProperties(addTaskDTO, QuartzJob.class);
        quartzJobRepository.save(quartzJob);
    }

    // 修改任务
    @Transactional
    public void updateJob(UpdateTaskDTO updateTaskDTO) throws SchedulerException {
        String jobGroup = updateTaskDTO.getJobGroup();
        String jobName = updateTaskDTO.getJobName();
        String cronExpression = updateTaskDTO.getCronExpression();
        String className = updateTaskDTO.getClassName();
        String email = updateTaskDTO.getEmail();
        String description = updateTaskDTO.getDescription();
        Boolean pause = updateTaskDTO.getisPause();
        String personInCharge = updateTaskDTO.getPersonInCharge();
        QuartzJob quartzJob = quartzJobRepository.findById(updateTaskDTO.getId()).get();
        // 如果任务名称或者任务分组改变或者执行类,那么就删除之前的任务,重新创建一个新的任务
        if (!quartzJob.getJobName().equals(jobName) || !quartzJob.getJobGroup().equals(jobGroup) || !quartzJob.getClassName().equals(className)) {
            JobKey oldJobKey = new JobKey(quartzJob.getJobName(), quartzJob.getJobGroup());
            // 判断任务是否存在
            if (!scheduler.checkExists(oldJobKey)) {
                throw new SchedulerException("Job does not exist: " + oldJobKey);
            }
            // 获取旧任务的 JobDetail
            // JobDetail oldJobDetail = scheduler.getJobDetail(oldJobKey);
            // 删除旧任务
            scheduler.deleteJob(oldJobKey);
            // 新增任务
            try {
                // 拼接完整的类路径
                String fullClassName = TaskConstant.BASE_PACKAGE + className;
                // 使用反射加载类
                Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(fullClassName);
                // 创建新的 JobDetail 和 Trigger
                JobDetail newJobDetail = JobBuilder.newJob(jobClass)
                        .withIdentity(jobName, jobGroup)
                        .build();
                CronTrigger newTrigger = TriggerBuilder.newTrigger()
                        .withIdentity(jobName, jobGroup) // 使用新任务名和任务组创建触发器
                        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                        .build();
                // 调度新任务
                scheduler.scheduleJob(newJobDetail, newTrigger);
            } catch (ClassNotFoundException e) {
                throw new JobClassNotFoundException(e.getMessage());
            }

        }
        // 当cron表达式改变时再改变cron表达式,重新调度任务
        if (!cronExpression.equals(quartzJob.getCronExpression())) {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            // 获取当前的触发器
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger != null) {
                // 使用 Cron 表达式创建 CronScheduleBuilder
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
                // 重新生成触发器
                CronTrigger newTrigger = trigger.getTriggerBuilder()
                        .withSchedule(scheduleBuilder)
                        .build();
                // 重新调度任务
                scheduler.rescheduleJob(triggerKey, newTrigger);
            } else {
                throw new SchedulerException("触发器不存在");
            }
            // 更新cron表达式
            quartzJob.setCronExpression(cronExpression);
        }
        // 判断状态是否改变 (改变那么就要判断是暂停还是恢复了)
        if (pause != quartzJob.getisPause()) {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            if (pause) {
                scheduler.pauseJob(jobKey);
            } else {
                scheduler.resumeJob(jobKey);
            }
            quartzJob.setisPause(pause);
        }
        // 更新任务数据库
        if (!jobName.equals(quartzJob.getJobName())) {
            quartzJob.setJobName(jobName);
        }
        if (!jobGroup.equals(quartzJob.getJobGroup())) {
            quartzJob.setJobGroup(jobGroup);
        }
        if (!className.equals(quartzJob.getClassName())) {
            quartzJob.setClassName(className);
        }
        if (!email.equals(quartzJob.getEmail())) {
            quartzJob.setEmail(email);
        }
        if (!description.equals(quartzJob.getDescription())) {
            quartzJob.setDescription(description);
        }

        if (!personInCharge.equals(quartzJob.getPersonInCharge())) {
            quartzJob.setPersonInCharge(personInCharge);
        }
        // 更新任务调度
        quartzJobRepository.saveAndFlush(quartzJob);
    }

    // 删除任务
    public void deleteJob(List<TaskDTO> taskDTOList) throws SchedulerException {
        taskDTOList.forEach(taskDTO -> {
            JobKey jobKey = new JobKey(taskDTO.getJobName(), taskDTO.getJobGroup());
            try {
                boolean exists = scheduler.checkExists(jobKey);
                if (exists) {
                    scheduler.deleteJob(jobKey);
                }
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        });
        // 删除自定义数据库中的数据
        List<Long> idList = taskDTOList.stream().map(TaskDTO::getId).toList();
        quartzJobRepository.deleteAllByIdInBatch(idList);
    }

    // 暂停任务
    public void pauseJob(TaskDTO taskDTO) throws SchedulerException {
        scheduler.pauseJob(new JobKey(taskDTO.getJobName(), taskDTO.getJobGroup()));
        Long id = taskDTO.getId();
        QuartzJob quartzJob = quartzJobRepository.findById(id).get();
        quartzJob.setisPause(true);
        quartzJobRepository.saveAndFlush(quartzJob);
    }

    // 恢复任务
    public void resumeJob(TaskDTO taskDTO) throws SchedulerException {
        // 确保任务存在
        JobKey jobKey = new JobKey(taskDTO.getJobName(), taskDTO.getJobGroup());
        if (!scheduler.checkExists(jobKey)) {
            throw new SchedulerException("Job does not exist: " + jobKey);
        }
        scheduler.resumeJob(jobKey);
        Long id = taskDTO.getId();
        QuartzJob quartzJob = quartzJobRepository.findById(id).get();
        quartzJob.setisPause(false);
        quartzJobRepository.saveAndFlush(quartzJob);
    }

    /**
     * 根据条件获取任务列表
     *
     * @param jobName
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public Result<PagingResult> getQuartzJobList(Integer page, Integer pageSize, String jobName, LocalDateTime beginTime, LocalDateTime endTime) {
        Specification<QuartzJob> quartzJobSpecification = (root, query, cb) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(jobName)) {
                Predicate predicate = cb.like(root.get("jobName"), "%" + jobName + "%");
                predicates.add(predicate);
            }
            if (beginTime != null & endTime != null) {
                Predicate createTime = cb.between(root.get("createTime"), beginTime, endTime);
                predicates.add(createTime);
            }
            Predicate[] predicates1 = new Predicate[predicates.size()];
            return cb.and(predicates.toArray(predicates1));
        };
        // 排序器
        Sort sort = Sort.by(Sort.Direction.DESC, UserConstant.CREATE_TIME);
        // 分页器
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
        Page<QuartzJob> quartzJobRepositoryAll = quartzJobRepository.findAll(quartzJobSpecification, pageRequest);
        List<QuartzJob> content = quartzJobRepositoryAll.getContent();
        PagingResult pagingResult = new PagingResult(quartzJobRepository.count(), content);
        return Result.success(pagingResult);
    }

    /**
     * 根据id获取任务信息
     *
     * @param id
     * @return
     */
    @Override
    public Result<QuartzJob> getQuartzJobById(Long id) {
        return Result.success(quartzJobRepository.findById(id).get());
    }

    /**
     * 导出任务数据
     *
     * @param httpServletResponse
     */
    @Override
    public void exportQuartzJobData(HttpServletResponse httpServletResponse) {
        InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream("templates/定时任务数据.xlsx");
        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("sheet1");
            List<QuartzJobExportDataVO> quartzJobExportDataVOS = quartzJobRepository.findAll()
                    .stream()
                    .map(quartzJob -> BeanUtil.copyProperties(quartzJob, QuartzJobExportDataVO.class))
                    .toList();
            int RowIndex = 1;
            for (QuartzJobExportDataVO quartzJobExportDataVO : quartzJobExportDataVOS) {
                XSSFRow row = sheet.createRow(RowIndex);
                row.createCell(0).setCellValue(quartzJobExportDataVO.getJobName());
                row.createCell(1).setCellValue(quartzJobExportDataVO.getClassName());
                row.createCell(2).setCellValue(quartzJobExportDataVO.getCronExpression());
                row.createCell(3).setCellValue(quartzJobExportDataVO.getisPause() ? "暂停中" : "执行中");
                row.createCell(4).setCellValue(quartzJobExportDataVO.getDescription());
                row.createCell(5).setCellValue(DateTimeUtils.formatLocalDateTime(quartzJobExportDataVO.getCreateTime()));
                RowIndex++;
            }

            // 设置请求头,让浏览器下载该文件
            httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String((DateTimeUtils.formatLocalDateTime(LocalDateTime.now()) + "用户数据").getBytes(), "ISO8859-1"));
            httpServletResponse.setCharacterEncoding("UTF-8");
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            excel.write(outputStream);
            // 释放资源
            outputStream.flush(); // 确保所有数据都被写入
            outputStream.close();
            excel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
