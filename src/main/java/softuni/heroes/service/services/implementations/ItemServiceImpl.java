package softuni.heroes.service.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.heroes.data.models.Hero;
import softuni.heroes.data.models.Item;
import softuni.heroes.data.repositories.HeroRepository;
import softuni.heroes.data.repositories.ItemRepository;
import softuni.heroes.service.models.item.ItemCreateServiceModel;
import softuni.heroes.service.models.item.ItemServiceModel;
import softuni.heroes.service.services.ItemValidationService;
import softuni.heroes.service.services.ItemsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemsService {

    private final ItemRepository itemRepository;
    private final HeroRepository heroRepository;
    private final ItemValidationService itemValidationService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, HeroRepository heroRepository, ItemValidationService itemValidationService, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.heroRepository = heroRepository;
        this.itemValidationService = itemValidationService;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ItemServiceModel> getItemsForUser(String username) {
        return itemRepository.findAll()
                .stream()
                .map(item -> {
                    ItemServiceModel serviceModel = modelMapper.map(item, ItemServiceModel.class);
                    if (item.getHeroes() != null){
                        Hero hero = item.getHeroes()
                                .stream()
                                .filter(h -> h.getUser().getUsername().equals(username))
                                .findAny()
                                .orElse(null);
                        serviceModel.setOwned(hero != null);
                    }
                    return serviceModel;
                }).collect(Collectors.toList());
    }

    @Override
    public void addToUserById(String id, String username) {
        Optional<Hero> heroResult = heroRepository.getByUserUsername(username);
        if (heroResult.isEmpty()){
            throw new NullPointerException("User does not have a hero");
        }

        Optional<Item> itemResult = itemRepository.findById(id);
        if (itemResult.isEmpty()){
            throw new NullPointerException("Item does not exist");
        }

        Hero hero = heroResult.get();
        Item item = itemResult.get();

        boolean hasItem = false;
        for (Item heroItem : hero.getItems()) {
            if (heroItem.getSlot() == item.getSlot()){
                hasItem = true;
                break;
            }
        }

        if (!hasItem){
            hero.getItems().add(item);
            hero.setAttack(hero.getAttack() + item.getAttack());
            hero.setDefence(hero.getDefence() + item.getDefence());
            hero.setStrength(hero.getStrength() + item.getStrength());
            hero.setStamina(hero.getStamina() + item.getStamina());
            heroRepository.saveAndFlush(hero);
        }

    }

    @Override
    public void create(ItemCreateServiceModel serviceModel) {
        if (!this.itemValidationService.isValid(serviceModel)){
            throw new RuntimeException("Hero is invalid");
        }

        Item item = modelMapper.map(serviceModel, Item.class);
        itemRepository.saveAndFlush(item);
    }

    @Override
    public List<ItemServiceModel> getAll() {
        return itemRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, ItemServiceModel.class))
                .collect(Collectors.toList());
    }
}
