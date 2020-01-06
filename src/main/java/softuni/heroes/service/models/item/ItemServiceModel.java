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
public class ItemServiceModel {

    private String id;
    private String name;
    private Integer attack;
    private Integer defence;
    private Integer stamina;
    private Integer strength;
    private Slot slot;
    private boolean isOwned;
}
