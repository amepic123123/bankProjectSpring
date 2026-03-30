package com.example.demo.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.util.ArrayList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
public class JwtFilter extends OncePerRequestFilter{
    
    
    private JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        final String jwtToken = authHeader.substring(7);

        try{
            String email = jwtService.extractEmail(jwtToken);
            Long userId = jwtService.extractUserId(jwtToken);
            
            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            request.setAttribute("userId", userId);
        
    
    }catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
        return;
    }

        filterChain.doFilter(request, response);

}

}