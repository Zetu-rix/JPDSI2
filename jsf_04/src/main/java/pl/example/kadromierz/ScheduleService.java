package pl.example.kadromierz;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ScheduleService {

    @Inject
    private JpaUtil jpa;

    public List<Schedule> findByUser(int userId) {
        EntityManager em = jpa.em();
        try {
            return em.createQuery(
                    "select s from Schedule s where s.user.id = :uid order by s.shiftDate, s.shiftStart",
                    Schedule.class
            ).setParameter("uid", userId).getResultList();
        } finally {
            em.close();
        }
    }

    public Schedule add(int userId, Schedule s) {
        EntityManager em = jpa.em();
        try {
            em.getTransaction().begin();
            User u = em.find(User.class, userId);
            s.setUser(u);
            em.persist(s);
            em.getTransaction().commit();
            return s;
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
