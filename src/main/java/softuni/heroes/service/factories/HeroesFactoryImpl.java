package softuni.heroes.service.factories;

import softuni.heroes.config.annotations.Factory;
import softuni.heroes.data.models.Gender;
import softuni.heroes.data.models.Hero;

import static softuni.heroes.service.factories.HeroesConstants.*;


@Factory
public class HeroesFactoryImpl implements HeroesFactory{

    @Override
    public Hero create(String name, Gender gender) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setGender(gender);
        hero.setAttack(INITIAL_ATTACK);
        hero.setDefence(INITIAL_DEFENCE);
        hero.setLevel(INITIAL_LEVEL);
        hero.setStamina(INITIAL_STAMINA);
        hero.setStrength(INITIAL_STRENGTH);

        return hero;
    }
}
