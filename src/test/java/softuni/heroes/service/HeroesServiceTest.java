package softuni.heroes.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.heroes.base.TestBase;
import softuni.heroes.data.models.Hero;
import softuni.heroes.data.repositories.HeroRepository;
import softuni.heroes.error.HeroNotFoundException;
import softuni.heroes.service.models.hero.HeroDetailsServiceModel;
import softuni.heroes.service.services.HeroesService;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeroesServiceTest extends TestBase {

    @MockBean
    HeroRepository heroRepository;

    @Autowired
    HeroesService heroesService;

    @Test
    void getByName_whenHeroDoesNotExist_shouldThrowHeroNotFoundException(){
        String heroName = "Hero Name";
        Mockito.when(heroRepository.getByNameIgnoreCase(heroName))
                .thenReturn(Optional.empty());

        assertThrows(HeroNotFoundException.class, () -> heroesService.getByName(heroName));
    }

    @Test
    void getByName_whenHeroDoesExist_shouldReturnHero(){
        String heroName = "Hero Name";
        Hero hero = new Hero();
        hero.setName(heroName);
        hero.setItems(new ArrayList<>());

        Mockito.when(heroRepository.getByNameIgnoreCase(heroName))
                .thenReturn(Optional.of(hero));

        HeroDetailsServiceModel heroDetails = heroesService.getByName(heroName);
        assertEquals(hero.getName(), heroDetails.getName());
    }

}
