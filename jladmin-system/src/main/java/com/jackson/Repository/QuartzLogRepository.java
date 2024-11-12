package com.jackson.Repository;

import com.jackson.entity.QuartzLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartzLogRepository extends JpaRepository<QuartzLog, Long> {
}
