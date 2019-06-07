package ua.taxi.best.servlet;

import ua.taxi.best.entity.Car;
import ua.taxi.best.entity.ComfortType;
import ua.taxi.best.entity.Order;
import ua.taxi.best.entity.User;
import ua.taxi.best.repository.impl.OrderRepositoryImpl;
import ua.taxi.best.repository.impl.UserRepositoryImpl;
import ua.taxi.best.service.OrderService;
import ua.taxi.best.service.UserService;
import ua.taxi.best.service.impl.OrderServiceImpl;
import ua.taxi.best.service.impl.UserServiceImpl;
import ua.taxi.best.service.util.Calculator;
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

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl(DbUtil.getDataSource()));
    private OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl(DbUtil.getDataSource()));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/order.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = req.getSession().getAttribute("email").toString();
        String button = req.getParameter("button");
        Long comfort_id = Long.valueOf(req.getParameter("selectComfort"));
        User user = userService.findByEmail(email).orElse(User.builder().build());
        Order order = createOrder(req, user, comfort_id);

        if ("Calculate".equals(button)) {
            order = new Calculator().calculateOrder(order, comfort_id);
            session.setAttribute("objectOrder", order);
        } else if ("Order now!".equals(button)) {
            order = (Order)session.getAttribute("objectOrder");
            updateUserDistanceCount(order);
            orderService.add(order);
            req.setAttribute("order", order);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/congratulation.jsp");
            dispatcher.forward(req, resp);
        }

        req.setAttribute("date", order.getDate().format(formatter));
        req.setAttribute("order", order);
        req.setAttribute("comfort", comfort_id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/order.jsp");
        dispatcher.forward(req, resp);
    }

    private void updateUserDistanceCount(Order order) {
        Integer distance = order.getDistance();
        Optional<User> optionalUser = userService.findById(order.getCustomer().getId());
        User user = optionalUser.orElse(User.builder().build());
        user.setTraveledDistance(user.getTraveledDistance() + distance / 1000);
        userService.updateTraveledDistance(user);
    }

    private Order createOrder(HttpServletRequest req, User user, Long comfort_id) {
        return Order.builder()
                .withDate(LocalDate.parse(req.getParameter("date"), DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .withStartDescription(req.getParameter("startDescription"))
                .withFinishDescription(req.getParameter("finishDescription"))
                .withDistance(Integer.parseInt(req.getParameter("distance")))
                .withOwner(user)
                .withCar(
                        Car.builder()
                                .withType(
                                        ComfortType.builder()
                                                .withId(comfort_id)
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}
