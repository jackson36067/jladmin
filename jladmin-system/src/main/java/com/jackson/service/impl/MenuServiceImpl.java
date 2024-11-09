package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.Repository.MenuRepository;
import com.jackson.Repository.UserRepository;
import com.jackson.constant.MenuConstant;
import com.jackson.dto.AddMenuDTO;
import com.jackson.entity.Menu;
import com.jackson.exception.MenuSortRepeatException;
import com.jackson.exception.MenuTitleExistException;
import com.jackson.result.Result;
import com.jackson.service.MenuService;
import com.jackson.vo.MenuListVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private UserRepository userRepository;

    /**
     * 获取菜单
     *
     * @param title
     * @param begin
     * @param end
     * @return
     */
    @Override
    public Result<List<MenuListVO>> getMenuList(String title, LocalDateTime begin, LocalDateTime end) {
        // 递归获取所有菜单及其子菜单
        // 如果没有请求条件,那么直接获取以及菜单
        List<Menu> menuList = new ArrayList<>();
        if (!StringUtils.hasText(title) && begin == null && end == null) {
            // 获取一级菜单集合
            menuList = menuRepository.findAll().stream().filter(menu -> menu.getType() == 0).toList();
        } else {
            // 加入条件
            Specification<Menu> menuSpecification = (root, query, cb) -> {
                ArrayList<Predicate> predicateList = new ArrayList<>();
                if (StringUtils.hasText(title)) {
                    Predicate predicate = cb.like(root.get("title"), "%" + title + "%");
                    predicateList.add(predicate);
                }
                if (begin != null & end != null) {
                    Predicate createTime = cb.between(root.get("createTime"), begin, end);
                    predicateList.add(createTime);
                }
                Predicate[] predicates = new Predicate[predicateList.size()];
                return cb.and(predicateList.toArray(predicates));
            };
            menuList = menuRepository.findAll(menuSpecification);
        }
        List<MenuListVO> menuListVOList = recursionForMenu(menuList);
        return Result.success(menuListVOList);
    }

    /**
     * 新增菜单
     *
     * @param addMenuDTO
     */
    @Transactional
    @Override
    public void addMenu(AddMenuDTO addMenuDTO) {
        // 新增目录
        Menu menu = BeanUtil.copyProperties(addMenuDTO, Menu.class);
        // 判断title是否重复
        Menu byTitle = menuRepository.findByTitle(menu.getTitle());
        if (byTitle != null) {
            throw new MenuTitleExistException(MenuConstant.MENU_TITLE_EXIST);
        }
        Menu byMenuSort = menuRepository.findByMenuSort(menu.getMenuSort());
        if (byMenuSort != null) {
            throw new MenuSortRepeatException(MenuConstant.MENU_SORT_EXIST);
        }
        menuRepository.save(menu);
        // 判断是否为顶级目录
        if (menu.getType() != 0) {
            // 不是,就要修改该菜单上级子菜单数量
            Menu highMenu = menuRepository.findById(menu.getPid()).get();
            highMenu.setSubCount(highMenu.getSubCount() + 1);
            menuRepository.saveAndFlush(highMenu);
        }
    }

    /**
     * 递归获取菜单及其子菜单集合
     *
     * @param menuList 一级菜单集合
     * @return
     */
    public List<MenuListVO> recursionForMenu(List<Menu> menuList) {
        // 转换成MenuListVO
        List<MenuListVO> MenuVOList = menuList.stream().map(menu -> BeanUtil.copyProperties(menu, MenuListVO.class)).toList();
        // 遍历递归
        for (MenuListVO menuListVO : MenuVOList) {
            Long menuId = menuListVO.getId();
            // 获取所有子级菜单
            List<Menu> subMenu = menuRepository.findAllByPid(menuId);
            // 递归
            if (!subMenu.isEmpty()) {
                List<MenuListVO> menuListVOList = recursionForMenu(subMenu);
                menuListVO.setMenuListVOList(menuListVOList);
            }
        }
        return MenuVOList;
    }
}
