package com.jackson;

import com.jackson.Repository.MenuRepository;
import com.jackson.entity.Menu;
import com.jackson.service.MenuService;
import com.jackson.service.impl.MenuServiceImpl;
import com.jackson.vo.MenuListVO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class MenuListTest {

    @Resource
    private MenuRepository menuRepository;
    @Resource
    private MenuServiceImpl menuService;

    @Test
    public void testGetMenuList() {
        List<Menu> menuStream = menuRepository.findAll().stream().filter(menu -> menu.getType() == 0).toList();
        List<MenuListVO> menuListVOList = menuService.recursionForMenu(menuStream);
        for (MenuListVO menuListVO : menuListVOList) {
            System.out.println(menuListVO);
            System.out.println();
        }
    }

}
