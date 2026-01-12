package pl.example.kadromierz;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    private JpaUtil jpa;

    public List<User> findAll() {
        EntityManager em = jpa.em();
        try {
            return em.createQuery("select u from User u order by u.id", User.class).getResultList();
        } finally {
            em.close();
        }
    }

    public User find(int id) {
        EntityManager em = jpa.em();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
}
