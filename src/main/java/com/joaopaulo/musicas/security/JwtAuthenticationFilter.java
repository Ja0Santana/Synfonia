package com.joaopaulo.musicas.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String email = jwtUtil.extractEmail(token);
            boolean isNotAuthenticated = SecurityContextHolder.getContext().getAuthentication() == null;

            if (email != null && isNotAuthenticated) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                if (jwtUtil.isTokenValid(token, email)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    // Renovação Sliding Session: Gerar um novo token para cada requisição válida
                    if (userDetails instanceof UsuarioDetails ud) {
                        String newToken = jwtUtil.generateAccessToken(
                                ud.getUsername(),
                                ud.getId(),
                                ud.getAuthorities().stream()
                                        .findFirst()
                                        .map(a -> a.getAuthority().replace("ROLE_", ""))
                                        .orElse("USER")
                        );
                        response.setHeader("New-Token", newToken);
                    }
                }
            }
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Token JWT inválido ou malformado: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"detalhe\": \"Token inválido ou expirado. Por favor, faça login novamente.\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
