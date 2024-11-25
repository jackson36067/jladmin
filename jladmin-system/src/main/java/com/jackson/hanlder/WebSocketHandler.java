package com.jackson.hanlder;

import cn.hutool.json.JSONUtil;
import com.jackson.Repository.UserMessageRepository;
import com.jackson.Repository.UserRepository;
import com.jackson.entity.UserMessage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserMessageRepository userMessageRepository;

    private static final ConcurrentHashMap<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper(); // 用于解析 JSON

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 用户通过 URL 参数（例如 /chat?user=username）传递用户名
        String username = session.getUri().getQuery().split("=")[0];
        userSessions.put(username, session);
        log.info("User connected: {}", username);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 解析收到的 JSON 消息
        UserMessage bean = JSONUtil.toBean(message.getPayload(), UserMessage.class);
        String toUser = bean.getRecipient();
        // 找到目标用户会话并发送消息
        WebSocketSession toSession = userSessions.get(toUser);
        if (toSession != null && toSession.isOpen()) {
            toSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(bean)));
            userMessageRepository.save(bean);
        } else {
            log.info("User {} is not connected.", toUser);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 移除断开连接的用户
        userSessions.values().remove(session);
        log.info("Connection closed: {}", session.getId());
    }
}
