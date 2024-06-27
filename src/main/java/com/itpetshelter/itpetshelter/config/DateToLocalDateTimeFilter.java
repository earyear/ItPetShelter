package com.itpetshelter.itpetshelter.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateToLocalDateTimeFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String dateParam = request.getParameter("Rdate2");
        if (dateParam != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime localDateTime = LocalDateTime.parse(dateParam, formatter);
                request.setAttribute("localDateTime", localDateTime);
            } catch (Exception e) {
                // 예외 처리 로직 추가
            }
        }
        filterChain.doFilter(request, response);
    }
}