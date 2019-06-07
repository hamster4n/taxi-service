package ua.taxi.best.repository.impl;

import org.apache.log4j.Logger;
import ua.taxi.best.entity.Car;
import ua.taxi.best.entity.Order;
import ua.taxi.best.entity.User;
import ua.taxi.best.exception.RepositoryException;
import ua.taxi.best.repository.OrderRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    private static final Logger LOGGER = Logger.getLogger(OrderRepositoryImpl.class);
    private static final String FIND_ALL_BY_USER_ID =
            "SELECT * FROM orders JOIN cars ON orders.car_id = cars.car_id WHERE user_id = ?";
    private static final String ADD =
            "INSERT INTO orders (date, start_description, finish_description, distance, car_id, " +
                    "base_cost, final_cost, loyalty_cost, discount_cost, birthday_cost, auto_feed, comfort_cost, user_id) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String FIND_BY_PAGINATION_BY_USER_ID =
            "SELECT * FROM orders " +
                    "JOIN cars ON orders.car_id = cars.car_id " +
                    "WHERE user_id = ? ORDER BY date DESC LIMIT ? OFFSET ? ";
    private static final String GET_NUMBER_OF_ROWS_BY_USER_ID =
            "SELECT COUNT(order_id) FROM orders WHERE user_id = ?";

    private final DataSource dataSource;

    public OrderRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_USER_ID)) {
            ps.setLong(1, userId);
            ResultSet result = ps.executeQuery();
            return collectToList(result);
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findAllByUserId");
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(Order order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(ADD)) {
            ps.setDate(1, java.sql.Date.valueOf(order.getDate()));
            ps.setString(2, order.getStartDescription());
            ps.setString(3, order.getFinishDescription());
            ps.setInt(4, order.getDistance());
            ps.setLong(5, order.getCar().getId());
            ps.setInt(6, order.getBaseCost());
            ps.setInt(7, order.getFinalCost());
            ps.setInt(8, order.getLoyaltyCost());
            ps.setInt(9, order.getDiscountCost());
            ps.setInt(10, order.getBirthdayCost());
            ps.setInt(11, order.getAutoFeed());
            ps.setInt(12, order.getComfortCost());
            ps.setLong(13, order.getCustomer().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException in method add");
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Order> findByPaginationByUserId(Long userId, int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_PAGINATION_BY_USER_ID)) {
            ps.setLong(1, userId);
            ps.setInt(2, recordsPerPage);
            ps.setInt(3, start);
            ResultSet result = ps.executeQuery();
            return collectToList(result);
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findByPagination: " + e.getMessage());
            throw new RepositoryException(e);
        }
    }

    @Override
    public int getNumberOfRowsByUserId(Long userId) {
        int numberOfRows = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_NUMBER_OF_ROWS_BY_USER_ID)) {
            ps.setLong(1, userId);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                numberOfRows = result.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in method getNumberOfRows: " + e.getMessage());
            throw new RepositoryException(e);
        }
        return numberOfRows;
    }

    private Optional<Order> parseRow(ResultSet result) throws SQLException {
        return Optional.ofNullable(Order.builder()
                .withId(result.getLong("order_id"))
                .withDate(result.getDate("date").toLocalDate())
                .withStartDescription(result.getString("start_description"))
                .withFinishDescription(result.getString("finish_description"))
                .withDistance(result.getInt("distance"))
                .withCar(
                        Car.builder()
                                .withLicensePlate(result.getString("license_plate"))
                                .build()
                )
                .withBaseCost(result.getInt("base_cost"))
                .withFinalCost(result.getInt("final_cost"))
                .withLoyaltyCost(result.getInt("loyalty_cost"))
                .withDiscountCost(result.getInt("discount_cost"))
                .withBirthdayCost(result.getInt("birthday_cost"))
                .withAutoFeed(result.getInt("auto_feed"))
                .withComfortCost(result.getInt("comfort_cost"))
                .withOwner(User.builder().withId(result.getLong("user_id")).build())
                .build());
    }

    private List<Order> collectToList(ResultSet result) throws SQLException {
        List<Order> list = new ArrayList<>();
        while (result.next()) {
            Order order = parseRow(result).orElse(Order.builder().build());
            list.add(order);
        }
        return list;
    }
}
