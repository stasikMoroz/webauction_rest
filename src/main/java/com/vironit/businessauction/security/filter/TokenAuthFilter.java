package com.vironit.businessauction.security.filter;

import com.vironit.businessauction.security.token.TokenAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //String token = request.getHeader("Token");

        String token = request.getParameter("token");//request.getHeaders()
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        System.out.println(token);
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        if (token == null) {
            tokenAuthentication.setAuthenticated(false);
        } else {
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
