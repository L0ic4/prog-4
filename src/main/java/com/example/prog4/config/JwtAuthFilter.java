package com.example.prog4.config;

import com.example.prog4.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
@AllArgsConstructor

public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  private final UserInfoUserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    if (requestURI.equals("/login") || requestURI.equals("/signup") || requestURI.equals("/createuser") || requestURI.equals("/connectuser")) {
      // Si le chemin correspond à "/login" ou "/signup", continuer la chaîne de filtres sans authentification
      filterChain.doFilter(request, response);
      return;
    }

    String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;

    // Vérifier si le token est déjà présent dans l'en-tête
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      // Si le token n'est pas présent dans l'en-tête, essayer de le récupérer à partir des cookies
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        for (Cookie cookie : cookies) {
          if (cookie.getName().equals("JWT")) {
            token = cookie.getValue();
            break;
          }
        }
      }

      if (token != null) {
        // Si le token est récupéré à partir des cookies, le placer dans l'en-tête "Authorization"
        response.addHeader("Authorization", "Bearer " + token);
        // Extraire le nom d'utilisateur à partir du token
        username = jwtService.extractUsername(token);
      }
    } else {
      // Si le token est déjà présent dans l'en-tête, l'extraire et récupérer le nom d'utilisateur
      token = authHeader.substring(7);
      username = jwtService.extractUsername(token);
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      if (jwtService.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, null);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}