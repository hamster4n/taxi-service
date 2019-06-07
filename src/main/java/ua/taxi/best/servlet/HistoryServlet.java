package ua.taxi.best.servlet;

import ua.taxi.best.entity.Order;
import ua.taxi.best.repository.impl.OrderRepositoryImpl;
import ua.taxi.best.service.OrderService;
import ua.taxi.best.service.impl.OrderServiceImpl;
import ua.taxi.best.servlet.util.Paginator;
import ua.taxi.best.util.DbUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

    private OrderService service = new OrderServiceImpl(new OrderRepositoryImpl(DbUtil.getDataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        int currentPage = Paginator.getCurrentPage(req);
        int recordsPerPage = Paginator.getRecordsPerPage(req);
        int numberOfRows = service.getNumberOfRowsByUserId(userId);
        int nOfPages = Paginator.getNumberOfPages(numberOfRows, recordsPerPage);
        List<Order> allPaginationByUserId = service.findByPaginationByUserId(userId, currentPage, recordsPerPage);
        Paginator.setAttributes(req, nOfPages, currentPage, recordsPerPage);
        req.setAttribute("orderList", allPaginationByUserId);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/history.jsp");
        dispatcher.forward(req, resp);
    }
}
