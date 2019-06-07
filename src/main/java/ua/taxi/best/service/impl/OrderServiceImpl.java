package ua.taxi.best.service.impl;

import ua.taxi.best.entity.Order;
import ua.taxi.best.repository.OrderRepository;
import ua.taxi.best.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<Order> findByPaginationByUserId(Long userId, int currentPage, int recordsPerPage) {
        return repository.findByPaginationByUserId(userId, currentPage, recordsPerPage);
    }

    @Override
    public void add(Order order) {
        repository.add(order);
    }

    @Override
    public int getNumberOfRowsByUserId(Long userId) {
        return repository.getNumberOfRowsByUserId(userId);
    }
}
