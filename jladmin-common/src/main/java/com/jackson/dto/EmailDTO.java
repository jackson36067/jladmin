package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class EmailDTO implements Serializable {
    private String subject;
    private String to;
    private String text;

    public EmailDTO() {
    }

    public EmailDTO(String subject, String to, String text) {
        this.subject = subject;
        this.to = to;
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDTO emailDTO = (EmailDTO) o;
        return Objects.equals(subject, emailDTO.subject) && Objects.equals(to, emailDTO.to) && Objects.equals(text, emailDTO.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, to, text);
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "subject='" + subject + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
