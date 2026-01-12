package pl.example.kadromierz;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ShiftService {

    @Inject
    private JpaUtil jpa;

    public List<Shift> findByUser(int userId) {
        EntityManager em = jpa.em();
        try {
            return em.createQuery(
                    "select sh from Shift sh where sh.user.id = :uid order by sh.startTime desc",
                    Shift.class
            ).setParameter("uid", userId).getResultList();
        } finally {
            em.close();
        }
    }

    public Shift startShift(int userId) {
        EntityManager em = jpa.em();
        try {
            em.getTransaction().begin();
            User u = em.find(User.class, userId);
            Shift sh = new Shift();
            sh.setUser(u);
            sh.setStartTime(LocalDateTime.now());
            em.persist(sh);
            em.getTransaction().commit();
            return sh;
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public Shift endShift(int shiftId) {
        EntityManager em = jpa.em();
        try {
            em.getTransaction().begin();
            Shift sh = em.find(Shift.class, shiftId);
            if (sh != null && sh.getEndTime() == null) {
                sh.setEndTime(LocalDateTime.now());
            }
            em.getTransaction().commit();
            return sh;
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
