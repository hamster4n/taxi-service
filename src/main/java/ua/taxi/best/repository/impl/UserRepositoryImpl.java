package ua.taxi.best.repository.impl;

import org.apache.log4j.Logger;
import ua.taxi.best.entity.Role;
import ua.taxi.best.entity.User;
import ua.taxi.best.exception.RepositoryException;
import ua.taxi.best.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);
    private static final int ROLE_USER = 1;
    private static final String FIND_BY_ID =
            "SELECT * FROM users " +
                    "JOIN  roles ON users.role_id = roles.role_id " +
                    "WHERE user_id = ?";
    private static final String FIND_BY_EMAIL =
            "SELECT * FROM users " +
                    "JOIN roles ON users.role_id = roles.role_id " +
                    "WHERE mail = ?";
    private static final String FIND_ALL =
            "SELECT * FROM users " +
                    "JOIN roles ON users.role_id = roles.role_id";
    private static final String ADD =
            "INSERT INTO users (mail, password, role_id) VALUES (?, ?, ?);";
    private static final String UPDATE_LOYALTY =
            "UPDATE users SET loyalty=? WHERE user_id = ?";
    private static final String UPDATE_TRAVELED_DISTANCE =
            "UPDATE users SET traveled_distance=? WHERE user_id = ?";
    private static final String UPDATE =
            "UPDATE users SET name=?, password=?,credit_card=?,birthday=?, discount=?, loyalty=?, profile_bonus=?" +
                    " WHERE user_id = ?";
    private static final String FIND_BY_PAGINATION =
            "SELECT * FROM users " +
                    "JOIN roles ON users.role_id = roles.role_id " +
                    "LIMIT ? OFFSET ?";
    private static final String GET_NUMBER_OF_ROWS =
            "SELECT COUNT(user_id) FROM users";

    private final DataSource dataSource;

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return parseRow(result);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findById");
            throw new RepositoryException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return parseRow(result);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findByEmail");
            throw new RepositoryException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(FIND_ALL)) {
            return collectToList(result);
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findAll");
            throw new RepositoryException(e);
        }
    }

    @Override
    public void add(String email, String password) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(ADD)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setLong(3, ROLE_USER);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException in method add");
            throw new RepositoryException(e);
        }
    }

    @Override
    public void updateLoyalty(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_LOYALTY)) {
            ps.setInt(1, user.getLoyalty());
            ps.setLong(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("SQLException in method updateLoyalty");
            throw new RepositoryException(e);
        }
    }

    @Override
    public void updateTraveledDistance(User user) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_TRAVELED_DISTANCE)) {
            ps.setInt(1, user.getTraveledDistance());
            ps.setLong(2, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("SQLException in method updateTraveledDistance");
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getCreditCard());
            ps.setDate(4, java.sql.Date.valueOf(user.getBirthday()));
            ps.setInt(5, user.getDiscount());
            ps.setInt(6, user.getLoyalty());
            ps.setBoolean(7, user.getProfileBonus());
            ps.setLong(8, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("SQLException in method update");
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<User> findByPagination(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_PAGINATION)) {
            ps.setInt(1, recordsPerPage);
            ps.setInt(2, start);
            ResultSet result = ps.executeQuery();
            return collectToList(result);
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findByPagination: " + e.getMessage());
            throw new RepositoryException(e);
        }
    }

    @Override
    public int getNumberOfRows() {
        int numberOfRows = 0;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(GET_NUMBER_OF_ROWS)) {
            while (result.next()) {
                numberOfRows = result.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in method getNumberOfRows: " + e.getMessage());
            throw new RepositoryException(e);
        }
        return numberOfRows;
    }

    private Optional<User> parseRow(ResultSet result) throws SQLException {
        return Optional.ofNullable(User.builder()
                .withId(result.getLong("user_id"))
                .withName(result.getString("name"))
                .withEmail(result.getString("mail"))
                .withPassword(result.getString("password"))
                .withCreditCard(result.getString("credit_card"))
                .withProfileBonus(result.getBoolean("profile_bonus"))
                .withDiscount(result.getInt("discount"))
                .withTraveledDistance(result.getInt("traveled_distance"))
                .withLoyalty(result.getInt("loyalty"))
                .withBirthday(
                        result.getDate("birthday") == null ? null :
                                result.getDate("birthday").toLocalDate()
                )
                .withRole(
                        Role.builder()
                                .withId(result.getLong("role_id"))
                                .withTitle(result.getString("title"))
                                .withDescription(result.getString("description"))
                                .build()
                )
                .build());
    }

    private List<User> collectToList(ResultSet result) throws SQLException {
        List<User> list = new ArrayList<>();
        while (result.next()) {
            User user = parseRow(result).orElse(User.builder().build());
            list.add(user);
        }
        return list;
    }
}
