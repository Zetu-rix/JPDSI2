package pl.example.kadromierz;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthBean implements Serializable {
    private Integer userId;
    private String username;
    private String role;

    public boolean isLoggedIn() { return userId != null; }

    public void login(User u) {
        this.userId = u.getId();
        this.username = u.getUsername();
        this.role = String.valueOf(u.getRole());
    }

    public void logout() {
        this.userId = null;
        this.username = null;
        this.role = null;
    }

    public Integer getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}
