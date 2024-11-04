package com.jackson.service;

import com.jackson.dto.UserDTO;
import com.jackson.dto.UpdateUserDTO;
import com.jackson.dto.UserLoginDTO;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.vo.UserLoginVO;
import com.jackson.vo.UserVO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    Result<UserLoginVO> login(UserLoginDTO userLoginDTO);

    Result<PagingResult> getUserWithPaging(Integer pageSize, Integer page, String usernameOrEmail, LocalDateTime begin, LocalDateTime end, Boolean enabled);

    Result<List<UserVO>> getUserListByDeptId(Long deptId);

    Result<Void> updateUser(Long id, UpdateUserDTO updateUserDTO);

    Result<Void> saveUser(UserDTO userDTO);

    Result<UserDTO> getUserInfoById(Long id);

    Result<Void> deleteUserById(List<Long> ids);

    void exportUserData(HttpServletResponse httpServletResponse);
}
