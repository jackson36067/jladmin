package com.jackson;

import com.jackson.Repository.MenuRepository;
import com.jackson.Repository.RoleRepository;
import com.jackson.entity.Menu;
import com.jackson.entity.Role;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static junit.framework.Assert.assertEquals;

//@SpringBootTest
@Slf4j
class JladminSystemApplicationTests {

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }

    /**
     * 测试RoleRepository中findRoleIdsByUserId是否有用
     */
    @Test
    public void testFindRoleIdsByUserId() {
        List<Long> roleIdList = roleRepository.findRoleIdsByUserId(1L);
        roleIdList.forEach(System.out::println);
    }

    @Test
    public void testFindPermissionsByRoleIdList() {
        ArrayList<Long> list = new ArrayList<>();
        Collections.addAll(list, 1L);
        List<String> permissions = new ArrayList<>();
        permissions = menuRepository.findPermissionsByRoleIds(list);
        List<String> list1 = permissions.stream().filter(permission -> !(permission == null)).toList();
        list1.forEach(System.out::println);
        System.out.println(permissions.size());
        list.add(2L);
        permissions = menuRepository.findPermissionsByRoleIds(list);
        System.out.println(permissions.size());
    }

    @Test
    public void testBcrypt() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Transactional
    @Test
    public void testFinaMenuByRoleIds() {
        ArrayList<Long> list = new ArrayList<>();
        list.add(1L);
        List<Menu> allByRoleSet = menuRepository.findAllByRoleIds(list);
    }

    @Test
    public void testUserPaging() {
        Result<PagingResult> admin = userService.getUserWithPaging(10, 1, null, LocalDateTime.of(2017, 10, 30, 0, 0, 0), LocalDateTime.of(2024, 10, 30, 0, 0, 0), true);
        PagingResult pagingResult = admin.getData();
        System.out.println(pagingResult.getTotal());
//        List list = pagingResult.getList();
//        list.forEach(System.out::println);
    }
}
