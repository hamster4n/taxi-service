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

@WebFilter(urlPatterns = {"/users/*", "/order/*", "/history/*", "/profile/*", "/cars/*"})
public class LoginFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        LOGGER.info("Login filter start!");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        check(filterChain, req, resp, session);
    }

    private void check(FilterChain filterChain, HttpServletRequest req, HttpServletResponse resp, HttpSession session)
            throws IOException, ServletException {
        if (session != null && session.getAttribute("email") != null
                && ("USER".equals(session.getAttribute("role")) || "ADMIN".equals(session.getAttribute("role")))) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
