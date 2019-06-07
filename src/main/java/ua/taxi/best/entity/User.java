package ua.taxi.best.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String creditCard;
    /**
     * profileBonus - means whether the user received a bonus for completing the profile
     */
    private boolean profileBonus;
    private int discount;
    private int traveledDistance;
    private int loyalty;
    private LocalDate birthday;
    private Role role;
    private List<Order> orders;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public int getDiscount() {
        return discount;
    }

    public boolean getProfileBonus() {
        return profileBonus;
    }

    public int getTraveledDistance() {
        return traveledDistance;
    }

    public void setTraveledDistance(int traveledDistance) {
        this.traveledDistance = traveledDistance;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setProfileBonus(boolean profileBonus) {
        this.profileBonus = profileBonus;
    }

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.creditCard = builder.creditCard;
        this.profileBonus = builder.profileBonus;
        this.discount = builder.discount;
        this.traveledDistance = builder.traveledDistance;
        this.loyalty = builder.loyalty;
        this.birthday = builder.birthday;
        this.role = builder.role;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return discount == user.discount &&
                loyalty == user.loyalty &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(creditCard, user.creditCard) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    public static class UserBuilder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String creditCard;
        private boolean profileBonus;
        private int discount;
        private int traveledDistance;
        private int loyalty;
        private LocalDate birthday;
        private Role role;

        private UserBuilder() {
        }

        public User build() {
            return new User(this);
        }

        public UserBuilder withId(Long id){
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withEmail(String email){
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password){
            this.password = password;
            return this;
        }

        public UserBuilder withCreditCard(String creditCard){
            this.creditCard = creditCard;
            return this;
        }

        public UserBuilder withProfileBonus(boolean profileBonus){
            this.profileBonus = profileBonus;
            return this;
        }

        public UserBuilder withDiscount(int discount){
            this.discount = discount;
            return this;
        }

        public UserBuilder withTraveledDistance(int traveledDistance){
            this.traveledDistance = traveledDistance;
            return this;
        }

        public UserBuilder withLoyalty(int loyalty){
            this.loyalty = loyalty;
            return this;
        }

        public UserBuilder withBirthday(LocalDate birthday){
            this.birthday = birthday;
            return this;
        }

        public UserBuilder withRole(Role role){
            this.role = role;
            return this;
        }
    }
}
