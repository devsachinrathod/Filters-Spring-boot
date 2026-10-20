package in.strikes.filterDemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.util.logging.LogRecord;

public interface LoggingFilters {
    void doFilter(ServletRequest request,
                  ServletResponse response,
                  FilterChain chain)
            throws IOException, ServletException;

    boolean isLoggable(LogRecord record);
}
