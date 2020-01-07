package softuni.heroes.service.factories;

import softuni.heroes.data.models.Gender;
import softuni.heroes.data.models.Hero;

public interface HeroesFactory {
    Hero create(String name, Gender gender);
}
