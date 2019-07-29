package service;

import dao.ItemDAO;
import exception.BadRequestException;
import model.Item;

public class ItemService {

    private ItemDAO itemDAO;
    private final int MAX_SIZE_DESCR;

    public ItemService(ItemDAO itemDAO, int maxSizeDescription) {
        this.itemDAO = itemDAO;
        MAX_SIZE_DESCR = maxSizeDescription;
    }

    public Item save(Item item) {

        saveValidation(item);
        return itemDAO.save(item);
    }

    public Item getById(long id) {

        return itemDAO.getById(id);
    }

    public Item update(Item item) {

        saveValidation(item);
        return itemDAO.update(item);
    }

    public void delete(long id) {

        itemDAO.delete(id);
    }

    private void saveValidation(Item item) {

        //For example some business logic for bad request situation. Now service is not empty)

        if (item.getDescription().length() > MAX_SIZE_DESCR)
            throw new BadRequestException("The size of description is too big, required less then " + MAX_SIZE_DESCR);

        if (item.getDescription() == null || item.getDescription() == "")
            throw new BadRequestException("Description empty or null not permitted for Item");
    }
}
