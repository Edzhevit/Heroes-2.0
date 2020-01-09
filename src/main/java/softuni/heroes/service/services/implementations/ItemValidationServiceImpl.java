package softuni.heroes.service.services.implementations;

import org.springframework.stereotype.Service;
import softuni.heroes.service.models.item.ItemCreateServiceModel;
import softuni.heroes.service.services.ItemValidationService;

@Service
public class ItemValidationServiceImpl implements ItemValidationService {

    @Override
    public boolean isValid(ItemCreateServiceModel serviceModel) {
        return serviceModel != null &&
                serviceModel.getName() != null &&
                serviceModel.getSlot() != null &&
                serviceModel.getAttack() > 0;
    }
}
