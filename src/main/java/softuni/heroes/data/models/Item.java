package softuni.heroes.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.heroes.data.models.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity {

    @Column
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private Slot slot;
    @Column
    private Integer stamina;
    @Column
    private Integer strength;
    @Column
    private Integer attack;
    @Column
    private Integer defence;
    @ManyToMany(mappedBy = "items")
    private List<Hero> heroes;
}
