package ua.taxi.best.repository;

import ua.taxi.best.entity.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findAllByUserId(Long userId);

    List<Order> findByPaginationByUserId(Long userId, int currentPage, int recordsPerPage);

    int getNumberOfRowsByUserId(Long userId);

    void add(Order order);
}
