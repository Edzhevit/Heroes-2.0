package softuni.heroes.service.models.hero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.heroes.data.models.Gender;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroCreateServiceModel {

    private String name;
    private Gender gender;
}
