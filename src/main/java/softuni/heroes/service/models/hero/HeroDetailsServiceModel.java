package softuni.heroes.service.models.hero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.heroes.data.models.Gender;

@Getter
@Setter
@NoArgsConstructor
public class HeroDetailsServiceModel {

    private String name;
    private Gender gender;
    private Integer level;
    private Integer stamina;
    private Integer strength;
    private Integer attack;
    private Integer defence;

    private HeroItemServiceModel weapon;
    private HeroItemServiceModel pads;
    private HeroItemServiceModel gauntlets;
    private HeroItemServiceModel pauldrons;
    private HeroItemServiceModel helmet;
}
