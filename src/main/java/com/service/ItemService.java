package com.service;

import com.dao.ItemDAO;

import com.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {

    @Autowired
    private ItemDAO itemDAO;

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
