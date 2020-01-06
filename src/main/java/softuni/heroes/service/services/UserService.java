package softuni.heroes.service.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import softuni.heroes.service.models.hero.HeroCreateServiceModel;

public interface UserService extends UserDetailsService {

    void createHeroForUser(String username, HeroCreateServiceModel hero) throws Exception;
}
