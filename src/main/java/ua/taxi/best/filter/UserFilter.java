package ua.taxi.best.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/users/*", "/cars/*"})
public class UserFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.info("UserFilter start!");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session != null && "ADMIN".equals(session.getAttribute("role"))) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect("/order");
        }
    }

    @Override
    public void destroy() {
    }
}
