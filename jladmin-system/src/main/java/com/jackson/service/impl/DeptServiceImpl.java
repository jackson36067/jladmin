package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.Repository.DeptRepository;
import com.jackson.entity.Dept;
import com.jackson.result.Result;
import com.jackson.service.DeptService;
import com.jackson.vo.DeptVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        // 封装条件
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
        List<Dept> deptList = deptRepository.findAll(deptSpecification);
        // 通过pid将部门进行分组(一级部门,二级部门,三级部门)
        List<DeptVO> deptVOList = deptList.stream().map(dept -> {
            DeptVO deptVO = null;
            // 判断是否为一级部门
            if (dept.getPid() == null) {
                deptVO = BeanUtil.copyProperties(dept, DeptVO.class);
                // 通过一级部门id以及部门pid获取二级部门集合
                List<Dept> subDeptList = deptRepository.findAllByPid(dept.getId());
                List<DeptVO> subDeptVOList = subDeptList.stream().map(subDept -> {
                    // 将二级部门转换为自定义SubDeptVO类型
                    DeptVO subDeptVO = BeanUtil.copyProperties(subDept, DeptVO.class);
                    // 判断二级部门下是否有三级部门
                    if (subDept.getSubCount() > 0) {
                        // 有 -> 获取三级部门转换为ThirdDeptVO
                        List<Dept> thirdDeptList = deptRepository.findAllByPid(subDeptVO.getId());
                        List<DeptVO> thirdDeptVOList = thirdDeptList.stream().map(thirdDept -> BeanUtil.copyProperties(thirdDept, DeptVO.class)).toList();
                        // 封装三级部门信息
                        subDeptVO.setSubDeptVOList(thirdDeptVOList);
                    }
                    return subDeptVO;
                }).toList();
                deptVO.setSubDeptVOList(subDeptVOList);
            }
            return deptVO;
        }).toList();
        // 过滤出空数据
        List<DeptVO> deptVOS = deptVOList.stream().filter(Objects::nonNull).toList();
        return Result.success(deptVOS);
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

    @Override
    public Result<Dept> getDeptById(Long id) {
        return Result.success(deptRepository.findById(id).get());
    }
}
