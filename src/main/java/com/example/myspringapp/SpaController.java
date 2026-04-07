package com.example.myspringapp;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Vue Router(History 모드) SPA 폴백 필터.
 *
 * Spring Boot 3(PathPatternParser)에서 복잡한 Controller 패턴 대신
 * Filter를 사용해 안정적으로 SPA 라우팅을 처리합니다.
 *
 * 통과 조건 (index.html로 포워딩하지 않음):
 *  - /api/** : REST API
 *  - 경로에 '.' 포함 : 정적 파일 (.js, .css, .png 등)
 */
@Component
@Order(Integer.MAX_VALUE - 1)
public class SpaController implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        boolean isApi    = path.startsWith("/api/");
        boolean isStatic = path.contains(".");

        if (isApi || isStatic) {
            chain.doFilter(request, response);
        } else {
            req.getRequestDispatcher("/index.html").forward(request, response);
        }
    }
}
