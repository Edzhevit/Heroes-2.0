package softuni.heroes.service.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.heroes.data.models.Hero;
import softuni.heroes.data.models.User;
import softuni.heroes.data.repositories.HeroRepository;
import softuni.heroes.data.repositories.UserRepository;
import softuni.heroes.service.models.hero.HeroCreateServiceModel;
import softuni.heroes.service.services.HeroesService;
import softuni.heroes.service.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final HeroesService heroesService;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(HeroesService heroesService, UserRepository userRepository) {
        this.heroesService = heroesService;
        this.userRepository = userRepository;
    }

    @Override
    public void createHeroForUser(String username, HeroCreateServiceModel heroServiceModel) throws Exception {

        User user = this.userRepository.findByUsername(username);
        if (user.getEntity() != null){
            throw new Exception("User already has a hero");
        }

        Hero hero = heroesService.create(heroServiceModel);
        user.setEntity(hero);
        userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        Set<GrantedAuthority> authorities = new HashSet<>(user.getAuthorities());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
