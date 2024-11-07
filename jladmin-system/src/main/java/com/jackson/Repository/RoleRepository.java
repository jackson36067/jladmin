package com.jackson.Repository;

import com.jackson.entity.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    @Query("SELECT r.id FROM Role r JOIN r.userSet u where u.id = :userId")
    List<Long> findRoleIdsByUserId(@Param("userId") Long userId);

    /**
     * 通过角色名称集合获取所有角色
     *
     * @param idList
     * @return
     */
    Set<Role> findAllByIdIn(List<Long> idList);

    Role findByName(String name);

    Role findByIdIn(List<Long> id);
}
