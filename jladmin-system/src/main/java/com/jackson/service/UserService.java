package com.jackson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jackson.dto.*;
import com.jackson.entity.UserMessage;
import com.jackson.result.PagingResult;
import com.jackson.result.Result;
import com.jackson.vo.UserFriendVO;
import com.jackson.vo.UserLoginVO;
import com.jackson.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    Result<UserLoginVO> login(UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException;

    Result<PagingResult> getUserWithPaging(Integer pageSize, Integer page, String usernameOrEmail, LocalDateTime begin, LocalDateTime end, Boolean enabled);

    Result<List<UserVO>> getUserListByDeptId(Long deptId);

    Result<Void> updateUser(Long id, UpdateUserDTO updateUserDTO);

    Result<Void> saveUser(UserDTO userDTO);

    Result<UserDTO> getUserInfoById(Long id);

    Result<Void> deleteUserById(List<Long> ids);

    void exportUserData(HttpServletResponse httpServletResponse);

    void generateCode(HttpServletResponse httpServletResponse);

    void logout(HttpServletRequest request);

    Result<PagingResult> getOnlineUserPaging(Integer page, Integer pageSize, String username);

    void ForcedWithdrawal(List<String> ipList);

    void exportOnlineUserInfo(HttpServletResponse response);

    Result<PagingResult> getUserLog(Integer page, Integer pageSize, String username);

    void sentEmailCode(String email);

    void updateEmail(UpdateEmailDTO updateEmailDTO);

    void updatePassword(UpdatePasswordDTO updatePasswordDTO);

    Result<List<UserFriendVO>> getAllFriendUser(String username);

    Result<List<UserMessage>> getUsersMessage(String username,String friendUsername);

    Result<String> uploadImage(MultipartFile image);
}
