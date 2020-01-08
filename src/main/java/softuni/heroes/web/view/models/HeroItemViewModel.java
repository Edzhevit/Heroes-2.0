package softuni.heroes.web.view.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HeroItemViewModel {

    private String name;
    private Integer stamina;
    private Integer strength;
    private Integer attack;
    private Integer defence;
}
