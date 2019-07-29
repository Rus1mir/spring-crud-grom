package demo;

import controller.ItemController;
import dao.ItemDAO;
import service.ItemService;

public class Demo {
    public static void main(String[] args) throws Exception {

        System.out.println(new ItemController(new ItemService(new ItemDAO(), 100)).getById(1));
    }
}
