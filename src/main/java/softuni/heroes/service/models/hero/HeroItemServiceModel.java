package softuni.heroes.service.models.hero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroItemServiceModel {

    private String name;
    private Integer stamina;
    private Integer strength;
    private Integer attack;
    private Integer defence;
}
