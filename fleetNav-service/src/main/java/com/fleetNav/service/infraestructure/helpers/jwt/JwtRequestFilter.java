package com.fleetNav.service.infraestructure.helpers.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
// import java.util.Base64;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final String SECRET_KEY =
    "lD9QW7Lg8LK5p0LwRpSFS8BGmOkgT5O7PKbV5Ic8JRY=";
  private final String HEADER_STRING = "Authorization";
  private final String TOKEN_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    String header = request.getHeader(HEADER_STRING);

    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = getAuthentication(
      request
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(
    HttpServletRequest request
  ) {
    String jwt = request.getHeader(HEADER_STRING);
    System.out.println("JWT: " + jwt);
    if (jwt != null) {
      // Parse the jwt
      Claims claims = Jwts
        .parserBuilder()
        .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)) // Utiliza la clave directamente
        .build()
        .parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
        .getBody();

      if (claims != null) {
        return new UsernamePasswordAuthenticationToken(
          null,
          null,
          Collections.emptyList()
        );
      }
      return null;
    }
    return null;
  }
}
