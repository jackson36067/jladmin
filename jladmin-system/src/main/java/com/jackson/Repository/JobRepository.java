package com.jackson.Repository;

import com.jackson.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * 通过状态获取所有岗位
     * @param enabled
     * @return
     */
    List<Job> findAllByEnabled(Boolean enabled);

    /**
     * 通过岗位名称集合获取所有岗位对象
     * @param idList
     * @return
     */
    Set<Job> findAllByIdIn(List<Long> idList);
}
