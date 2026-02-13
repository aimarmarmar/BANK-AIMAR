package id.bni.nasabah.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class RequestIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = java.util.UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();
        try {
            MDC.put("requestId", requestId);
            log.info("START {} - {}", request.getMethod(), request.getRequestURI());
            response.setHeader("X-Request-ID", requestId);
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            log.info("END {} - {} - {} - {} ms", request.getMethod(), request.getRequestURI(), response.getStatus(), duration);
            MDC.clear();
        }
    }
}
