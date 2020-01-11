package softuni.heroes.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import softuni.heroes.base.TestBase;
import softuni.heroes.data.models.Gender;
import softuni.heroes.data.models.Hero;
import softuni.heroes.data.repositories.HeroRepository;
import softuni.heroes.web.view.controllers.HeroesController;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
public class HeroesControllerTest extends TestBase {

    @MockBean
    HeroRepository heroRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getDetails_whenNoHeroWithName_shouldReturnErrorPageWith404() throws Exception {
        String heroName = "Pesho";
        mockMvc.perform(get("/heroes/details/" + heroName))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"));
    }

    @Test
    void getDetails_whenHeroWithName_shouldReturnHeroDetailsViewWith200() throws Exception {
        String heroName = "Pesho";
        Hero hero = new Hero();
        hero.setName(heroName);
        hero.setGender(Gender.MALE);
        hero.setItems(new ArrayList<>());
        Mockito.when(heroRepository.getByNameIgnoreCase(heroName))
                .thenReturn(Optional.of(hero));
        mockMvc.perform(get("/heroes/details/" + heroName))
                .andExpect(status().isOk())
                .andExpect(view().name(HeroesController.HERO_DETAILS_VIEW_NAME));
    }
}
