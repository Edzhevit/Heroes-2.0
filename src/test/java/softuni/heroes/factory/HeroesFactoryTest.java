package softuni.heroes.factory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.heroes.base.TestBase;
import softuni.heroes.data.models.Gender;
import softuni.heroes.data.models.Hero;
import softuni.heroes.service.factories.HeroesFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softuni.heroes.service.factories.HeroesConstants.*;

public class HeroesFactoryTest extends TestBase {

    @Autowired
    HeroesFactory factory;

    @Test
    void create_withNameAndGender_ShouldReturnHeroWithDefaultProps(){
        String name = "hero";
        Gender gender = Gender.FEMALE;

        Hero hero = factory.create(name, gender);

        assertEquals(name, hero.getName());
        assertEquals(gender, hero.getGender());
        assertEquals(INITIAL_ATTACK, hero.getAttack());
        assertEquals(INITIAL_DEFENCE, hero.getDefence());
        assertEquals(INITIAL_STAMINA, hero.getStamina());
        assertEquals(INITIAL_STRENGTH, hero.getStrength());
    }

}
