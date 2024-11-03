package com.jackson.hanlder;

import com.jackson.exception.BaseException;
import com.jackson.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     *
     * @param be
     * @return
     */
    @ExceptionHandler
    public void ExceptionHandler(Exception be, HttpServletResponse httpServletResponse) throws IOException {
        log.info("异常信息:{}", be.getMessage());
        httpServletResponse.setStatus(500);
        httpServletResponse.getWriter().write(be.getMessage());
    }
}
