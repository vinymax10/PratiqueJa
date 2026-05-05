package Infra;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@ApplicationScoped
public class JPAUtil
{

	@PersistenceUnit(unitName = "PratiqueJa")
	private EntityManagerFactory factory;
//	
//    @Produces
//    @Dependent
//    public EntityManager newEntityManager() {
//        return factory.createEntityManager();
//    }
//
//    public void close(@Disposes EntityManager em) {
//        em.close();
//    }

}
