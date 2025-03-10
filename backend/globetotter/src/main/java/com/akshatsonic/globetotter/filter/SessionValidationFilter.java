package com.akshatsonic.globetotter.filter;

import com.akshatsonic.globetotter.service.SessionService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SessionValidationFilter implements Filter {

    private final SessionService sessionService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String sessionToken = httpRequest.getHeader("session-token");
        Long userId = Long.parseLong(httpRequest.getHeader("user-id"));
        String uri = httpRequest.getRequestURI();
        if(uri.equals("/auth/user") || uri.equals("/auth/login")){
            chain.doFilter(request, response);
            return;
        }
        if (sessionToken == null || userId == null ) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session token or User id not found in request");
            return;
        }
        if (!sessionService.isValid(sessionToken, userId)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid session token");
            return;
        }
        chain.doFilter(request, response);
    }
}
