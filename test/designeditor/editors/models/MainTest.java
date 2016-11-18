package designeditor.editors.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MainTest {
	private static final String PERSISTENCE_UNIT_NAME = "designeditor";
	private static EntityManagerFactory factory;
	private static SimpleDateFormat date = new SimpleDateFormat("yyyymmddhhmmssSSS");

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		addSample();
		queryListSample();
		queryByPkSample();
		
		// 其他的sample请参照网页JPQL  https://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL#JPQL
		
	}

	private static void queryByPkSample() {

		System.out.println("##############queryByPkSample");
		EntityManager em = factory.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();
		// Query for a List of objects.
		Query query = em.createQuery("Select e FROM TProject e WHERE e.projectId = :projectId");
		query.setParameter("projectId", "2016011912011083212");
		TProject result = (TProject) query.getSingleResult();
		System.out.println("projectId= " + result.getProjectId());
		em.close();
	}

	private static void addSample() {
		System.out.println("##############addSample");
		EntityManager em = factory.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		for (int i = 0; i < 40; i++) {
			TProject project = new TProject();
			project.setProjectId(date.format(new Date()) + i);
			project.setProjectIdName("TEST_PROJECT_ID_NAME" + i);
			em.persist(project);
		}

		em.getTransaction().commit();
		em.close();

	}

	private static void queryListSample() {

		System.out.println("##############queryListSample");
		EntityManager em = factory.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();
		// Query for a List of objects.
		Query query = em.createQuery("Select e FROM TProject e WHERE e.projectId > 2016");
		List<TProject> result = query.getResultList();
		for (TProject projetc : result) {
			System.out.println("projectId= " + projetc.getProjectId());
		}
		em.getTransaction().commit();
		em.close();
	}
}