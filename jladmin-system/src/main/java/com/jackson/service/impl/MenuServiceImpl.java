package com.jackson.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.jackson.Repository.MenuRepository;
import com.jackson.Repository.UserRepository;
import com.jackson.constant.MenuConstant;
import com.jackson.dto.AddMenuDTO;
import com.jackson.dto.UpdateMenuDTO;
import com.jackson.entity.Menu;
import com.jackson.exception.MenuNameExistException;
import com.jackson.exception.MenuSortRepeatException;
import com.jackson.exception.MenuTitleExistException;
import com.jackson.result.Result;
import com.jackson.service.MenuService;
import com.jackson.vo.MenuListVO;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
        if (StringUtils.hasText(addMenuDTO.getTitle())) {
            Menu byTitle = menuRepository.findByTitle(menu.getTitle());
            if (byTitle != null) {
                throw new MenuTitleExistException(MenuConstant.MENU_TITLE_EXIST);
            }
        }
        // 判断顺序是否重复
        if (addMenuDTO.getMenuSort() != null) {
            Menu byMenuSort = menuRepository.findByMenuSort(menu.getMenuSort());
            if (byMenuSort != null) {
                throw new MenuSortRepeatException(MenuConstant.MENU_SORT_EXIST);
            }
        }
        // 判断菜单组件名是否已经存在
        if (StringUtils.hasText(addMenuDTO.getName())) {
            Menu byName = menuRepository.findByName(menu.getName());
            if (byName != null) {
                throw new MenuNameExistException(MenuConstant.MENU_NAME_EXIST);
            }
        } else {
            // 防止名字为""时,导致name重复异常
            menu.setName(null);
        }
        menuRepository.save(menu);
        // 判断是否为顶级目录
        if (menu.getType() != 0) {
            // 不是,就要修改该菜单上级子菜单数量
            Menu highMenu = menuRepository.findById(menu.getPid()).get();
            Integer subCount = highMenu.getSubCount();
            highMenu.setSubCount(subCount == null ? 1 : subCount + 1);
            menuRepository.saveAndFlush(highMenu);
        }
    }

    /**
     * 编辑菜单
     *
     * @param updateMenuDTO
     */
    @Override
    public void updateMenu(UpdateMenuDTO updateMenuDTO) {
        String title = updateMenuDTO.getTitle();
        Integer type = updateMenuDTO.getType();
        Integer menuSort = updateMenuDTO.getMenuSort();
        String name = updateMenuDTO.getName();
        String icon = updateMenuDTO.getIcon();
        Boolean iFrame = updateMenuDTO.getiFrame();
        Boolean cache = updateMenuDTO.getCache();
        Boolean hidden = updateMenuDTO.getHidden();
        String permission = updateMenuDTO.getPermission();
        String path = updateMenuDTO.getPath();
        String component = updateMenuDTO.getComponent();
        Long pid = updateMenuDTO.getPid();
        // 根据菜单id获取菜单
        Long menuId = updateMenuDTO.getId();
        Menu menu = menuRepository.findById(menuId).get();
        if (type != null & !Objects.equals(menu.getType(), type)) {
            menu.setType(type);
        }
        if (title != null & !menu.getTitle().equals(title)) {
            Menu byTitle = menuRepository.findByTitle(title);
            if (byTitle != null) {
                throw new MenuTitleExistException(MenuConstant.MENU_TITLE_EXIST);
            }
            menu.setTitle(title);
        }
        if (menuSort != null & !Objects.equals(menu.getMenuSort(), menuSort)) {
            Menu byMenuSort = menuRepository.findByMenuSort(menuSort);
            if (byMenuSort != null) {
                throw new MenuSortRepeatException(MenuConstant.MENU_SORT_EXIST);
            }
            menu.setMenuSort(menuSort);
        }
        if (name != null & !Objects.equals(name, menu.getName())) {
            Menu byName = menuRepository.findByName(name);
            if (byName != null) {
                throw new MenuNameExistException(MenuConstant.MENU_NAME_EXIST);
            }
            menu.setName(name);
        }
        if (icon != null & !menu.getIcon().equals(icon)) {
            menu.setIcon(icon);
        }
        if (iFrame != null & menu.getiFrame() != iFrame) {
            menu.setiFrame(iFrame);
        }
        if (cache != null & menu.getCache() != cache) {
            menu.setCache(cache);
        }
        if (hidden != null & menu.getHidden() != hidden) {
            menu.setHidden(hidden);
        }
        if (permission != null & !Objects.equals(menu.getPermission(), permission)) {
            menu.setPermission(permission);
        }
        if (path != null & !Objects.equals(menu.getPath(), path)) {
            menu.setPath(path);
        }
        if (component != null & !Objects.equals(menu.getComponent(), component)) {
            menu.setComponent(component);
        }
        if (pid != null & !Objects.equals(menu.getPid(), pid)) {
            menu.setPid(pid);
        }
        menuRepository.saveAndFlush(menu);
    }

    /**
     * 根据id批量删除菜单
     *
     * @param ids
     */
    @Override
    public void deleteMenuByIds(List<Long> ids) {
        // 根据删除的id判断是否为子级菜单
        Set<Menu> menuSet = menuRepository.findAllByIdIn(ids);
        menuSet.forEach(menu -> {
            // 删除顶级菜单不不要将子级菜单数量➖1
            if (menu.getType() != 0) {
                Long pid = menu.getPid();
                Menu menu1 = menuRepository.findById(pid).get();
                menu1.setSubCount(menu1.getSubCount() - 1);
            }
        });
        menuRepository.deleteAllByIdInBatch(ids);
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
