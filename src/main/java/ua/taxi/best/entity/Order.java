package ua.taxi.best.entity;

import java.time.LocalDate;

public class Order {
    private Long id;
    private LocalDate date;
    /**
     * startDescription - meaning the address to which the client called the car
     */
    private String startDescription;
    /**
     * finishDescription - meaning the address that is the end point of the trip
     */
    private String finishDescription;
    private Integer distance;
    private Car car;
    /**
     * baseCost - meaning the cost of the trip before the accrual of bonuses, discounts and additional options
     */
    private Integer baseCost;
    /**
     * finalCost - meaning the cost of the trip after the accrual of bonuses, discounts and additional options
     */
    private Integer finalCost;
    /**
     * loyaltyCost - meaning loyalty discount sum
     */
    private Integer loyaltyCost;
    /**
     * discountCost - meaning discount sum
     */
    private Integer discountCost;
    /**
     * birthdayCost - meaning birthday discount sum
     */
    private Integer birthdayCost;
    /**
     * autoFeed - meaning the cost of delivering the car to the customer
     */
    private Integer autoFeed;
    /**
     * comfortCost - meaning the amount of charges for the use of more comfortable cars
     */
    private Integer comfortCost;
    private User customer;

    public Order() {
    }

    private Order(OrderBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.startDescription = builder.startDescription;
        this.finishDescription = builder.finishDescription;
        this.distance = builder.distance;
        this.car = builder.car;
        this.baseCost = builder.baseCost;
        this.finalCost = builder.finalCost;
        this.loyaltyCost = builder.loyaltyCost;
        this.discountCost = builder.discountCost;
        this.birthdayCost = builder.birthdayCost;
        this.autoFeed = builder.autoFeed;
        this.comfortCost = builder.comfortCost;
        this.customer = builder.owner;
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStartDescription() {
        return startDescription;
    }

    public String getFinishDescription() {
        return finishDescription;
    }

    public Integer getDistance() {
        return distance;
    }

    public Car getCar() {
        return car;
    }

    public Integer getBaseCost() {
        return baseCost;
    }

    public Integer getFinalCost() {
        return finalCost;
    }

    public Integer getLoyaltyCost() {
        return loyaltyCost;
    }

    public Integer getDiscountCost() {
        return discountCost;
    }

    public Integer getBirthdayCost() {
        return birthdayCost;
    }

    public Integer getAutoFeed() {
        return autoFeed;
    }

    public Integer getComfortCost() {
        return comfortCost;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setBaseCost(Integer baseCost) {
        this.baseCost = baseCost;
    }

    public void setFinalCost(Integer finalCost) {
        this.finalCost = finalCost;
    }

    public void setLoyaltyCost(Integer loyaltyCost) {
        this.loyaltyCost = loyaltyCost;
    }

    public void setDiscountCost(Integer discountCost) {
        this.discountCost = discountCost;
    }

    public void setBirthdayCost(Integer birthdayCost) {
        this.birthdayCost = birthdayCost;
    }

    public void setAutoFeed(Integer autoFeed) {
        this.autoFeed = autoFeed;
    }

    public void setComfortCost(Integer comfortCost) {
        this.comfortCost = comfortCost;
    }

    public static class OrderBuilder {
        private Long id;
        private LocalDate date;
        private String startDescription;
        private String finishDescription;
        private Integer distance;
        private Car car;
        private Integer baseCost;
        private Integer finalCost;
        private Integer loyaltyCost;
        private Integer discountCost;
        private Integer birthdayCost;
        private Integer autoFeed;
        private Integer comfortCost;
        private User owner;

        private OrderBuilder() {
        }

        public Order build() {
            return new Order(this);
        }

        public OrderBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public OrderBuilder withStartDescription(String startDescription) {
            this.startDescription = startDescription;
            return this;
        }

        public OrderBuilder withFinishDescription(String finishDescription) {
            this.finishDescription = finishDescription;
            return this;
        }

        public OrderBuilder withDistance(Integer distance) {
            this.distance = distance;
            return this;
        }

        public OrderBuilder withCar(Car car) {
            this.car = car;
            return this;
        }

        public OrderBuilder withBaseCost(Integer baseCost) {
            this.baseCost = baseCost;
            return this;
        }

        public OrderBuilder withFinalCost(Integer finalCost) {
            this.finalCost = finalCost;
            return this;
        }

        public OrderBuilder withLoyaltyCost(Integer loyaltyCost) {
            this.loyaltyCost = loyaltyCost;
            return this;
        }

        public OrderBuilder withDiscountCost(Integer discountCost) {
            this.discountCost = discountCost;
            return this;
        }

        public OrderBuilder withBirthdayCost(Integer birthdayCost) {
            this.birthdayCost = birthdayCost;
            return this;
        }

        public OrderBuilder withAutoFeed(Integer autoFeed) {
            this.autoFeed = autoFeed;
            return this;
        }

        public OrderBuilder withComfortCost(Integer comfortCost) {
            this.comfortCost = comfortCost;
            return this;
        }

        public OrderBuilder withOwner(User owner) {
            this.owner = owner;
            return this;
        }
    }
}
