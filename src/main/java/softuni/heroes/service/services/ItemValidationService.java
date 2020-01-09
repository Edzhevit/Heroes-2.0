package softuni.heroes.service.services;

import softuni.heroes.service.models.item.ItemCreateServiceModel;

public interface ItemValidationService {

    boolean isValid(ItemCreateServiceModel serviceModel);
}
