//package com.tcsKart.UserService.filter;
//
//import com.tcsKart.UserService.util.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    @Qualifier("customerDetailsService")
//    private UserDetailsService customerDetailsService;
//
//    @Autowired
//    @Qualifier("adminDetailsService")
//    private UserDetailsService adminDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String path = request.getRequestURI();
//
//        // 🛡️ Skip JWT filter for login and register endpoints
//        if (path.equals("/customer/login") || path.equals("/customer/register") ||
//                path.equals("/admin/login") || path.equals("/admin/register")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String authHeader = request.getHeader("Authorization");
//
//        String token=null;
//        String username=null;
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            token = authHeader.substring(7);
//
//            try{
//                username = jwtUtil.validateTokenAndRetrieveSubject(token);
//            } catch (Exception e) {
//                // Optional: log token validation error
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                return; // ❌ Prevent infinite looping
//            }
//
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//                // 👇 Decide logic here: admin vs customer
//                UserDetails userDetails;
//
////                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                if (request.getRequestURI().startsWith("/admin")) {
//                    userDetails = adminDetailsService.loadUserByUsername(username);
//                } else {
//                    userDetails = customerDetailsService.loadUserByUsername(username);
//                }
//
//                if (jwtUtil.validateToken(token, userDetails)) {
//                    UsernamePasswordAuthenticationToken authToken =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//        }
//
//        filterChain.doFilter(request, response);
//
//    }
//
//}
//
