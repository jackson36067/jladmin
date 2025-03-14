package com.jackson.Repository;

import com.jackson.entity.Role;
import com.jackson.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 通过用户名获取用户
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 通过部门id集合获取部门下的所有用户
     *
     * @param deptId
     * @return
     */
    List<User> findAllByDeptIdIn(List<Long> deptId);

    User findByEmail(String email);

    User findByPhone(String phone);

    @Query("SELECT u FROM User u JOIN u.roleSet r WHERE r.id IN :roleIds")
    List<User> findByRoleIds(@Param("roleIds") List<Long> roleIds);
}
