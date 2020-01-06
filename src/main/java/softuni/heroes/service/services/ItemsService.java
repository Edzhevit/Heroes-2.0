package softuni.heroes.service.services;

import softuni.heroes.service.models.item.ItemCreateServiceModel;
import softuni.heroes.service.models.item.ItemServiceModel;

import java.util.List;

public interface ItemsService {

    List<ItemServiceModel> getItemsForUser(String username);

    void addToUserById(String id, String username);

    void create(ItemCreateServiceModel serviceModel);

    List<ItemServiceModel> getAll();
}
