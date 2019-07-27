package service;

import dao.ItemDAO;
import model.Item;

public class ItemService {

    private ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public Item save(Item item) {

        return itemDAO.save(item);
    }

    public Item getById(long id) {

        return itemDAO.getById(id);
    }

    public Item update(Item item) {

        return itemDAO.update(item);
    }

    public void delete(long id) {

        itemDAO.delete(id);
    }
}
