package ua.taxi.best.servlet.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PaginatorParameterizedTest {

    private int numberOfRows;
    private int recordsPerPage;
    private int expected;

    public PaginatorParameterizedTest(int numberOfRows, int recordsPerPage, int expected) {
        this.numberOfRows = numberOfRows;
        this.recordsPerPage = recordsPerPage;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> stepUpCoefficientTableValues() {
        return Arrays.asList(new Object[][]{
                {2, 3, 1},
                {6, 3, 2},
                {19, 3, 7},
                {4, 5, 1},
                {10, 5, 2},
                {21, 5, 5}
        });
    }

    @Test
    public void methodGetNumberOfPagesShouldReturnFor() {

        int expected = this.expected;

        int actual = Paginator.getNumberOfPages(this.numberOfRows, this.recordsPerPage);

        assertEquals(expected, actual);
    }

}