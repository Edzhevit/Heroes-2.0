package softuni.heroes.service.services;

import java.util.List;

public interface AuthenticationUserService {
    String getUsername();

    List<String> getRoles();
}
