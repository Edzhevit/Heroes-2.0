package softuni.heroes.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.heroes.base.TestBase;
import softuni.heroes.data.models.Hero;
import softuni.heroes.data.models.Item;
import softuni.heroes.data.models.Slot;
import softuni.heroes.data.models.User;
import softuni.heroes.data.repositories.HeroRepository;
import softuni.heroes.data.repositories.ItemRepository;
import softuni.heroes.service.models.item.ItemCreateServiceModel;
import softuni.heroes.service.models.item.ItemServiceModel;
import softuni.heroes.service.services.ItemValidationService;
import softuni.heroes.service.services.ItemsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ItemServiceTest extends TestBase {

    List<Item> items;

    @MockBean
    ItemRepository itemRepository;
    @MockBean
    HeroRepository heroRepository;
    @MockBean
    ItemValidationService validationService;

    @Autowired
    ItemsService service;

    @Override
    protected void beforeEach(){
        items = new ArrayList<>();

        Mockito.when(itemRepository.findAll())
                .thenReturn(items);
    }

    @Test
    void getItemsForUser_whenNoItems_shouldReturnEmptyList() {
        items.clear();

        List<ItemServiceModel> actualItems = service.getItemsForUser("username");

        assertEquals(0, actualItems.size());
    }

    @Test
    void getItemsForUser_whenItemsButNotForUser_shouldReturnItemsWithoutOwned() {
        items.clear();
        items.addAll(getItems());

        List<ItemServiceModel> actualItems = service.getItemsForUser("username");

        assertEquals(items.size(), actualItems.size());
        boolean hasNoOwned = actualItems.stream()
                .filter(ItemServiceModel::isOwned)
                .findAny()
                .isEmpty();

        assertTrue(hasNoOwned);
    }

    @Test
    void getItemsForUser_whenItemsAndOneForUser_shouldReturnItemsWithOneForUser() {
        items.clear();
        items.addAll(getItems());

        Hero hero = new Hero();
        User user = new User();
        user.setUsername("username");
        hero.setUser(user);

        items.get(0)
                .setHeroes(new ArrayList<>());
        items.get(0)
                .getHeroes()
                .add(hero);

        List<ItemServiceModel> actualItems = service.getItemsForUser(user.getUsername());

        assertEquals(items.size(), actualItems.size());

        List<ItemServiceModel> itemsForUser = actualItems.stream()
                .filter(ItemServiceModel::isOwned)
                .collect(Collectors.toList());

        assertEquals(1, itemsForUser.size());
        assertEquals(items.get(0).getId(), itemsForUser.get(0).getId());
    }

    @Test
    void addToUserById_whenItemDoesNotExist_shouldThrowException() {
        items.clear();
        String username = "username";
        Mockito.when(itemRepository.findById("1"))
                .thenReturn(Optional.of(new Item()));

        assertThrows(
                NullPointerException.class,
                () -> service.addToUserById("1", username));

    }

    @Test
    void addToUserById_whenUserHasNoHero_shouldThrowException() {
        items.clear();
        items.addAll(getItems());

        String username = "username";
        Mockito.when(heroRepository.getByUserUsername(username))
                .thenReturn(Optional.empty());

        assertThrows(
                NullPointerException.class,
                () -> service.addToUserById(items.get(0).getId(), username));
    }

    @Test
    void addToUserById_whenUserHasHeroAndItemExists_shouldBeSaved() {
        items.clear();
        items.addAll(getItems());

        String username = "username";
        String id = "id";
        Hero hero = new Hero();
        hero.setItems(new ArrayList<>());
        Mockito.when(heroRepository.getByUserUsername(username))
                .thenReturn(Optional.of(hero));

        Mockito.when(itemRepository.findById(id))
                .thenReturn(Optional.of(items.get(0)));

        service.addToUserById(items.get(0).getId(), username);

        ArgumentCaptor<Hero> argument = ArgumentCaptor.forClass(Hero.class);
        Mockito.verify(heroRepository).saveAndFlush(argument.capture());

        Hero theHero = argument.getValue();
        assertEquals(1, theHero.getItems().size());
        assertEquals(items.get(0).getId(), theHero.getItems().get(0).getId());
    }

    @Test
    void create_whenItemIsValid_shouldThrow() {
        ItemCreateServiceModel item = getItemCreateModel();

        Mockito.when(validationService.isValid(item))
                .thenReturn(false);

        assertThrows(
                RuntimeException.class,
                () -> service.create(item));
    }

    @Test
    void create_whenAllIsValid_shouldThrow() {
        ItemCreateServiceModel itemModel = getItemCreateModel();

        Mockito.when(validationService.isValid(itemModel))
                .thenReturn(true);

        service.create(itemModel);


        ArgumentCaptor<Item> argument = ArgumentCaptor.forClass(Item.class);
        Mockito.verify(itemRepository).saveAndFlush(argument.capture());

        Item item = argument.getValue();
        assertNotNull(item);
    }

    private ItemCreateServiceModel getItemCreateModel() {
        return new ItemCreateServiceModel("Item 1", Slot.PADS, 1, 2, 3, 4);
    }

    private List<Item> getItems() {
        return getItems(2);
    }

    private List<Item> getItems(int count) {
        return IntStream.range(0, count)
                .map(x -> x + 1)
                .mapToObj(id -> {
                    Item item = new Item();
                    item.setId(String.valueOf(id));
                    item.setName("Item " + id);
                    item.setSlot(Slot.PADS);
                    item.setStamina(1);
                    item.setAttack(2 * id);
                    item.setDefence(3 * id);
                    item.setStrength(5 * id);
                    return item;
                })
                .collect(Collectors.toList());
    }
}
