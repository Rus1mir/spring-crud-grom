package controller;

import model.Item;
import service.ItemService;

public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    public Item save(Item item) {

        return itemService.save(item);
    }

    public Item getById(long id) {

        return itemService.getById(id);
    }

    public Item update(Item item) {

        return itemService.update(item);
    }

    public void delete(long id) {

        itemService.delete(id);
    }
}
