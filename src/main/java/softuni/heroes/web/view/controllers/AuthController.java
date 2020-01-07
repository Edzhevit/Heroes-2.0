package softuni.heroes.web.view.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import softuni.heroes.service.models.auth.RegisterUserServiceModel;
import softuni.heroes.service.services.AuthService;
import softuni.heroes.web.view.models.UserRegisterModel;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String getLoginForm(@RequestParam(required = false) String error, Model model) {
        if (error != null){
            model.addAttribute("error", error);
        }
        return "auth/login.html";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        model.addAttribute("model", new UserRegisterModel());
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserRegisterModel model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "auth/register.html";
        }

        RegisterUserServiceModel userServiceModel = this.modelMapper.map(model, RegisterUserServiceModel.class);
        this.authService.register(userServiceModel);
        return "redirect:/login";
    }

}