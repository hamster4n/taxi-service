package ua.taxi.best.servlet;

import ua.taxi.best.entity.User;
import ua.taxi.best.repository.UserRepository;
import ua.taxi.best.repository.impl.UserRepositoryImpl;
import ua.taxi.best.service.UserService;
import ua.taxi.best.service.impl.UserServiceImpl;
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

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService service = new UserServiceImpl(new UserRepositoryImpl(DbUtil.getDataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Paginator.getCurrentPage(req);
        int recordsPerPage = Paginator.getRecordsPerPage(req);
        int numberOfRows = service.getNumberOfRows();
        int nOfPages = Paginator.getNumberOfPages(numberOfRows, recordsPerPage);
        List<User> usersByPagination = service.findByPagination(currentPage, recordsPerPage);
        Paginator.setAttributes(req, nOfPages, currentPage, recordsPerPage);
        req.setAttribute("userList", usersByPagination);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/users.jsp");
        dispatcher.forward(req, resp);
    }
}
