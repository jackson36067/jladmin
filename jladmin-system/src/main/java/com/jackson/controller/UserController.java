package com.jackson.controller;

import com.jackson.dto.UserDTO;
import com.jackson.dto.UpdateUserDTO;
import com.jackson.dto.UserLoginDTO;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.UserService;
import com.jackson.vo.UserLoginVO;
import com.jackson.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    /**
     * 根据条件分页展示用户数据
     *
     * @param pageSize        页码数
     * @param page            页码
     * @param usernameOrEmail 名称或者邮箱
     * @param begin           开始时间
     * @param end             结束时间
     * @param enabled         用户账号状态
     * @return Result<UserVO>
     */
    @GetMapping("/list")
    @PreAuthorize(value = "hasAuthority('user:list')")
    public Result<PagingResult> getUserWithPaging(
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(required = false) String usernameOrEmail,
            @RequestParam(required = false) String begin,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) Boolean enabled
    ) {
        LocalDateTime beginTime = null;
        LocalDateTime endTime = null;
        if (StringUtils.hasText(begin) & StringUtils.hasText(end)) {
            beginTime = LocalDateTime.parse(begin);
            endTime = LocalDateTime.parse(end);
        }
        return userService.getUserWithPaging(pageSize, page, usernameOrEmail, beginTime, endTime, enabled);
    }

    /**
     * 根据部门id获取用户信息
     *
     * @param deptId
     * @return
     */
    @GetMapping("/dept")
    @PreAuthorize(value = "hasAuthority('user:list')")
    public Result<List<UserVO>> getUserListByDeptId(Long deptId) {
        return userService.getUserListByDeptId(deptId);
    }


    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<UserDTO> getUserInfoById(@PathVariable Long id) {
        return userService.getUserInfoById(id);
    }

    /**
     * 根据用户id修改用户状态
     *
     * @param id
     * @param updateUserDTO
     * @return
     */
    @PutMapping("/update/{id}")
    @PreAuthorize(value = "hasAuthority('user:list')")
    public Result<Void> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        return userService.updateUser(id, updateUserDTO);
    }

    /**
     * 新增用户
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/save")
    @PreAuthorize(value = "hasAuthority('user:list')")
    public Result<Void> saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    /**
     * 根据id删除用户,支持多删
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    @PreAuthorize(value = "hasAuthority('user:list')")
    public Result<Void> deleteUserByIdIn(@RequestBody List<Long> ids) {
        return userService.deleteUserById(ids);
    }
}
