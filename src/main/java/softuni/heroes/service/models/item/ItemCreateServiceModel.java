package softuni.heroes.service.models.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.heroes.data.models.Slot;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateServiceModel {

    private String name;
    private Slot slot;
    private Integer attack;
    private Integer defence;
    private Integer stamina;
    private Integer strength;
}
