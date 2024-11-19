package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.jackson.Repository.LogRepository;
import com.jackson.constant.LogConstant;
import com.jackson.entity.Log;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.LogService;
import com.jackson.utils.DateTimeUtils;
import com.jackson.vo.LogExportDataVO;
import com.jackson.vo.LogVO;
import com.jackson.vo.QuartzJobExportDataVO;
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
public class LogServiceImpl implements LogService {

    @Resource
    private LogRepository logRepository;

    /**
     * 分页展示操作日志
     *
     * @param page
     * @param pageSize
     * @param location
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public Result<PagingResult> getLogWithPaging(Integer page, Integer pageSize, String location, LocalDateTime beginTime, LocalDateTime endTime) {
        Specification<Log> logSpecification = (Specification<Log>) (root, query, cb) -> {
            ArrayList<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.hasText(location)) {
                Predicate predicate = cb.like(root.get(LogConstant.ADDRESS), "%" + location + "%");
                predicateList.add(predicate);
            }
            if (beginTime != null && endTime != null) {
                Predicate createTime = cb.between(root.get(LogConstant.CREATE_TIME), beginTime, endTime);
                predicateList.add(createTime);
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, LogConstant.CREATE_TIME));
        Page<Log> logRepositoryAll = logRepository.findAll(logSpecification, pageRequest);
        List<LogVO> logVOList = logRepositoryAll.getContent()
                .stream()
                .map(log -> BeanUtil.copyProperties(log, LogVO.class))
                .toList();
        PagingResult pagingResult = new PagingResult(logRepository.count(), logVOList);
        return Result.success(pagingResult);
    }

    /**
     * 导出日志数据
     *
     * @param response
     */
    @Override
    public void exportLogInfo(HttpServletResponse response) {
        InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream("templates/日志数据.xlsx");
        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("sheet1");
            List<LogExportDataVO> logExportDataVOList = logRepository.findAll()
                    .stream()
                    .map(log -> BeanUtil.copyProperties(log, LogExportDataVO.class))
                    .toList();
            int RowIndex = 1;
            for (LogExportDataVO logExportDataVO
                    : logExportDataVOList) {
                XSSFRow row = sheet.createRow(RowIndex);
                row.createCell(0).setCellValue(logExportDataVO.getUsername());
                row.createCell(1).setCellValue(logExportDataVO.getRequestIp());
                row.createCell(2).setCellValue(logExportDataVO.getAddress());
                row.createCell(3).setCellValue(logExportDataVO.getDescription());
                row.createCell(4).setCellValue(logExportDataVO.getBrowser());
                row.createCell(5).setCellValue(logExportDataVO.getTime() + "ms");
                row.createCell(6).setCellValue(logExportDataVO.getExceptionDetail());
                row.createCell(7).setCellValue(DateTimeUtils.formatLocalDateTime(logExportDataVO.getCreateTime()));
                RowIndex++;
            }

            // 设置请求头,让浏览器下载该文件
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((DateTimeUtils.formatLocalDateTime(LocalDateTime.now()) + "用户数据").getBytes(), "ISO8859-1"));
            response.setCharacterEncoding("UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
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
