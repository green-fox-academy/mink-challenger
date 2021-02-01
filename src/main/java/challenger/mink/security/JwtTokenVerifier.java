package challenger.mink.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.DecodingException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return Stream.of("/", "/login", "/register", "/main", "/verify/{uuid}", "verify/**")
        .anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
  }

  @SneakyThrows
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

    try {
      String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
      Jws<Claims> claimsJws = Jwts
          .parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token);

      Claims body = claimsJws.getBody();
      String username = body.getSubject();
      var authorities = (List<Map<String, String>>) body.get("authorities");

      Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
          .map(m -> new SimpleGrantedAuthority(m.get("authority")))
          .collect(Collectors.toSet());

      Authentication authentication =
          new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (NullPointerException | MalformedJwtException | DecodingException e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
    filterChain.doFilter(request, response);
  }

}
