package softuni.heroes.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import softuni.heroes.base.TestBase;
import softuni.heroes.data.models.Item;
import softuni.heroes.data.repositories.ItemRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemsApiController extends TestBase {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    ItemRepository itemRepository;

    @Test
    void asd(){
        Item item = new Item();
        item.setId("1");
        item.setName("Sword");
        Mockito.when(itemRepository.findAll())
                .thenReturn(List.of(item));
        var result = testRestTemplate.getForObject("http://localhost:" + port + "/api/items-all", String.class);
    }
}
