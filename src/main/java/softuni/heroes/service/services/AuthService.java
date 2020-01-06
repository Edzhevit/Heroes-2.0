package softuni.heroes.service.services;

import softuni.heroes.service.models.auth.LoginUserServiceModel;
import softuni.heroes.service.models.auth.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel user);

    LoginUserServiceModel login(RegisterUserServiceModel userServiceModel) throws Exception;
}
