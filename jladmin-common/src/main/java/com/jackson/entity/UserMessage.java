package com.jackson.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sys_user_message")
@EntityListeners(AuditingEntityListener.class)
public class UserMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "sender")
    private String sender;
    @Column(name = "content")
    private String content;
    @Column(name = "recipient")
    private String recipient;
    @Column(name = "send_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    public UserMessage() {
    }

    public UserMessage(Long id, String sender, String content, String recipient, LocalDateTime sendTime) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.recipient = recipient;
        this.sendTime = sendTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String username) {
        this.sender = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String message) {
        this.content = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMessage that = (UserMessage) o;
        return Objects.equals(id, that.id) && Objects.equals(sender, that.sender) && Objects.equals(content, that.content) && Objects.equals(recipient, that.recipient) && Objects.equals(sendTime, that.sendTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, content, recipient, sendTime);
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "id=" + id +
                ", username='" + sender + '\'' +
                ", message='" + content + '\'' +
                ", recipient='" + recipient + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}