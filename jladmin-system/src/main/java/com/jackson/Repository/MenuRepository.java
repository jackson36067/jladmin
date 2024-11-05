package com.jackson.Repository;

import com.jackson.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT m.permission FROM Role r JOIN r.menuSet m WHERE r.id IN :roleIds")
    List<String> findPermissionsByRoleIds(@Param("roleIds") List<Long> roleIds);

    @Query("SELECT m FROM Menu m JOIN m.roleSet r WHERE r.id IN :roleIds")
    List<Menu> findAllByRoleIds(@Param("roleIds") List<Long> roleIds);

    List<Menu> findAllByPid(Long pid);

    @Query("SELECT m FROM Menu m JOIN m.roleSet r WHERE r.id = :roleId")
    List<Menu> findAllByRoleId(@Param("roleId") Long roleId);

    Set<Menu> findAllByIdIn(List<Long> ids);
}
