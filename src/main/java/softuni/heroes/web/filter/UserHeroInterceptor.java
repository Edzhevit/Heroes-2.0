package softuni.heroes.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import softuni.heroes.error.HeroNotFoundException;
import softuni.heroes.service.models.hero.HeroDetailsServiceModel;
import softuni.heroes.service.services.AuthenticationUserService;
import softuni.heroes.service.services.HeroesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserHeroInterceptor implements HandlerInterceptor {

    private final AuthenticationUserService authenticationUserService;
    private final HeroesService heroesService;

    @Autowired
    public UserHeroInterceptor(AuthenticationUserService authenticationUserService, HeroesService heroesService) {
        this.authenticationUserService = authenticationUserService;
        this.heroesService = heroesService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String username = this.authenticationUserService.getUsername();
        try {
            HeroDetailsServiceModel hero = heroesService.getByUsername(username);
            request.getSession().setAttribute("heroName", hero.getName());
        } catch (HeroNotFoundException ex){
            //do nothing
        }
    }
}
