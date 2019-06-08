package ua.taxi.best.servlet;

import ua.taxi.best.entity.User;
import ua.taxi.best.repository.UserRepository;
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
import java.io.IOException;
import java.util.Optional;

import static ua.taxi.best.servlet.util.Validator.isEmailValid;
import static ua.taxi.best.servlet.util.Validator.isPasswordValid;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl(DbUtil.getDataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        emailValidation(req, resp, email);
        passwordValidation(req, resp, password);
        passwordComparison(req, resp, password, password2);
        Optional<User> optionalUser = userService.findByEmail(email);
        checkingExistenceUserInDatabase(req, resp, optionalUser);
        userService.add(email, password);
        req.setAttribute("successful_registration", "Successful registration! Now you can login!");
        resp.sendRedirect("/besttaxi/login");
    }

    private void emailValidation(HttpServletRequest req, HttpServletResponse resp, String email) throws ServletException, IOException {
        if (!isEmailValid(email)) {
            req.setAttribute("exception", "Your email is invalid.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void passwordValidation(HttpServletRequest req, HttpServletResponse resp, String password)
            throws ServletException, IOException {
        if (!isPasswordValid(password)) {
            req.setAttribute("exception", "Your password is invalid.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void passwordComparison(HttpServletRequest req, HttpServletResponse resp, String password,
                                    String password2) throws ServletException, IOException {
        if (!password.equals(password2)) {
            req.setAttribute("exception", "Your passwords are different!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void checkingExistenceUserInDatabase(HttpServletRequest req, HttpServletResponse resp,
                                                 Optional<User> optionalUser) throws ServletException, IOException {
        if (optionalUser.isPresent()) {
            req.setAttribute("exception", "User with this email is already registered!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/registration.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
