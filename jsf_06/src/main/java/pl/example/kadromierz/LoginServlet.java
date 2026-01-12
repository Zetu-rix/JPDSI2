package pl.example.kadromierz;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Inject private AuthService authService;
    @Inject private AuthBean authBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User u = authService.authenticate(username, password);
        if (u != null) {
            authBean.login(u);
            // zaznacz w sesji (wymóg: obiekt sesji)
            req.getSession().setAttribute("loggedIn", true);
            resp.sendRedirect(req.getContextPath() + "/secure/user.xhtml");
        } else {
            req.setAttribute("error", "Błędny login lub hasło");
            req.getRequestDispatcher("/login.xhtml").forward(req, resp);
        }
    }
}
