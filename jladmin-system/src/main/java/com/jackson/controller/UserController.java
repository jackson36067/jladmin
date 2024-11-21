package com.jackson.controller;

import com.jackson.annotation.SysLog;
import com.jackson.constant.UserConstant;
import com.jackson.dto.*;
import com.jackson.enumeration.SysLogType;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.service.UserService;
import com.jackson.vo.UserLogVO;
import com.jackson.vo.UserLoginVO;
import com.jackson.vo.UserVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @GetMapping("/code")
    public void generateCode(HttpServletResponse httpServletResponse) {
        userService.generateCode(httpServletResponse);
    }


    @PostMapping("/login")
    @SysLog(value = UserConstant.LOGIN_USER_LOG, type = SysLogType.LOGIN) // 操作日志记录
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {
        return userService.login(userLoginDTO, request, response);
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
    @SysLog(value = UserConstant.UPDATE_USER_LOG, type = SysLogType.UPDATE)
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
    @SysLog(value = UserConstant.ADD_USER_LOG, type = SysLogType.ADD)
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
    @SysLog(value = UserConstant.DELETE_USER_LOG, type = SysLogType.DELETE)
    public Result<Void> deleteUserByIdIn(@RequestBody List<Long> ids) {
        return userService.deleteUserById(ids);
    }

    /**
     * 导出用户数据
     *
     * @param httpServletResponse
     * @return
     */
    @PostMapping("/export")
    @PreAuthorize(value = "hasAuthority('user:list')")
    public void exportUserData(HttpServletResponse httpServletResponse) {
        userService.exportUserData(httpServletResponse);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @PreAuthorize(value = "hasAuthority('user:list')")
    public void logout(HttpServletRequest request) {
        userService.logout(request);
    }

    /**
     * 分页获取在线用户列表
     *
     * @param page
     * @param pageSize
     * @param username
     * @return
     */
    @GetMapping("/online")
    public Result<PagingResult> getOnlineUserPaging(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String username
    ) {
        return userService.getOnlineUserPaging(page, pageSize, username);
    }

    /**
     * 强制退出某个用户账号
     *
     * @param ipList
     */
    @PostMapping("/force")
    @SysLog(value = UserConstant.BLOCK_USER_LOG, type = SysLogType.BLOCK)
    public void ForcedWithdrawal(@RequestBody List<String> ipList) {
        userService.ForcedWithdrawal(ipList);
    }

    /**
     * 导出在线用户数据
     *
     * @param response
     */
    @GetMapping("/online/export")
    public void exportOnlineUserInfo(HttpServletResponse response) {
        userService.exportOnlineUserInfo(response);
    }

    /**
     * 根据用户名分页获取用户操作日志
     *
     * @param username
     * @return
     */
    @GetMapping("/center/log")
    public Result<PagingResult> getUserLog(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String username) {
        return userService.getUserLog(page, pageSize, username);
    }

    /**
     * 发送验证码到指定邮箱
     *
     * @param email
     */
    @GetMapping("/email/code")
    public void sentEmailCode(String email) {
        userService.sentEmailCode(email);
    }

    /**
     * 修改用户邮箱
     *
     * @param updateEmailDTO
     */
    @PutMapping("/update/email")
    @SysLog(value = UserConstant.UPDATE_USER_EMAIL_LOG, type = SysLogType.UPDATE)
    public void updateEmail(@RequestBody UpdateEmailDTO updateEmailDTO) {
        userService.updateEmail(updateEmailDTO);
    }

    /**
     * 修改用户密码
     * @param updatePasswordDTO
     */
    @PutMapping("/update/password")
    @SysLog(value = UserConstant.UPDATE_USER_PASSWORD_LOG, type = SysLogType.UPDATE)
    public void updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
        userService.updatePassword(updatePasswordDTO);
    }
}
