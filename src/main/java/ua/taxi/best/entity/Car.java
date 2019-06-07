package ua.taxi.best.entity;

public class Car {
    private Long id;
    private String licensePlate;
    private ComfortType type;
    private Integer productionYear;
    /**
     * available - meaning that the car is not busy and is ready to fulfill the order
     */
    private boolean available;
    private Model model;
    /**
     * arrivalTime - meaning the time during which the car will arrive to the client
     */
    private Integer arrivalTime;

    public Car() {
    }

    private Car(CarBuilder builder){
        this.id = builder.id;
        this.licensePlate = builder.licensePlate;
        this.type = builder.type;
        this.productionYear = builder.productionYear;
        this.available = builder.available;
        this.model = builder.model;
        this.arrivalTime = builder.arrivalTime;
    }

    public static CarBuilder builder(){
        return new CarBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public ComfortType getType() {
        return type;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public Model getModel() {
        return model;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public static class CarBuilder{
        private Long id;
        private String licensePlate;
        private ComfortType type;
        private Integer productionYear;
        private boolean available;
        private Model model;
        private Integer arrivalTime;

        private CarBuilder(){}

        public Car build(){
            return new Car(this);
        }

        public CarBuilder withId(Long id){
            this.id = id;
            return this;
        }

        public CarBuilder withLicensePlate(String licensePlate){
            this.licensePlate = licensePlate;
            return this;
        }

        public CarBuilder withType(ComfortType type){
            this.type = type;
            return this;
        }

        public CarBuilder withPoductionYear (Integer productionYear){
            this.productionYear = productionYear;
            return this;
        }

        public CarBuilder withAvailable(boolean available){
            this.available = available;
            return this;
        }

        public CarBuilder withModel(Model model){
            this.model = model;
            return this;
        }

        public CarBuilder withArrivalTime(Integer arrivalTime){
            this.arrivalTime = arrivalTime;
            return this;
        }
    }
}
