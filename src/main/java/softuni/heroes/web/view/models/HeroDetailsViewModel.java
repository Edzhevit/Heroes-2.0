package softuni.heroes.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.heroes.data.models.Gender;

@Getter
@Setter
@NoArgsConstructor
public class HeroDetailsViewModel {

    private String name;
    private Gender gender;
    private Integer level;
    private Integer stamina;
    private Integer strength;
    private Integer attack;
    private Integer defence;

    private HeroItemViewModel weapon;
    private HeroItemViewModel pads;
    private HeroItemViewModel gauntlets;
    private HeroItemViewModel pauldrons;
    private HeroItemViewModel helmet;
}
