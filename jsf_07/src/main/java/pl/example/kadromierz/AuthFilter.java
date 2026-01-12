package pl.example.kadromierz;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Filtr chroniący zasoby /secure/*.
 * Jeśli użytkownik nie jest zalogowany (brak atrybutu w sesji lub AuthBean), następuje redirect do /login.xhtml.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && Boolean.TRUE.equals(session.getAttribute("loggedIn"));

        if (!loggedIn) {
            resp.sendRedirect(req.getContextPath() + "/login.xhtml");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() { }
}
