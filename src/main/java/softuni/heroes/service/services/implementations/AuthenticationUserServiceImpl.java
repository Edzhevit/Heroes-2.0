package softuni.heroes.service.services.implementations;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import softuni.heroes.service.services.AuthenticationUserService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationUserServiceImpl implements AuthenticationUserService {

    @Override
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public List<String> getRoles() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
