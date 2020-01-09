package softuni.heroes.web.api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import softuni.heroes.service.models.item.ItemCreateServiceModel;
import softuni.heroes.service.services.ItemsService;
import softuni.heroes.web.api.models.ItemCreateRequestModel;
import softuni.heroes.web.api.models.ItemResponseModel;
import softuni.heroes.web.base.BaseController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ItemsApiController extends BaseController {

    private final ItemsService itemsService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemsApiController(ItemsService itemsService, ModelMapper modelMapper) {
        this.itemsService = itemsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/items")
    public ResponseEntity<List<ItemResponseModel>> getItems(HttpSession session){
        String username = getUsername(session);
        List<ItemResponseModel> result = itemsService.getItemsForUser(username)
                .stream()
                .map(item -> modelMapper.map(item, ItemResponseModel.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/api/items-all")
    public List<ItemResponseModel> getItems() throws InterruptedException {
        return itemsService.getAll()
                .stream()
                .map(item -> modelMapper.map(item, ItemResponseModel.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/items/buy/{id}")
    public void buyItem(@PathVariable String id, HttpSession session, HttpServletResponse response) throws IOException {
        String username = getUsername(session);
        itemsService.addToUserById(id, username);
        String heroName = getHeroName(session);

        response.sendRedirect("/heroes/details/" + heroName);
    }

    @PostMapping("/api/items")
    public ResponseEntity<Void> create(ItemCreateRequestModel requestModel) throws IOException {
        ItemCreateServiceModel serviceModel = modelMapper.map(requestModel, ItemCreateServiceModel.class);
        itemsService.create(serviceModel);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/items/merchant");
        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }
}
