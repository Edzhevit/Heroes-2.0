package softuni.heroes.service.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.heroes.data.repositories.UserRepository;
import softuni.heroes.service.models.auth.RegisterUserServiceModel;
import softuni.heroes.service.services.AuthValidationService;

@Service
public class AuthValidationServiceImpl implements AuthValidationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(RegisterUserServiceModel user) {
        return this.isEmailValid(user.getEmail()) &&
                this.arePasswordsValid(user.getPassword(), user.getConfirmPassword()) &&
                this.isUsernameFree(user.getUsername());
    }

    private boolean isUsernameFree(String username) {
        return !this.userRepository.existsByUsername(username);
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        return true;
    }
}
