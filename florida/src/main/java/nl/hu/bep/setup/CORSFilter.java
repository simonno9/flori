package nl.hu.bep.setup;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Log the request to verify the filter is being called
        System.out.println("CORSFilter: " + httpRequest.getMethod() + " request to " + httpRequest.getRequestURI());

        // Additional logging for request details
        logRequestDetails(httpRequest);

        // Adjust the allowed origin to match your frontend's origin
        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Requested-With, Authorization");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        // Handle preflight requests
        if ("OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(request, response);
    }

    private void logRequestDetails(HttpServletRequest request) {
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request Protocol: " + request.getProtocol());
        System.out.println("Request Context Path: " + request.getContextPath());
        System.out.println("Request Query String: " + request.getQueryString());
        System.out.println("Request Remote Addr: " + request.getRemoteAddr());
        System.out.println("Request Remote Host: " + request.getRemoteHost());
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
