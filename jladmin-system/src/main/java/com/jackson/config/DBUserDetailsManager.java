package com.jackson.config;

import com.jackson.Repository.MenuRepository;
import com.jackson.Repository.RoleRepository;
import com.jackson.Repository.UserRepository;
import com.jackson.entity.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository repository;
    @Resource
    private MenuRepository menuRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        // 通过用户id获取角色id集合
        List<Long> roleIdList = repository.findRoleIdsByUserId(user.getId());
        // 通过角色id集合从menu中获取权限
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = menuRepository.findPermissionsByRoleIds(roleIdList) // 用户权限集合
                .stream()
                .filter(permission -> !(permission == null || permission.isEmpty())) // 过滤出空权限
                .map(SimpleGrantedAuthority::new) // 封装成SimpleGrantedAuthority保存权限
                .toList();
        // 返回用户
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                simpleGrantedAuthorities
        );
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
