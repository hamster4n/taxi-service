package ua.taxi.best.servlet;

import ua.taxi.best.entity.User;
import ua.taxi.best.repository.impl.UserRepositoryImpl;
import ua.taxi.best.service.UserService;
import ua.taxi.best.service.impl.UserServiceImpl;
import ua.taxi.best.servlet.util.Encryptor;
import ua.taxi.best.util.DbUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static ua.taxi.best.servlet.util.Validator.isEmailValid;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl(DbUtil.getDataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        emailValidation(req, resp, email);
        Optional<User> optionalUser = userService.findByEmail(email);
        checkingExistenceUserInDatabase(req, resp, optionalUser);
        User user = optionalUser.orElse(User.builder().build());
        if (!user.getPassword().equals(Encryptor.encrypt(password))) {
            req.setAttribute("exception", "You entered the wrong password!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        userService.updateLoyalty(user);

        HttpSession session = req.getSession();
        session.setAttribute("currentDate", LocalDate.now().format(formatter));
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole().getTitle());
        session.setAttribute("email", email);
        resp.sendRedirect("/besttaxi/order");
    }

    private void emailValidation(HttpServletRequest req, HttpServletResponse resp, String email) throws ServletException, IOException {
        if (!isEmailValid(email)) {
            req.setAttribute("exception", "Your email is invalid.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
            return;
        }
    }

    private void checkingExistenceUserInDatabase(HttpServletRequest req, HttpServletResponse resp,
                                                 Optional<User> optionalUser) throws ServletException, IOException {
        if (!optionalUser.isPresent()) {
            req.setAttribute("exception", "There is no user with such email!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(req, resp);
            return;
        }
    }
}
