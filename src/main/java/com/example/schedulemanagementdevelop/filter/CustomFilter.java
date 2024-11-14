package com.example.schedulemanagementdevelop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class CustomFilter implements Filter {

    // TODO 고민해볼것
    private static final String[] WHITE_LIST = {"/users/login", "/users/signup", "/schedules", "/users"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String requestURI = httpServletRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpServletRequest.getSession(false);
            log.info(requestURI);

            if (session == null || session.getAttribute("email") == null) {
                throw new RuntimeException("로그인 해주세요");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
