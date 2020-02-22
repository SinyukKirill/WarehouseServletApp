package app.servlets.warehouse;

import app.entities.Warehouse;
import app.service.FactoryDao;
import app.util.ValidateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddWarehouseServlet", urlPatterns = "/warehouse/add")
public class AddWarehouseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Warehouse warehouse = ValidateUtil.validateAndCreateWarehouse(req);
        if (warehouse == null){
            resp.sendError(400);
        } else {
            FactoryDao.getInstance().getWarehouseDAO().addWarehouse(warehouse);
            resp.setStatus(200);
        }
        doGet(req, resp);
    }
}