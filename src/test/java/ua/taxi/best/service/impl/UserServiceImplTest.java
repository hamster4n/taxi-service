package ua.taxi.best.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.taxi.best.entity.User;
import ua.taxi.best.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static User USER_NOT_FILL_PROFILE =
            User.builder()
                    .withProfileBonus(false)
                    .withName("")
                    .withDiscount(3)
                    .withTraveledDistance(1000)
                    .build();

    private static User USER_FILL_PROFILE_HAVNT_BONUS =
            User.builder()
                    .withProfileBonus(false)
                    .withName("Ivan")
                    .withCreditCard("1111111111")
                    .withDiscount(3)
                    .withTraveledDistance(150)
                    .build();

    private static User USER =
            User.builder()
                    .withProfileBonus(false)
                    .withName("Ivan")
                    .withCreditCard("1111111111")
                    .withDiscount(3)
                    .withTraveledDistance(150)
                    .build();

    private static final int UPDATED_DISCOUNT = 4;
    private static final int NOT_UPDATED_DISCOUNT = 3;
    private static final int FIRST_LVL_LOYALTY = 1;
    private static final int SECOND_LVL_LOYALTY = 2;

    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserServiceImpl service;


    @Test
    public void methodUpdateShouldIncrementDiscount() {
        service.update(USER_FILL_PROFILE_HAVNT_BONUS);

        int actual = USER_FILL_PROFILE_HAVNT_BONUS.getDiscount();

        assertEquals(UPDATED_DISCOUNT, actual);
    }

    @Test
    public void methodUpdateShouldNoIncrementDiscount() {
        service.update(USER_NOT_FILL_PROFILE);

        int actual = USER_NOT_FILL_PROFILE.getDiscount();

        assertEquals(NOT_UPDATED_DISCOUNT, actual);
    }

    @Test
    public void methodUpdateLoyaltyShouldCalculateSecondLvlLoyalty() {
        service.updateLoyalty(USER_NOT_FILL_PROFILE);

        int actual = USER_NOT_FILL_PROFILE.getLoyalty();

        assertEquals(SECOND_LVL_LOYALTY, actual);
    }

    @Test
    public void methodUpdateLoyaltyShouldCalculateFirstLvlLoyalty() {
        service.updateLoyalty(USER_FILL_PROFILE_HAVNT_BONUS);

        int actual = USER_FILL_PROFILE_HAVNT_BONUS.getLoyalty();

        assertEquals(FIRST_LVL_LOYALTY, actual);
    }

    @Test
    public void methodUpdateLoyaltyShouldCallRepo() {
        service.updateLoyalty(USER);

        verify(repository, times(1)).updateLoyalty(USER);
    }
}