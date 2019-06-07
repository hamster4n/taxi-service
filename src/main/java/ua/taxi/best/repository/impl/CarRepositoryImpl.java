package ua.taxi.best.repository.impl;

import org.apache.log4j.Logger;
import ua.taxi.best.entity.Car;
import ua.taxi.best.entity.ComfortType;
import ua.taxi.best.entity.Model;
import ua.taxi.best.exception.RepositoryException;
import ua.taxi.best.repository.CarRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepositoryImpl implements CarRepository {

    private static final Logger LOGGER = Logger.getLogger(CarRepositoryImpl.class);
    private static final String FIND_ALL =
            "SELECT * FROM cars " +
                    "JOIN comforts ON cars.type_id = comforts.comfort_id " +
                    "JOIN models ON cars.model_id = models.model_id";
    private static final String FIND_ALL_GROUP_BY_COMFORT_DISTINCT =
            "SELECT DISTINCT ON (cars.model_id) cars.model_id, cars.car_id, * FROM cars " +
                    "JOIN comforts ON cars.type_id = comforts.comfort_id " +
                    "JOIN models ON cars.model_id = models.model_id";
    private static final String FIND_AVAILABLE_CAR_BY_COMFORT_ID =
            "SELECT * FROM cars JOIN comforts ON cars.type_id = comforts.comfort_id " +
                    "JOIN models ON cars.model_id = models.model_id " +
                    "WHERE cars.available = 'true' AND comforts.comfort_id = ?";
    private static final String FIND_BY_PAGINATION =
            "SELECT * FROM cars " +
                    "JOIN comforts ON cars.type_id = comforts.comfort_id " +
                    "JOIN models ON cars.model_id = models.model_id LIMIT ? OFFSET ?";
    private static final String GET_NUMBER_OF_ROW = "SELECT COUNT(car_id) FROM cars";
    private static final String FIND_ANY_BY_COMFORT_ID =
            "SELECT * FROM cars " +
                    "JOIN comforts ON cars.type_id = comforts.comfort_id " +
                    "JOIN models ON cars.model_id = models.model_id " +
                    "WHERE type_id = ? ORDER BY RANDOM() LIMIT 1";

    private final DataSource dataSource;

    public CarRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Car> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(FIND_ALL)) {
            return collectToList(result);
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findAll: " + e);
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Car> findAllGroupByComfortDistinct() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(FIND_ALL_GROUP_BY_COMFORT_DISTINCT)) {
            return collectToList(result);
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findAllGroupByComfortDistinct: " + e);
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Car> findAvailableCarByComfortId(Long comfort_id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_AVAILABLE_CAR_BY_COMFORT_ID)) {
            ps.setLong(1, comfort_id);
            ResultSet result = ps.executeQuery();
            return collectToList(result);
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findAvailableCarByComfort: " + e);
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<Car> findByPagination(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_PAGINATION);
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
    public Optional<Car> findAnyByComfortId(Long economyComfortId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ANY_BY_COMFORT_ID)) {
            ps.setLong(1, economyComfortId);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return parseRow(result);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in method findByPagination: " + e.getMessage());
            throw new RepositoryException(e);
        }
        return Optional.empty();
    }

    @Override
    public int getNumberOfRows() {
        int numberOfRows = 0;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(GET_NUMBER_OF_ROW)) {
            while (result.next()) {
                numberOfRows = result.getInt("count");
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in method getNumberOfRows: " + e.getMessage());
            throw new RepositoryException(e);
        }
        return numberOfRows;
    }

    private Optional<Car> parseRow(ResultSet result) throws SQLException {
        return Optional.ofNullable(Car.builder()
                .withId(result.getLong("car_id"))
                .withLicensePlate(result.getString("license_plate"))
                .withType(
                        ComfortType.builder()
                                .withId(result.getLong("comfort_id"))
                                .withTitle(result.getString("title"))
                                .withDescription(result.getString("description"))
                                .build()
                )
                .withPoductionYear(result.getInt("production_year"))
                .withAvailable(result.getBoolean("available"))
                .withModel(
                        Model.builder()
                                .withId(result.getLong("model_id"))
                                .withName(result.getString("name"))
                                .withPhoto(result.getString("photo"))
                                .build()
                )
                .build());
    }

    private List<Car> collectToList(ResultSet result) throws SQLException {
        List<Car> list = new ArrayList<>();
        while (result.next()) {
            Car car = parseRow(result).orElse(Car.builder().build());
            list.add(car);
        }
        return list;
    }
}
