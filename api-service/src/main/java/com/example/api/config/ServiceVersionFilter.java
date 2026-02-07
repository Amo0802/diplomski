package com.example.api.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServiceVersionFilter implements Filter {

    @Value("${service.version:unknown}")
    private String serviceVersion;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (response instanceof HttpServletResponse httpResponse) {
            httpResponse.setHeader("X-Service-Version", serviceVersion);
        }

        chain.doFilter(request, response);
    }
}