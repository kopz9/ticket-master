package br.com.kopz.ticketmaster.filters;

import br.com.kopz.ticketmaster.domain.entities.User;
import br.com.kopz.ticketmaster.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter {

  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Jwt jwt) {
      UUID keycloakId = UUID.fromString(jwt.getSubject());

      if (!userRepository.existsById(keycloakId)) {
        var user = new User();
        user.setId(keycloakId);
        user.setName(jwt.getClaimAsString("kopz"));
        user.setEmail(jwt.getClaimAsString("email"));

        userRepository.save(user);
      }
    }

    filterChain.doFilter(request, response);



  }
}
