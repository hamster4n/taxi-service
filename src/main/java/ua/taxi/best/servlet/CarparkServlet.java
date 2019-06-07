package ua.taxi.best.servlet;

import ua.taxi.best.entity.Car;
import ua.taxi.best.repository.CarRepository;
import ua.taxi.best.repository.impl.CarRepositoryImpl;
import ua.taxi.best.service.CarService;
import ua.taxi.best.service.impl.CarServiceImpl;
import ua.taxi.best.util.DbUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/carpark")
public class CarparkServlet extends HttpServlet {

    private CarService service = new CarServiceImpl(new CarRepositoryImpl(DbUtil.getDataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Car> all = service.findAllGroupByComfortDistinct();
        req.setAttribute("carList", all);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/carpark.jsp");
        dispatcher.forward(req, resp);
    }
}
