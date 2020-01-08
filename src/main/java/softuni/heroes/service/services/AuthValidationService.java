package softuni.heroes.service.services;

import softuni.heroes.service.models.auth.RegisterUserServiceModel;

public interface AuthValidationService {

    boolean isValid(RegisterUserServiceModel user);
}
