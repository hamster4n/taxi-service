package ua.taxi.best.service.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class BusinessConstant {
    /**
     * BASE_RATE - tariff before bonuses and additional options (UAH/km)
     */
    public static final int BASE_RATE = 5;
    /**
     * BIRTHDAY_DISCOUNT - fixed discount for the user on his birthday
     */
    public static final int BIRTHDAY_DISCOUNT = 10;
    /**
     * BIRTHDAY_DISCOUNT - fixed price for delivering the car to the customer
     */
    public static final int AUTO_FEED = 50;
    /**
     * COMFORT_ECONOMY_COST - fixed cost of using economy class car
     */
    public static final int COMFORT_ECONOMY_COST = 0;
    /**
     * COMFORT_BUSINESS_COST - fixed cost of using a business class car
     */
    public static final int COMFORT_BUSINESS_COST = 50;
    /**
     * COMFORT_LUX_COST - fixed cost of using a lux class car
     */
    public static final int COMFORT_LUX_COST = 100;


    /**
     * TRAVEL_DISTANCE_FOR_***_LVL_LOYALTY - fixed distance for which the extra bonus is charged
     */
    private static final int TRAVEL_DISTANCE_FOR_FIRST_LVL_LOYALTY = 100;
    private static final int TRAVEL_DISTANCE_FOR_SECOND_LVL_LOYALTY = 1000;
    private static final int TRAVEL_DISTANCE_FOR_THIRD_LVL_LOYALTY = 5000;
    private static final int TRAVEL_DISTANCE_FOR_FOURTH_LVL_LOYALTY = 10000;
    private static final int TRAVEL_DISTANCE_FOR_FIFTH_LVL_LOYALTY = 25000;

    private static final int FIRST_LVL_LOYALTY = 1;
    private static final int SECOND_LVL_LOYALTY = 2;
    private static final int THIRD_LVL_LOYALTY = 3;
    private static final int FOURTH_LVL_LOYALTY = 4;
    private static final int FIFTH_LVL_LOYALTY = 5;

    public static Map<Integer, Integer> getLoyaltyMap() {
        Map<Integer, Integer> loyaltyMap = new LinkedHashMap<>();
        loyaltyMap.put(FIRST_LVL_LOYALTY, TRAVEL_DISTANCE_FOR_FIRST_LVL_LOYALTY);
        loyaltyMap.put(SECOND_LVL_LOYALTY, TRAVEL_DISTANCE_FOR_SECOND_LVL_LOYALTY);
        loyaltyMap.put(THIRD_LVL_LOYALTY, TRAVEL_DISTANCE_FOR_THIRD_LVL_LOYALTY);
        loyaltyMap.put(FOURTH_LVL_LOYALTY, TRAVEL_DISTANCE_FOR_FOURTH_LVL_LOYALTY);
        loyaltyMap.put(FIFTH_LVL_LOYALTY, TRAVEL_DISTANCE_FOR_FIFTH_LVL_LOYALTY);
        return loyaltyMap;
    }
}
