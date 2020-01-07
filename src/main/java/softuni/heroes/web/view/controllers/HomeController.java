package softuni.heroes.web.view.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.heroes.service.services.AuthenticationUserService;
import softuni.heroes.service.services.HeroesService;
import softuni.heroes.web.base.BaseController;
import softuni.heroes.web.view.models.HeroHomeModel;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    private final HeroesService heroesService;
    private final ModelMapper modelMapper;
    private final AuthenticationUserService authenticationUserService;

    @Autowired
    public HomeController(HeroesService heroesService, ModelMapper modelMapper, AuthenticationUserService authenticationUserService) {
        this.heroesService = heroesService;
        this.modelMapper = modelMapper;
        this.authenticationUserService = authenticationUserService;
    }

    @GetMapping("/")
    public String getIndex(){
        return "home/index.html";
    }

    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView) {
        List<String> authorities = authenticationUserService.getRoles();
        modelAndView.setViewName("home/home");
        boolean condition = heroesService.areThereOpponents();

//        List<HeroHomeModel> heroes = heroesService.getOpponents(getHeroName(session))
//                .stream()
//                .map(hero -> modelMapper.map(hero, HeroHomeModel.class))
//                .collect(Collectors.toList());
//        modelAndView.addObject("heroes", heroes);
//        modelAndView.addObject("condition", condition);
        return modelAndView;
    }

}
