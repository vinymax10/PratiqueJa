package Infra;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

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
