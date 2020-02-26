package app.servlets.documents.movement;

import app.model.entities.Product;
import app.service.FactoryDao;
import app.service.converter.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "AddMovementServlet", urlPatterns = "/movement/add")
public class AddMovementServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strMovement = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonConverter converter = new JsonConverter();
        Product product = converter.parseProductFromJson(strMovement, true);
        if (product == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        FactoryDao.getInstance().getProductDAO().addProduct(product);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
