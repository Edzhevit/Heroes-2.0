package softuni.heroes.web.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.heroes.data.models.Slot;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseModel {

    private String id;
    private String name;
    private Slot slot;
    private Integer stamina;
    private Integer strength;
    private Integer attack;
    private Integer defence;
    private boolean isOwned;
}
