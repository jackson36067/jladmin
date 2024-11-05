package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.Repository.MenuRepository;
import com.jackson.entity.Menu;
import com.jackson.result.Result;
import com.jackson.service.MenuService;
import com.jackson.vo.MenuListVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;

    @Override
    public Result<List<MenuListVO>> getMenuList(String title, LocalDateTime begin, LocalDateTime end) {
        // 递归获取所有菜单及其子菜单
        // 如果没有请求条件,那么直接获取以及菜单
        List<Menu> menuList = new ArrayList<>();
        if (!StringUtils.hasText(title) && begin == null && end == null) {
            // 获取一级菜单集合
            menuList = menuRepository.findAll().stream().filter(menu -> menu.getType() == 0).toList();
        }
        List<MenuListVO> menuListVOList = recursionForMenu(menuList);
        return Result.success(menuListVOList);
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
