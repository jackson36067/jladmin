package com.jackson.Repository;

import com.jackson.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {
}
