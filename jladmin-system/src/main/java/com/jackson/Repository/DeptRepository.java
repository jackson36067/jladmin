package com.jackson.Repository;

import com.jackson.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DeptRepository extends JpaRepository<Dept, Long>, JpaSpecificationExecutor<Dept> {

    /**
     * 通过用户id后去部门名称
     *
     * @param userId
     * @return
     */
    @Query("SELECT u.dept.name FROM User u WHERE u.id = :userId")
    String findNameByUserId(Long userId);

    /**
     * 通过条件获取部门集合
     *
     * @param name    部门名称
     * @param begin 创建时间范围的开始时间
     * @param end 创建时间的范围的结束时间
     * @param enabled 部门状态(启用或者禁用)
     * @return Dept集合
     */
    List<Dept> findAllByNameAndCreateTimeBetweenAndEnabled(String name, LocalDateTime begin, LocalDateTime end, Boolean enabled);

    /**
     * 通过pid获取用户信息
     * @param pid
     * @return
     */
    List<Dept> findAllByPid(Long pid);

}
