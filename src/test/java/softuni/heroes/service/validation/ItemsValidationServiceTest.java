package softuni.heroes.service.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.heroes.base.TestBase;
import softuni.heroes.data.models.Slot;
import softuni.heroes.service.models.item.ItemCreateServiceModel;
import softuni.heroes.service.services.ItemValidationService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemsValidationServiceTest extends TestBase {

    @Autowired
    ItemValidationService service;

    @Test
    void isValid_whenNameIsNull_shouldReturnFalse(){
        ItemCreateServiceModel model = new ItemCreateServiceModel(null, Slot.PADS, 1,2,3,4);
        boolean isValid = service.isValid(model);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenSlotIsNull_shouldReturnFalse(){
        ItemCreateServiceModel model = new ItemCreateServiceModel("Valid Name", null, 1,2,3,4);
        boolean isValid = service.isValid(model);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenAttackIsNegative_shouldReturnFalse(){
        ItemCreateServiceModel model = new ItemCreateServiceModel("Valid Name", Slot.PADS, -1,2,3,4);
        boolean isValid = service.isValid(model);
        assertFalse(isValid);
    }

    @Test
    void isValid_whenItemIsValid_shouldReturnTrue(){
        ItemCreateServiceModel model = new ItemCreateServiceModel("Valid Name", Slot.PADS, 1,2,3,4);
        boolean isValid = service.isValid(model);
        assertTrue(isValid);
    }

}
