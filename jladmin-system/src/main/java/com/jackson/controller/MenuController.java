package com.jackson.controller;

import com.jackson.dto.AddMenuDTO;
import com.jackson.dto.UpdateMenuDTO;
import com.jackson.result.Result;
import com.jackson.service.MenuService;
import com.jackson.vo.MenuListVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 获取菜单集合
     *
     * @return
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('menu:list')")
    public Result<List<MenuListVO>> getMenuList(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String begin,
            @RequestParam(required = false) String end
    ) {
        LocalDateTime beginTime = null;
        LocalDateTime endTime = null;
        if (StringUtils.hasText(begin) & StringUtils.hasText(end)) {
            beginTime = LocalDateTime.parse(begin);
            endTime = LocalDateTime.parse(end);
        }
        return menuService.getMenuList(title, beginTime, endTime);
    }

    /**
     * 新增菜单
     *
     * @param addMenuDTO
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('menu:list')")
    public void addMenu(@RequestBody AddMenuDTO addMenuDTO) {
        menuService.addMenu(addMenuDTO);
    }

    /**
     * 编辑菜单
     *
     * @param updateMenuDTO
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('menu:list')")
    public void updateMenu(@RequestBody UpdateMenuDTO updateMenuDTO) {
        menuService.updateMenu(updateMenuDTO);
    }

    /**
     * 根据id批量删除菜单
     *
     * @param ids
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('menu:list')")
    public void deleteMenuByIds(@RequestBody List<Long> ids) {
        menuService.deleteMenuByIds(ids);
    }

    /**
     * 导出菜单数据
     */
    @GetMapping("/export")
    @PreAuthorize("hasAuthority('menu:list')")
    public void exportMenuData(HttpServletResponse httpServletResponse) {
        menuService.exportMenuData(httpServletResponse);
    }
}
