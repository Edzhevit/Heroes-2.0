package softuni.heroes.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.heroes.data.models.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "heroes")
@Getter
@Setter
@NoArgsConstructor
public class Hero extends BaseEntity {

    @Column(unique = true)
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private Integer level;
    @Column
    private Integer stamina;
    @Column
    private Integer strength;
    @Column
    private Integer attack;
    @Column
    private Integer defence;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "heroes_items",
            joinColumns = @JoinColumn(name = "hero_id"), inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
