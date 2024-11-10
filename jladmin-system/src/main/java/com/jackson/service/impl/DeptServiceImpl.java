package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.jackson.Repository.DeptRepository;
import com.jackson.constant.DeptConstant;
import com.jackson.dto.AddDeptDTO;
import com.jackson.dto.UpdateDeptDTO;
import com.jackson.entity.Dept;
import com.jackson.exception.DeptNameException;
import com.jackson.exception.SortRepeatException;
import com.jackson.result.Result;
import com.jackson.service.DeptService;
import com.jackson.util.DateTimeUtils;
import com.jackson.util.ListUtils;
import com.jackson.vo.DeptExportDataVO;
import com.jackson.vo.DeptVO;
import com.jackson.vo.RoleExportDataVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptRepository deptRepository;

    @Override
    public Result<List<DeptVO>> getDeptList(String name, LocalDateTime begin, LocalDateTime end, Boolean enabled) {
        List<Dept> deptList = new ArrayList<>();
        if (!StringUtils.hasText(name) && begin == null && end == null & enabled == null) {
            // 获取一级部门集合
            deptList = deptRepository.findAll().stream().filter(dept -> dept.getPid() == null).toList();
        } else {
            Specification<Dept> deptSpecification = (root, query, cb) -> {
                List<Predicate> predicateList = new ArrayList<>();
                if (StringUtils.hasText(name)) {
                    Predicate predicate = cb.like(root.get("name"), name);
                    predicateList.add(predicate);
                }
                if (begin != null & end != null) {
                    Predicate predicate = cb.between(root.get("createTime"), begin, end);
                    predicateList.add(predicate);
                }
                if (enabled != null) {
                    Predicate predicate = cb.equal(root.get("enabled"), enabled);
                    predicateList.add(predicate);
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                return cb.and(predicateList.toArray(predicates));
            };
            // 根据条件获取部门集合
            deptList = deptRepository.findAll(deptSpecification);
        }
        // 封装条件
        List<DeptVO> deptVOList = recursionForDept(deptList);
        return Result.success(deptVOList);
    }

    /**
     * 根据id获取部门
     *
     * @param id
     * @return
     */
    @Override
    public Result<Dept> getDeptById(Long id) {
        return Result.success(deptRepository.findById(id).get());
    }

    /**
     * 新增部门
     *
     * @param addDeptDTO
     */
    @Override
    public void addDept(AddDeptDTO addDeptDTO) {
        Dept dept = BeanUtil.copyProperties(addDeptDTO, Dept.class);
        if (deptRepository.findByName(dept.getName()) != null) {
            throw new DeptNameException(DeptConstant.NAME_EXIST);
        }
        if (deptRepository.findByDeptSort(dept.getDeptSort()) != null) {
            throw new SortRepeatException(DeptConstant.DEPT_SORT_REPEAT);
        }
        if (dept.getPid() != null) {
            Dept dept1 = deptRepository.findById(dept.getPid()).get();
            dept1.setSubCount(dept1.getSubCount() == null ? 1 : dept1.getSubCount() + 1);
        } else {
            dept.setSubCount(0);
        }
        deptRepository.save(dept);
    }

    /**
     * 编辑部门
     *
     * @param updateDeptDTO
     */
    @Override
    // TODO 顶级部门的判断
    public void updateDept(UpdateDeptDTO updateDeptDTO) {
        Long deptId = updateDeptDTO.getId();
        String name = updateDeptDTO.getName();
        Integer deptSort = updateDeptDTO.getDeptSort();
        Boolean enabled = updateDeptDTO.getEnabled();
        Long pid = updateDeptDTO.getPid();
        Dept dept = deptRepository.findById(deptId).get();
        if (StringUtils.hasText(name) & !dept.getName().equals(name)) {
            if (deptRepository.findByName(name) != null) {
                throw new DeptNameException(DeptConstant.NAME_EXIST);
            }
            dept.setName(name);
        }
        if (deptSort != null & !Objects.equals(deptSort, dept.getDeptSort())) {
            if (deptRepository.findByDeptSort(deptSort) != null) {
                throw new SortRepeatException(DeptConstant.DEPT_SORT_REPEAT);
            }
            dept.setDeptSort(deptSort);
        }
        if (enabled != null & enabled != dept.getEnabled()) {
            dept.setEnabled(enabled);
        }
        if (pid != null & !Objects.equals(pid, dept.getPid())) {
            // 原本的上级部门子部门数➖1
            if (dept.getPid() != null) {
                // 不是顶级部门就不要➖1
                Dept dept1 = deptRepository.findById(dept.getPid()).get();
                dept1.setSubCount(dept1.getSubCount() - 1);
            }
            // 现在的上级部门子部门数➕1
            Dept dept2 = deptRepository.findById(pid).get();
            dept2.setSubCount(dept2.getSubCount() == null ? 1 : dept2.getSubCount() + 1);
            dept.setPid(pid);
        }
        deptRepository.saveAndFlush(dept);
    }

    /**
     * 批量删除部门
     *
     * @param ids
     */
    @Override
    public void deleteDeptByIds(List<Long> ids) {
        List<Dept> deptList = deptRepository.findAllById(ids);
        deptList.forEach(dept -> {
            // 删除顶级部门不需要将子部门数量减1
            Long pid = dept.getPid();
            if (pid != null) {
                Dept dept1 = deptRepository.findById(pid).get();
                dept1.setSubCount(dept1.getSubCount() - 1);
            } else {
                // 当删除的id只有一个顶级部门的id时,将其所有子部门id一起删除
                List<Long> subDeptIds = getAllSubDeptIds(dept.getId());
                // 判断子部门的id是否已经存在在传递的ids中
                ListUtils.mergeLists(ids, subDeptIds);
            }
        });
        deptRepository.deleteAllByIdInBatch(ids);
    }

    /**
     * 导出部门数据
     *
     * @param httpServletResponse
     */
    @Override
    public void exportDeptData(HttpServletResponse httpServletResponse) {
        InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream("templates/部门数据.xlsx");
        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("sheet1");
            List<DeptExportDataVO> deptExportDataVOS = deptRepository.findAll()
                    .stream()
                    .map(dept -> BeanUtil.copyProperties(dept, DeptExportDataVO.class))
                    .toList();
            int RowIndex = 1;
            for (DeptExportDataVO deptExportDataVO : deptExportDataVOS) {
                XSSFRow row = sheet.createRow(RowIndex);
                row.createCell(0).setCellValue(deptExportDataVO.getName());
                row.createCell(1).setCellValue(deptExportDataVO.getEnabled() ? "激活" : "禁用");
                row.createCell(2).setCellValue(DateTimeUtils.formatLocalDateTime(deptExportDataVO.getCreateTime()));
                RowIndex++;
            }
            // 设置请求头,让浏览器下载该文件
            httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            String fileName = URLEncoder.encode(DateTimeUtils.formatLocalDateTime(LocalDateTime.now()) + "部门数据.xls", "UTF-8");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            httpServletResponse.setCharacterEncoding("UTF-8");
            // 防止浏览器缓存导致的文件损坏问题
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

    /**
     * 递归通过部门id获取其所有子部门的id
     *
     * @param parentId
     * @return
     */
    public List<Long> getAllSubDeptIds(Long parentId) {
        List<Long> subDeptIds = new ArrayList<>();
        subDeptIds.add(parentId);
        List<Dept> subDeptList = deptRepository.findAllByPid(parentId); // 获取直接下级部门
        if (subDeptList != null) {
            for (Dept dept : subDeptList) {
                subDeptIds.add(dept.getId()); // 添加当前下级部门 ID
                subDeptIds.addAll(getAllSubDeptIds(dept.getId())); // 递归查找下下级部门
            }
        }
        return subDeptIds;
    }

    /**
     * 递归获取部门集合
     *
     * @return
     */
    public List<DeptVO> recursionForDept(List<Dept> deptList) {
        List<DeptVO> deptVOList = deptList.stream().map(dept -> BeanUtil.copyProperties(dept, DeptVO.class)).toList();
        for (DeptVO deptVO : deptVOList) {
            Long deptId = deptVO.getId();
            List<Dept> subDept = deptRepository.findAllByPid(deptId);
            if (subDept != null) {
                List<DeptVO> deptVOList1 = recursionForDept(subDept);
                deptVO.setSubDeptVOList(deptVOList1);
            }
        }
        return deptVOList;
    }


}
