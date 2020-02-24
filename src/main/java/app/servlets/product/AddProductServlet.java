package app.servlets.product;

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

@WebServlet(name = "AddProductServlet", urlPatterns = "/product/add")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strProduct = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonConverter converter = new JsonConverter();
        Product product = converter.parseProductFromJson(strProduct, true);
        if (product == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        FactoryDao.getInstance().getProductDAO().addProduct(product);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
