package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.db.PageResult;
import com.jackson.Repository.RoleRepository;
import com.jackson.constant.RoleConstant;
import com.jackson.constant.UserConstant;
import com.jackson.entity.Role;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.RoleService;
import com.jackson.vo.RoleAllVO;
import com.jackson.vo.RoleVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    /**
     * 获取所有角色名称集合
     *
     * @return
     */
    @Override
    public Result<List<RoleAllVO>> getAllRoles() {
        List<RoleAllVO> roleAllVOList = roleRepository
                .findAll()
                .stream()
                .map(role -> BeanUtil.copyProperties(role, RoleAllVO.class))
                .toList();
        return Result.success(roleAllVOList);
    }

    /**
     * 根据条件分页获取角色数据
     *
     * @param page
     * @param pageSize
     * @param nameOrDescription
     * @param begin
     * @param end
     * @return
     */
    @Override
    public Result<PagingResult> getRoleWithPaging(Integer page, Integer pageSize, String nameOrDescription, LocalDateTime begin, LocalDateTime end) {
        Specification<Role> roleSpecification = (root, query, cb) -> {
            //用于暂时存放查询条件,存放到查询条件List中
            ArrayList<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.hasText(nameOrDescription)) {
                Predicate nod = cb.or(cb.like(root.get("name"), "%" + nameOrDescription + "%"), cb.like(root.get("description"), "%" + nameOrDescription + "%"));
                predicateList.add(nod);
            }
            if (begin != null & end != null) {
                Predicate createTime = cb.between(root.get("createTime"), begin, end);
                predicateList.add(createTime);
            }
            Predicate[] predicates = new Predicate[predicateList.size()];
            // 将所有条件连接起来
            return cb.and(predicateList.toArray(predicates));
        };
        // 排序器
        Sort sort = Sort.by(Sort.Direction.DESC, RoleConstant.CREATE_TIME);
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, sort);
        Page<Role> rolePage = roleRepository.findAll(roleSpecification, pageRequest);
        List<RoleVO> roleVOList = rolePage.getContent()
                .stream()
                .map(role -> BeanUtil.copyProperties(role, RoleVO.class))
                .toList();
        PagingResult pagingResult = new PagingResult(roleVOList.size(), roleVOList);
        return Result.success(pagingResult);
    }
}
