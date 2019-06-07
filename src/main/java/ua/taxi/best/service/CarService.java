package ua.taxi.best.service;

import ua.taxi.best.entity.Car;
import ua.taxi.best.entity.ComfortType;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();

    List<Car> findAllGroupByComfortDistinct();

    List<Car> findByPagination(int currentPage, int recordsPerPage);

    List<Car> findAvailableCarByComfortId(Long comfort_id);

    int getNumberOfRows();

    Optional<Car> findAnyByComfortId(Long economyComfortId);
}
