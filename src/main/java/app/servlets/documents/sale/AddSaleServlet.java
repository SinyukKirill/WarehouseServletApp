package app.servlets.documents.sale;

import app.model.entities.docs.Sale;
import app.service.DaoFactory;
import app.service.converter.json.JsonSaleConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "AddSaleServlet", urlPatterns = "/sale/add")
public class AddSaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strProduct = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonSaleConverter converter = new JsonSaleConverter();
        Sale sale = converter.parseSaleFromJson(strProduct);
        if (sale == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        DaoFactory.getSaleDAO().add(sale);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
