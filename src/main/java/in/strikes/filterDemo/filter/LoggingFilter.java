package in.strikes.filterDemo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.LogRecord;

@Component
public class LoggingFilter implements Filter, LoggingFilters {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        long requestTime = System.currentTimeMillis();


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestId = UUID.randomUUID().toString();

        httpResponse.setHeader("X-Request-Id",requestId);

        System.out.println(requestId);
        System.out.println("Incomming request " + httpRequest.getMethod() + httpRequest.getRequestURI());


        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - requestTime;

        System.out.println("The API time taken : " + duration);

        System.out.println("Response statues " + httpResponse.getStatus());


    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}