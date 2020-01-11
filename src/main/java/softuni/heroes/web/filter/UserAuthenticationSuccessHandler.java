package softuni.heroes.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import softuni.heroes.error.HeroNotFoundException;
import softuni.heroes.service.models.hero.HeroDetailsServiceModel;
import softuni.heroes.service.services.AuthenticationUserService;
import softuni.heroes.service.services.HeroesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthenticationUserService authenticationUserService;
    private final HeroesService heroesService;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    public UserAuthenticationSuccessHandler(AuthenticationUserService authenticationUserService, HeroesService heroesService) {
        super();
        this.authenticationUserService = authenticationUserService;
        this.heroesService = heroesService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        org.springframework.security.core.Authentication authentication) throws IOException, ServletException, IOException {
        String username = authenticationUserService.getUsername();
        try {
            HeroDetailsServiceModel hero = heroesService.getByUsername(username);
            httpServletRequest.getSession()
                    .setAttribute("heroName", hero.getName());
        } catch (HeroNotFoundException ex) {
            // do nothing
        }

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/home");
    }
}
