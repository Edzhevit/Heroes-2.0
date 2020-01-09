package softuni.heroes.web.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateRequestModel {

    private String name;
    private String slot;
    private Integer stamina;
    private Integer strength;
    private Integer attack;
    private Integer defence;
}
