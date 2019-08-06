package com.controller;

import com.exception.BadRequestException;
import com.exception.NotFoundException;
import com.model.Item;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.service.ItemService;

import javax.persistence.EntityNotFoundException;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public ResponseEntity<Item> save(@RequestBody Item item) {

        try {

            return new ResponseEntity<>(itemService.save(item),HttpStatus.CREATED);
        } catch (BadRequestException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HibernateException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public ResponseEntity<Item> get(@RequestParam(value = "id") Long id) {
        try {

            return new ResponseEntity<>(itemService.getById(id), HttpStatus.OK);
        } catch (NumberFormatException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (HibernateException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<Item> update(@RequestBody Item item) {

        try {

            return new ResponseEntity<>(itemService.update(item),HttpStatus.OK);
        } catch (BadRequestException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (HibernateException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity<String> delete(@RequestParam(value = "id") Long id){

        try {

            itemService.delete(id);
            return new ResponseEntity<>("Successfuly deleted", HttpStatus.OK);
        } catch (EntityNotFoundException e) {

            return new ResponseEntity<>("No found", HttpStatus.NOT_FOUND);

        } catch (HibernateException e) {

            return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
