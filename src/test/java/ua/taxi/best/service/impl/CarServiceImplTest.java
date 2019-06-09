package ua.taxi.best.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.taxi.best.entity.Car;
import ua.taxi.best.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

    private static final List<Car> CAR_LIST = new ArrayList<>();
    private static final int NUMBER_ROWS = 5;
    private static final int CURRENT_PAGE = 1;
    private static final int RECORDS_PER_PAGE = 5;

    @Mock
    private CarRepository repository;
    @InjectMocks
    private CarServiceImpl service;

    @BeforeClass
    public static void init() {
        CAR_LIST.add(Car.builder().withId(1L).build());
        CAR_LIST.add(Car.builder().withId(2L).build());
        CAR_LIST.add(Car.builder().withId(3L).build());
        CAR_LIST.add(Car.builder().withId(4L).build());
        CAR_LIST.add(Car.builder().withId(5L).build());
    }

    @Test
    public void methodGetAllShouldReturnAllCars() {
        when(repository.findAll()).thenReturn(CAR_LIST);

        int actual = service.findAll().size();
        int expected = CAR_LIST.size();

        assertEquals(expected, actual);
    }

    @Test
    public void methodGetNumberOfRowsShouldReturnNumbersRowsFromDb() {
        when(repository.getNumberOfRows()).thenReturn(NUMBER_ROWS);

        int actual = service.getNumberOfRows();
        int expected = CAR_LIST.size();

        assertEquals(expected, actual);
    }

    @Test
    public void methodFindByPaginationShouldReturnRecordsPerPage() {
        when(repository.findByPagination(CURRENT_PAGE, RECORDS_PER_PAGE)).thenReturn(CAR_LIST);

        int actual = service.findByPagination(CURRENT_PAGE, RECORDS_PER_PAGE).size();
        int expected = RECORDS_PER_PAGE;

        assertEquals(expected, actual);
    }
}
