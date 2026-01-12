package pl.example.kadromierz;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class JpaUtil {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("kadromierzPU");

    public EntityManager em() { return emf.createEntityManager(); }

    @PreDestroy
    public void shutdown() {
        if (emf != null && emf.isOpen()) emf.close();
    }
}
