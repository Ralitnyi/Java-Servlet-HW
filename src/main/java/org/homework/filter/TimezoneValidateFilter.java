package org.homework.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String timezone = servletRequest.getParameter("timezone");
        if (timezone == null || timezone.isBlank()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        try {
            ZoneId.of(timezone);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (DateTimeException exception) {
            servletResponse.getWriter().write("Invalid timezone");
        }
    }
}
