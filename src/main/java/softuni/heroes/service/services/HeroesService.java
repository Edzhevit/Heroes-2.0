package softuni.heroes.service.services;

import softuni.heroes.data.models.Hero;
import softuni.heroes.service.models.hero.HeroCreateServiceModel;
import softuni.heroes.service.models.hero.HeroDetailsServiceModel;

import java.util.List;

public interface HeroesService {

    HeroDetailsServiceModel getByName(String name);

    Hero create(HeroCreateServiceModel hero);

    boolean areThereOpponents();

    List<HeroDetailsServiceModel> getOpponents(String heroName);

    String getWinner(HeroDetailsServiceModel player1, HeroDetailsServiceModel player2);

    void levelUp(Hero winner);

    HeroDetailsServiceModel getByUsername(String username);

    void levelUpHeroes();
}
