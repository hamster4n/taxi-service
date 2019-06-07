package ua.taxi.best.service.impl;

import ua.taxi.best.entity.User;
import ua.taxi.best.repository.UserRepository;
import ua.taxi.best.service.UserService;
import ua.taxi.best.service.constant.BusinessConstant;
import ua.taxi.best.servlet.util.Encryptor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final int PROFILE_BONUS = 1;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void add(String email, String password) {
        userRepository.add(email, Encryptor.encrypt(password));
    }

    @Override
    public void update(User user) {
        if (isUserFillProfile(user)) {
            user.setProfileBonus(true);
            user.setDiscount(user.getDiscount() + PROFILE_BONUS);
        }
        userRepository.update(user);
    }

    @Override
    public void updateLoyalty(User user) {
        int loyaltyShouldBe = calculateLoyalty(user.getTraveledDistance());
        if (loyaltyShouldBe > user.getLoyalty()) {
            user.setLoyalty(loyaltyShouldBe);
            userRepository.updateLoyalty(user);
        }
    }

    @Override
    public void updateTraveledDistance(User user) {
        userRepository.updateTraveledDistance(user);
    }

    @Override
    public List<User> findByPagination(int currentPage, int recordsPerPage) {
        return userRepository.findByPagination(currentPage, recordsPerPage);
    }

    @Override
    public int getNumberOfRows() {
        return userRepository.getNumberOfRows();
    }

    private int calculateLoyalty(int currentTravelDistance) {
        int result = 0;
        Map<Integer, Integer> loyaltyMap = BusinessConstant.getLoyaltyMap();
        for (Map.Entry<Integer, Integer> entry : loyaltyMap.entrySet()) {
            Integer temp = entry.getValue();
            if (currentTravelDistance >= temp) {
                result = entry.getKey();
            }
        }
        return result;
    }

    private boolean isUserFillProfile(User user) {
        return !"".equals(user.getName()) && !"".equals(user.getCreditCard()) && !user.getProfileBonus();
    }
}
