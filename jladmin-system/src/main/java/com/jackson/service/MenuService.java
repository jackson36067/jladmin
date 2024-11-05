package com.jackson.service;

import com.jackson.result.Result;
import com.jackson.vo.MenuListVO;

import java.time.LocalDateTime;
import java.util.List;

public interface MenuService {
    Result<List<MenuListVO>> getMenuList(String title, LocalDateTime begin, LocalDateTime end);
}
