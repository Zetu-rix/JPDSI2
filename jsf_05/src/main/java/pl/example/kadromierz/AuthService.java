package pl.example.kadromierz;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class AuthService {

    @Inject private JpaUtil jpa;

    public User authenticate(String username, String password) {
        EntityManager em = jpa.em();
        try {
            return em.createQuery(
                    "select u from User u where u.username = :un and u.password = :pw",
                    User.class
            ).setParameter("un", username)
             .setParameter("pw", password)
             .getResultStream()
             .findFirst()
             .orElse(null);
        } finally {
            em.close();
        }
    }
}
