package ua.taxi.best.service.util;

import ua.taxi.best.entity.Car;
import ua.taxi.best.entity.Order;
import ua.taxi.best.entity.User;
import ua.taxi.best.repository.impl.CarRepositoryImpl;
import ua.taxi.best.service.CarService;
import ua.taxi.best.service.impl.CarServiceImpl;
import ua.taxi.best.util.DbUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import static ua.taxi.best.service.constant.BusinessConstant.AUTO_FEED;
import static ua.taxi.best.service.constant.BusinessConstant.BASE_RATE;
import static ua.taxi.best.service.constant.BusinessConstant.BIRTHDAY_DISCOUNT;
import static ua.taxi.best.service.constant.BusinessConstant.COMFORT_BUSINESS_COST;
import static ua.taxi.best.service.constant.BusinessConstant.COMFORT_ECONOMY_COST;
import static ua.taxi.best.service.constant.BusinessConstant.COMFORT_LUX_COST;
import static ua.taxi.best.service.util.ArrivalTimeSimulator.generateArrivalTime;

public final class Calculator {

    private CarService service = new CarServiceImpl(new CarRepositoryImpl(DbUtil.getDataSource()));

    public Calculator() {
    }

    public Order calculateOrder(Order order, Long comfort_id) {
        Order calculatedOrder = calculateFinalPrice(order);
        return findAvailableCar(calculatedOrder, comfort_id);
    }

    private Order findAvailableCar(Order calculatedOrder, Long comfort_id) {
        List<Car> availableCars = service.findAvailableCarByComfortId(comfort_id);
        Car nearestCar = chooseNearestCar(availableCars);
        calculatedOrder.setCar(nearestCar);
        return calculatedOrder;
    }

    private Car chooseNearestCar(List<Car> availableCars) {
        TreeMap<Integer, Car> map = new TreeMap<>();
        Car result = null;
        for (Car car : availableCars) {
            int minutes = generateArrivalTime();
            map.put(minutes, car);
        }
        if (!(map.size() == 0)) {
            result = map.firstEntry().getValue();
            result.setArrivalTime(map.firstEntry().getKey());
        }
        return result;
    }

    private Order calculateFinalPrice(Order order) {
        User owner = order.getCustomer();
        int baseCost = order.getDistance() * BASE_RATE / 1000;
        int discountCost = calculateReduction(owner.getDiscount(), baseCost);
        int loyaltyCost = calculateReduction(owner.getLoyalty(), baseCost);
        int birthdayCost = calculateBirthdayReduction(owner, baseCost);
        int comfortCost = calculateComfortCost(order);
        int finalCost = baseCost - discountCost - loyaltyCost - birthdayCost + AUTO_FEED + comfortCost;

        order.setComfortCost(comfortCost);
        order.setAutoFeed(AUTO_FEED);
        order.setBaseCost(baseCost);
        order.setDiscountCost(discountCost);
        order.setLoyaltyCost(loyaltyCost);
        order.setBirthdayCost(birthdayCost);
        order.setFinalCost(finalCost);
        return order;
    }

    private int calculateComfortCost(Order order) {
        String comfort_id = order.getCar().getType().getId().toString();
        switch (comfort_id){
            case "1" : return COMFORT_ECONOMY_COST;
            case "2" : return COMFORT_BUSINESS_COST;
            case "3" : return COMFORT_LUX_COST;
            default: return COMFORT_ECONOMY_COST;
        }
    }

    private int calculateBirthdayReduction(User owner, int baseRate) {
        int birthdayCost = 0;
        if (LocalDate.now().equals(owner.getBirthday())) {
            birthdayCost = calculateReduction(BIRTHDAY_DISCOUNT, baseRate);
        }
        return birthdayCost;
    }

    private int calculateReduction(int reductionPercentage, int baseRate) {
        return reductionPercentage * baseRate / 100;
    }
}
