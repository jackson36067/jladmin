package com.jackson.service;

import com.jackson.dto.AddMenuDTO;
import com.jackson.dto.UpdateMenuDTO;
import com.jackson.result.Result;
import com.jackson.vo.MenuListVO;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface MenuService {
    Result<List<MenuListVO>> getMenuList(String title, LocalDateTime begin, LocalDateTime end);

    void addMenu(AddMenuDTO addMenuDTO);

    void updateMenu(UpdateMenuDTO updateMenuDTO);

    void deleteMenuByIds(List<Long> ids);

    void exportMenuData(HttpServletResponse httpServletResponse);
}
