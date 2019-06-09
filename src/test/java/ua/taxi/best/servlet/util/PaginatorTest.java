package ua.taxi.best.servlet.util;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PaginatorTest {

    private static final int PARAMETER_VALUE_ONE = 1;
    private static final int PARAMETER_VALUE_FIVE = 5;

    @Mock
    private HttpServletRequest req;

    @Test
    public void methodGetCurrentPageShouldReturnOne() {
        when(req.getParameter("currentPage")).thenReturn("1");

        int currentPage = Paginator.getCurrentPage(req);

        assertEquals(PARAMETER_VALUE_ONE, currentPage);
    }

    @Test
    public void methodGetRecordsPerPageShouldReturnFive() {
        when(req.getParameter("currentPage")).thenReturn("5");

        int currentPage = Paginator.getRecordsPerPage(req);

        assertEquals(PARAMETER_VALUE_FIVE, currentPage);
    }
}