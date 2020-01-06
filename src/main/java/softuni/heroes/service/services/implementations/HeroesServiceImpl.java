package softuni.heroes.service.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.heroes.data.models.Hero;
import softuni.heroes.data.models.Item;
import softuni.heroes.data.models.Slot;
import softuni.heroes.data.repositories.HeroRepository;
import softuni.heroes.error.HeroNotFoundException;
import softuni.heroes.service.factories.HeroesFactory;
import softuni.heroes.service.models.hero.HeroCreateServiceModel;
import softuni.heroes.service.models.hero.HeroDetailsServiceModel;
import softuni.heroes.service.models.hero.HeroItemServiceModel;
import softuni.heroes.service.services.HeroesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeroesServiceImpl implements HeroesService {

    private final HeroRepository heroRepository;
    private final HeroesFactory heroesFactory;
    private final ModelMapper modelMapper;

    @Autowired
    public HeroesServiceImpl(HeroRepository heroRepository, HeroesFactory heroesFactory, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.heroesFactory = heroesFactory;
        this.modelMapper = modelMapper;
    }

    @Override
    public HeroDetailsServiceModel getByName(String name) {
        Optional<Hero> heroResult = heroRepository.getByNameIgnoreCase(name);
        if (heroResult.isEmpty()){
            throw new HeroNotFoundException("Hero with such name does not exist");
        }

        Hero hero = heroResult.get();
        HeroDetailsServiceModel serviceModel = this.modelMapper.map(hero, HeroDetailsServiceModel.class);
        serviceModel.setWeapon(getItemBySlot(hero.getItems(), Slot.WEAPON));
        serviceModel.setGauntlets(getItemBySlot(hero.getItems(), Slot.GAUNTLETS));
        serviceModel.setHelmet(getItemBySlot(hero.getItems(), Slot.HELMET));
        serviceModel.setPads(getItemBySlot(hero.getItems(), Slot.PADS));
        serviceModel.setPauldrons(getItemBySlot(hero.getItems(), Slot.PAULDRON));

        return serviceModel;
    }

    @Override
    public Hero create(HeroCreateServiceModel serviceModel) {
        Hero hero = heroesFactory.create(serviceModel.getName(), serviceModel.getGender());
        heroRepository.saveAndFlush(hero);
        return hero;
    }

    @Override
    public boolean areThereOpponents() {
        return heroRepository.count() > 1;
    }

    @Override
    public List<HeroDetailsServiceModel> getOpponents(String heroName) {
        return heroRepository.findAll()
                .stream()
                .filter(hero -> !hero.getName().equals(heroName))
                .map(hero -> modelMapper.map(hero, HeroDetailsServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String getWinner(HeroDetailsServiceModel player1, HeroDetailsServiceModel player2) {
        int player1Dmg = (player1.getAttack() + player1.getStrength()) * 4 - (player2.getDefence() + player2.getStamina()) * 2;
        int player2Dmg = (player2.getAttack() + player2.getStrength()) * 4 - (player1.getDefence() + player1.getStamina()) * 2;
        if (player1Dmg > player2Dmg){
            levelUp(heroRepository.getByNameIgnoreCase(player1.getName())
                    .orElseThrow(() -> new HeroNotFoundException("No such hero")));
            return player1.getName();
        } else {
            levelUp(heroRepository.getByNameIgnoreCase(player2.getName())
                    .orElseThrow(() -> new HeroNotFoundException("No such hero")));
            return player2.getName();
        }
    }

    @Override
    public void levelUp(Hero winner) {
        heroRepository.save(levelUpHero(winner));
    }

    @Override
    public HeroDetailsServiceModel getByUsername(String username) {
        Optional<Hero> hero = this.heroRepository.getByUserUsername(username);
        if (hero.isEmpty()){
            throw new HeroNotFoundException("Not Such Hero");
        }

        return modelMapper.map(hero, HeroDetailsServiceModel.class);
    }

    @Override
    public void levelUpHeroes() {
        List<Hero> heroes = heroRepository.findAll()
                .stream()
                .map(this::levelUpHero)
                .collect(Collectors.toList());
        heroRepository.saveAll(heroes);
    }

    private Hero levelUpHero(Hero hero){
        hero.setLevel(hero.getLevel() + 1);
        hero.setStamina(hero.getStamina() + 5);
        hero.setStrength(hero.getStrength() + 5);
        return hero;
    }

    private HeroItemServiceModel getItemBySlot(List<Item> items, Slot slot){
        Optional<Item> item = items.stream()
                .filter(x -> x.getSlot() == slot)
                .findFirst();

        return item.isPresent() ? modelMapper.map(item, HeroItemServiceModel.class) : null;
    }
}
