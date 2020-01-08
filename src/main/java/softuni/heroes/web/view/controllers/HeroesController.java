package softuni.heroes.web.view.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.heroes.error.HeroNotFoundException;
import softuni.heroes.service.models.hero.HeroCreateServiceModel;
import softuni.heroes.service.models.hero.HeroDetailsServiceModel;
import softuni.heroes.service.services.HeroesService;
import softuni.heroes.service.services.UserService;
import softuni.heroes.web.base.BaseController;
import softuni.heroes.web.view.models.HeroCreateModel;
import softuni.heroes.web.view.models.HeroDetailsViewModel;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/heroes")
public class HeroesController extends BaseController {

    public final static String HERO_DETAILS_VIEW_NAME = "heroes/hero-details.html";

    private final HeroesService heroesService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroesController(HeroesService heroesService, UserService userService, ModelMapper modelMapper) {
        this.heroesService = heroesService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/details/{name}")
    public ModelAndView getHeroDetails(@PathVariable String name, ModelAndView modelAndView){
        HeroDetailsServiceModel serviceModel = heroesService.getByName(name);
        HeroDetailsViewModel viewModel = modelMapper.map(serviceModel, HeroDetailsViewModel.class);
        modelAndView.addObject("hero", viewModel);
        modelAndView.setViewName(HERO_DETAILS_VIEW_NAME);
        return modelAndView;
    }

    @GetMapping("/create")
    public String getHeroCreateForm() {
        return "heroes/create-hero.html";
    }

    @PostMapping("/create")
    public String createHero(@ModelAttribute HeroCreateModel hero, Principal principal){

        String username = principal.getName();

        HeroCreateServiceModel serviceModel = modelMapper.map(hero, HeroCreateServiceModel.class);

        try {
            userService.createHeroForUser(username, serviceModel);
            return "redirect:/heroes/details/" + hero.getName();
        } catch (Exception ex){
            return "redirect:/heroes/create";
        }
    }

    @GetMapping("/fight/{heroName}")
    public ModelAndView fight(@PathVariable String heroName, HttpSession session, ModelAndView modelAndView){
        modelAndView.setViewName("heroes/fight");
        HeroDetailsServiceModel currentHero = heroesService.getByName(getHeroName(session));
        HeroDetailsServiceModel opponent = heroesService.getByName(heroName);
        String winner = heroesService.getWinner(currentHero, opponent);
        modelAndView.addObject("currentHero", currentHero);
        modelAndView.addObject("opponent", opponent);
        modelAndView.addObject("winner", winner);

        return modelAndView;
    }

    @ExceptionHandler(HeroNotFoundException.class)
    public ModelAndView handleException(HeroNotFoundException exception){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
