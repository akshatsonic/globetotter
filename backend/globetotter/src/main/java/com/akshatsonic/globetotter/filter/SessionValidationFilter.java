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

import static com.akshatsonic.globetotter.Constants.SESSION_TOKEN_HEADER;
import static com.akshatsonic.globetotter.Constants.USER_ID_HEADER;

@Component
@RequiredArgsConstructor
public class SessionValidationFilter implements Filter {

    private final SessionService sessionService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        if(uri.equals("/auth/user") || uri.equals("/auth/login") || uri.equals("/ping")){
            chain.doFilter(request, response);
            return;
        }
        String sessionToken = httpRequest.getHeader(SESSION_TOKEN_HEADER);
        Long userId = Long.parseLong(httpRequest.getHeader(USER_ID_HEADER));

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
