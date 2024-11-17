package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.jackson.Repository.QuartzLogRepository;
import com.jackson.entity.QuartzLog;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.QuartzLogService;
import com.jackson.utils.DateTimeUtils;
import com.jackson.vo.QuartzLogExportDataVO;
import com.jackson.vo.QuartzLogVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuartzLogServiceImpl implements QuartzLogService {

    @Resource
    private QuartzLogRepository quartzLogRepository;

    @Override
    public Result<PagingResult> getQuartzLogPaging(Integer page, Integer pageSize, String jobName, LocalDateTime begin, LocalDateTime end, Boolean isSuccess) {
        Specification<QuartzLog> quartzLogSpecification = (root, query, cb) -> {
            ArrayList<Predicate> predicateArrayList = new ArrayList<>();
            if (StringUtils.hasText(jobName)) {
                Predicate predicate = cb.like(root.get("jobName"), "%" + jobName + "%");
                predicateArrayList.add(predicate);
            }
            if (begin != null && end != null) {
                Predicate predicate = cb.between(root.get("createTime"), begin, end);
                predicateArrayList.add(predicate);
            }
            if (isSuccess != null) {
                Predicate predicate = cb.equal(root.get("isSuccess"), isSuccess);
                predicateArrayList.add(predicate);
            }
            Predicate[] predicates = new Predicate[predicateArrayList.size()];
            return cb.and(predicateArrayList.toArray(predicates));
        };
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
        Page<QuartzLog> quartzLogRepositoryAll = quartzLogRepository.findAll(quartzLogSpecification, pageRequest);
        List<QuartzLogVO> quartzLogVOList = quartzLogRepositoryAll.getContent()
                .stream()
                .map(quartzLog -> BeanUtil.copyProperties(quartzLog, QuartzLogVO.class))
                .toList();
        PagingResult pagingResult = new PagingResult(quartzLogRepository.count(), quartzLogVOList);
        return Result.success(pagingResult);
    }

    /**
     * 导出任务日志数据
     *
     * @param httpServletResponse
     */
    @Override
    public void exportQuartzLogData(HttpServletResponse httpServletResponse) {
        InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream("templates/任务日志数据.xlsx");
        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("sheet1");
            List<QuartzLogExportDataVO> quartzJobExportDataVOS = quartzLogRepository.findAll()
                    .stream()
                    .map(quartzLog -> BeanUtil.copyProperties(quartzLog, QuartzLogExportDataVO.class))
                    .toList();
            int RowIndex = 1;
            for (QuartzLogExportDataVO quartzLogExportDataVO : quartzJobExportDataVOS) {
                XSSFRow row = sheet.createRow(RowIndex);
                row.createCell(0).setCellValue(quartzLogExportDataVO.getJobName());
                row.createCell(1).setCellValue(quartzLogExportDataVO.getClassName());
                row.createCell(2).setCellValue(quartzLogExportDataVO.getCronExpression());
                row.createCell(3).setCellValue(StringUtils.hasText(quartzLogExportDataVO.getExceptionDetail()) ? quartzLogExportDataVO.getExceptionDetail() : "");
                row.createCell(4).setCellValue(quartzLogExportDataVO.getTime() * 1000); // 展示毫秒
                row.createCell(5).setCellValue(quartzLogExportDataVO.getisSuccess() ? "成功" : "失败");
                row.createCell(6).setCellValue(DateTimeUtils.formatLocalDateTime(quartzLogExportDataVO.getCreateTime()));
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
