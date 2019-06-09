package ua.taxi.best.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest extends Mockito {

    private static final String INVALID_EMAIL = "ivanov_34@";

    final LoginServlet servlet = new LoginServlet();
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

    @Test
    public void loginServletShouldBackToLoginPageWhenInvalidEmail() throws ServletException, IOException {
        when(request.getParameter("email")).thenReturn(INVALID_EMAIL);

        new LoginServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("email");
    }
}