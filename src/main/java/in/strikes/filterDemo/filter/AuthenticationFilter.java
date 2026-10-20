
package in.strikes.filterDemo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        String token = httpRequest.getHeader("token");

        if (token == null || !token.equals("1234")) {
            httpResponse.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );
            return;
        }
        String apikey = httpRequest.getHeader("x-api-key");

        if (apikey == null || !apikey.equals("secretkey")) {
            httpResponse.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write("{\n" +
                    "    \"nessage\":\"Empty api key\"\n" +
                    "}");
            return;
        }



        chain.doFilter(request, response);
    }
}
