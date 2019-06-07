package ua.taxi.best.servlet;

import ua.taxi.best.entity.Car;
import ua.taxi.best.repository.CarRepository;
import ua.taxi.best.repository.impl.CarRepositoryImpl;
import ua.taxi.best.service.CarService;
import ua.taxi.best.service.impl.CarServiceImpl;
import ua.taxi.best.servlet.util.Paginator;
import ua.taxi.best.util.DbUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cars")
public class CarServlet extends HttpServlet {

    private CarService service = new CarServiceImpl(new CarRepositoryImpl(DbUtil.getDataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Paginator.getCurrentPage(req);
        int recordsPerPage = Paginator.getRecordsPerPage(req);
        int numberOfRows = service.getNumberOfRows();
        int nOfPages = Paginator.getNumberOfPages(numberOfRows, recordsPerPage);
        List<Car> byPagination = service.findByPagination(currentPage, recordsPerPage);
        Paginator.setAttributes(req, nOfPages, currentPage, recordsPerPage);
        req.setAttribute("carList", byPagination);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/cars.jsp");
        dispatcher.forward(req, resp);
    }
}
