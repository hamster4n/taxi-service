package ua.taxi.best.service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.taxi.best.entity.Order;
import ua.taxi.best.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    private static final List<Order> ORDER_LIST = new ArrayList<>();
    private static final Order ORDER = Order.builder().withId(100L).build();
    private static final int NUMBER_ROWS = 5;
    private static final long USER_ID = 1L;
    private static final int CURRENT_PAGE = 1;
    private static final int RECORDS_PER_PAGE = 5;

    @Mock
    private OrderRepository repository;
    @InjectMocks
    private OrderServiceImpl service;

    @BeforeClass
    public static void init() {
        ORDER_LIST.add(Order.builder().withId(1L).build());
        ORDER_LIST.add(Order.builder().withId(2L).build());
        ORDER_LIST.add(Order.builder().withId(3L).build());
        ORDER_LIST.add(Order.builder().withId(4L).build());
        ORDER_LIST.add(Order.builder().withId(5L).build());
    }

    @Test
    public void methodFindAllByUserIdShouldReturnOrdersByUserID() {
        when(repository.findAllByUserId(USER_ID)).thenReturn(ORDER_LIST);

        int actual = service.findAllByUserId(USER_ID).size();
        int expected = ORDER_LIST.size();

        assertEquals(expected, actual);
    }

    @Test
    public void methodFindByPaginationByUserIdShouldReturnRecordsPerPage() {
        when(repository.findByPaginationByUserId(USER_ID, CURRENT_PAGE, RECORDS_PER_PAGE)).thenReturn(ORDER_LIST);

        int actual = service.findByPaginationByUserId(USER_ID, CURRENT_PAGE, RECORDS_PER_PAGE).size();
        int expected = RECORDS_PER_PAGE;

        assertEquals(expected, actual);
    }

    @Test
    public void methodGetNumberOfRowsByUserIdShouldReturnNumbersRowsFromDb() {
        when(repository.getNumberOfRowsByUserId(USER_ID)).thenReturn(NUMBER_ROWS);

        int actual = service.getNumberOfRowsByUserId(USER_ID);
        int expected = ORDER_LIST.size();

        assertEquals(expected, actual);
    }

    @Test
    public void methodAddShouldCallRepo(){
        service.add(ORDER);

        verify(repository, times(1)).add(ORDER);
    }
}