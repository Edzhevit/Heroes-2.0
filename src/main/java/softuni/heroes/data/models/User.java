package softuni.heroes.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import softuni.heroes.data.models.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    public User(){
        authorities = new HashSet<>();
    }

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @OneToOne(mappedBy = "user")
    private Hero entity;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities;

    @Column
    @Transient
    private boolean isAccountNonExpired;

    @Column
    @Transient
    private boolean isAccountNonLocked;

    @Column
    @Transient
    private boolean isCredentialsNonExpired;

    @Column
    @Transient
    private boolean isEnabled;
}


