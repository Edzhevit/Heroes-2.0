package softuni.heroes.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.heroes.base.TestBase;
import softuni.heroes.data.models.Gender;
import softuni.heroes.data.models.Hero;
import softuni.heroes.data.models.User;
import softuni.heroes.data.repositories.UserRepository;
import softuni.heroes.service.models.hero.HeroCreateServiceModel;
import softuni.heroes.service.services.UserService;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest extends TestBase {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService service;

    @Test
    public void createHeroForUser_whenUserExistsAndDoesNotHaveAHero_shouldCreateHeroForUser() throws Exception {
        User user = new User();
        user.setUsername("Pesho");
        String heroName = "Gosho";
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        HeroCreateServiceModel heroToCreate = new HeroCreateServiceModel(heroName, Gender.MALE);
        service.createHeroForUser(user.getUsername(), heroToCreate);

        assertEquals(heroName, user.getEntity().getName());
    }

    public void createHeroForUser_whenUserDoesNotExist_shouldThrowException(){}

    @Test
    public void createHeroForUser_whenUserExistsAndHasAHero_shouldThrowException(){
        User user = new User();
        user.setUsername("Pesho");
        user.setEntity(new Hero());
        String heroName = "Gosho";
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        HeroCreateServiceModel heroToCreate = new HeroCreateServiceModel(heroName, Gender.MALE);

        assertThrows(Exception.class, () ->  service.createHeroForUser(user.getUsername(), heroToCreate));
    }
}
