package softuni.heroes.service.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.heroes.data.models.User;
import softuni.heroes.data.repositories.UserRepository;
import softuni.heroes.service.models.auth.LoginUserServiceModel;
import softuni.heroes.service.models.auth.RegisterUserServiceModel;
import softuni.heroes.service.services.AuthService;
import softuni.heroes.service.services.HashingService;
import softuni.heroes.service.services.AuthValidationService;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthValidationService authValidationService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final HashingService hashingService;

    @Autowired
    public AuthServiceImpl(AuthValidationService authValidationService, UserRepository userRepository, ModelMapper modelMapper, HashingService hashingService) {
        this.authValidationService = authValidationService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        if (!this.authValidationService.isValid(model)){
            return;
        }

        User user = this.modelMapper.map(model, User.class);
        user.setPassword(this.hashingService.hash(user.getPassword()));
        this.userRepository.save(user);

    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel userServiceModel) throws Exception {
        String hashPassword = this.hashingService.hash(userServiceModel.getPassword());

        return userRepository.findByUsernameAndPassword(userServiceModel.getUsername(), hashPassword)
                .map(user -> {
                    String heroName = user.getEntity() == null ? null : user.getEntity().getName();

                    return new LoginUserServiceModel(userServiceModel.getUsername(), heroName);
                }).orElseThrow(() -> new Exception("Invalid user"));
    }
}
