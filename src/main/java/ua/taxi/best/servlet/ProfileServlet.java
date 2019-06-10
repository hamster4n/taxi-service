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

import static ua.taxi.best.servlet.util.Validator.isPasswordValid;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl(DbUtil.getDataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("email");
        User user = userService.findByEmail(email).orElse(User.builder().build());
        goJsp(req, resp, user);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromJsp(req);
        User userForProfile = userService.findByEmail(user.getEmail()).orElse(User.builder().build());
        user.setProfileBonus(userForProfile.getProfileBonus());
        passwordValidation(req, resp, user.getPassword(), user.getEmail());
        user.setPassword(Encryptor.encrypt(user.getPassword()));
        userService.update(user);
        User userUpdated = userService.findByEmail(user.getEmail()).orElse(User.builder().build());
        goJsp(req, resp, userUpdated);
    }

    private void goJsp(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String birthday = "";
        if (!(user.getBirthday() == null)) {
            birthday = user.getBirthday().format(formatter);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/profile.jsp");
        req.setAttribute("date", birthday);
        req.setAttribute("user", user);
        dispatcher.forward(req, resp);
    }

    private User getUserFromJsp(HttpServletRequest req) {
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String creditCard = req.getParameter("creditCard");
        boolean profileBonus = Boolean.getBoolean(req.getParameter("profile_bonus"));
        int discount = Integer.parseInt(req.getParameter("discount"));
        int traveledDistance = Integer.parseInt(req.getParameter("traveledDistance"));
        int loyalty = Integer.parseInt(req.getParameter("loyalty"));
        String birthday = req.getParameter("date");
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");

        return User.builder()
                .withId(userId)
                .withName(name)
                .withEmail(email)
                .withPassword(password)
                .withCreditCard(creditCard)
                .withProfileBonus(profileBonus)
                .withDiscount(discount)
                .withTraveledDistance(traveledDistance)
                .withLoyalty(loyalty)
                .withBirthday(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .build();
    }

    private void passwordValidation(HttpServletRequest req, HttpServletResponse resp, String password, String email)
            throws ServletException, IOException {
        if (!isPasswordValid(password)) {
            User user = userService.findByEmail(email).orElse(User.builder().build());
            req.setAttribute("exception", "Your password is invalid.");
            req.setAttribute("user", user);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/profile.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
