package com.jackson.Repository;

import com.jackson.entity.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuartzJobRepository extends JpaRepository<QuartzJob, Long>, JpaSpecificationExecutor<QuartzJob> {
    QuartzJob findByJobName(String jobName);

    QuartzJob findByJobNameAndJobGroup(String jobName, String jobGroup);
}
