package ua.taxi.best.service;

import ua.taxi.best.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllByUserId(Long userId);

    List<Order> findByPaginationByUserId(Long userId, int currentPage, int recordsPerPage);

    int getNumberOfRowsByUserId(Long userId);

    void add(Order order);
}
