package ua.taxi.best.service.impl;

import ua.taxi.best.entity.Car;
import ua.taxi.best.entity.ComfortType;
import ua.taxi.best.repository.CarRepository;
import ua.taxi.best.service.CarService;

import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findAllGroupByComfortDistinct() {
        return carRepository.findAllGroupByComfortDistinct();
    }

    @Override
    public List<Car> findByPagination(int currentPage, int recordsPerPage) {
        return carRepository.findByPagination(currentPage, recordsPerPage);
    }

    @Override
    public Optional<Car> findAnyByComfortId(Long economyComfortId) {
        return carRepository.findAnyByComfortId(economyComfortId);
    }

    @Override
    public List<Car> findAvailableCarByComfortId(Long comfort_id) {
        return carRepository.findAvailableCarByComfortId(comfort_id);
    }

    @Override
    public int getNumberOfRows() {
        return carRepository.getNumberOfRows();
    }
}
