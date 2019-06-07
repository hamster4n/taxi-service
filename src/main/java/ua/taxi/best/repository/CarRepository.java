package ua.taxi.best.repository;

import ua.taxi.best.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    List<Car> findAll();

    List<Car> findAllGroupByComfortDistinct();

    List<Car> findByPagination(int currentPage, int recordsPerPage);

    List<Car> findAvailableCarByComfortId(Long comfort_id);

    int getNumberOfRows();

    Optional<Car> findAnyByComfortId(Long economyComfortId);
}
