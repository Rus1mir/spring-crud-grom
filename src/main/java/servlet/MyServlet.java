package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.ItemController;
import dao.ItemDAO;
import exception.NotFoundException;
import model.Item;
import org.hibernate.HibernateException;
import service.ItemService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class MyServlet extends HttpServlet {

    ItemController controller = new ItemController(new ItemService(new ItemDAO()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {

            ObjectMapper mapper = new ObjectMapper();
            Long id = Long.parseLong(req.getParameter("id"));

            mapper.writeValue(resp.getWriter(), controller.getById(id));

        } catch (NumberFormatException e) {

            resp.getWriter().println("Id param must be a number like 'id = 1'");
        } catch (NotFoundException e) {

            resp.getWriter().println(e.getMessage());
        } catch (HibernateException e) {

            resp.getWriter().println("Internal server Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {

            Item item = getItem(req);
            controller.save(item);
            resp.getWriter().println("Item id: " + item.getId() + " is successfully saved");

        } catch (Exception e) {
            resp.getWriter().println("Saving item filed !" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {

            Item item = getItem(req);
            controller.update(item);

            resp.getWriter().println("Item id: " + item.getId() + " is successfully updated");

        } catch (Exception e) {
            resp.getWriter().println("Update item filed !" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {

            Long id = Long.parseLong(req.getParameter("id"));
            controller.delete(id);
            resp.getWriter().println("Item id: " + id + " was deleted successfully");

        } catch (NumberFormatException e) {

            resp.getWriter().println("Id param must be a number like 'id = 1'");
        } catch (EntityNotFoundException e) {

            resp.getWriter().println(e.getMessage());
        } catch (HibernateException e) {

            resp.getWriter().println("Deleting item was filed");
        }
    }

    private Item getItem(HttpServletRequest req) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        Item item = mapper.readValue(req.getReader(), Item.class);

        if (item.getLastUpdatedDate() == null)
            item.setLastUpdatedDate(new Date()); //Maybe as option

        if (item.getDateCreated() == null)
            item.setDateCreated(new Date()); //Maybe as option

        return item;
    }
}
