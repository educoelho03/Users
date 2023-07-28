package br.com.project.crud.security;

import br.com.project.crud.model.User;
import br.com.project.crud.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        var token = this.recoverToken(request);
        if(token != null){
            var login = tokenService.validateToken(token);
            User user = userRepository.findByLogin(login);


            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authoritzation");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
