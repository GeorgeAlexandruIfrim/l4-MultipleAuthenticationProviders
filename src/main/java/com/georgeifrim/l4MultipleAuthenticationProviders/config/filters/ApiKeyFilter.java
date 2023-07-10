package com.georgeifrim.l4MultipleAuthenticationProviders.config.filters;


import com.georgeifrim.l4MultipleAuthenticationProviders.config.authentications.ApiKeyAuthentication;
import com.georgeifrim.l4MultipleAuthenticationProviders.config.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import java.io.IOException;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {
    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var manager = new CustomAuthenticationManager(key);
        var requestKey = request.getHeader("key");

        if("null".equals(requestKey) || requestKey==null){
            filterChain.doFilter(request,response);
        }
        var auth = new ApiKeyAuthentication(requestKey);

        var a = manager.authenticate(auth);

            if(a.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request, response);
            }

    }
}
