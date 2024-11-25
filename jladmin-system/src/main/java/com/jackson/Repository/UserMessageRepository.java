package com.jackson.Repository;

import com.jackson.entity.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserMessageRepository extends JpaRepository<UserMessage,Long>, JpaSpecificationExecutor<UserMessage> {
}
