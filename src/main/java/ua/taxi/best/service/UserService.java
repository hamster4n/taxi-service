package ua.taxi.best.service;

import ua.taxi.best.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void add(String email, String password);

    void update(User user);

    void updateLoyalty(User user);

    void updateTraveledDistance(User user);

    List<User> findByPagination(int currentPage, int recordsPerPage);

    int getNumberOfRows();
}
