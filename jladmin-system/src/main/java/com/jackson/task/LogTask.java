package com.jackson.task;

import cn.hutool.json.JSONUtil;
import com.jackson.Repository.LogRepository;
import com.jackson.constant.LogConstant;
import com.jackson.constant.RedisConstant;
import com.jackson.entity.Log;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志定时任务
 */
@Component
public class LogTask implements Job {

    @Resource
    private LogRepository logRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 每个月月底执行该任务
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 在每个月的月底进行清除该月的操作日志以及异常日志 (该月前一个礼拜留着)
        LocalDate now = LocalDate.now();
        // 获取7天前的日期
        LocalDate localDate = now.plusDays(-7);
        LocalDateTime localDateTime = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0, 0);
        // 封装条件
        Specification<Log> logSpecification = (root, query, cb) -> {
            ArrayList<Predicate> predicateArrayList = new ArrayList<>();
            Predicate predicate = cb.lessThan(root.get(LogConstant.CREATE_TIME), localDateTime);
            predicateArrayList.add(predicate);
            return cb.and(predicateArrayList.toArray(new Predicate[0]));
        };
        // 将删除的信息保存到redis
        List<Log> all = logRepository.findAll(logSpecification);
        all.forEach(log ->
                stringRedisTemplate.opsForZSet().add(RedisConstant.LOG_KEY, JSONUtil.toJsonStr(log), System.currentTimeMillis())
        );
        // 删除该月前23天的日志
        logRepository.delete(logSpecification);
    }
}
