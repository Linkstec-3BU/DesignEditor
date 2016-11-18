package designeditor.editors.models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainTest {
	private static final String PERSISTENCE_UNIT_NAME = "designeditor";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		for (int i = 0; i < 40; i++) {
			Project project = new Project();
			project.setProjectId("TEST_PROJECT_ID" + i);
			project.setProjectIdName("TEST_PROJECT_ID_NAME" + i);
			em.persist(project);
			em.persist(project);
		}

		em.getTransaction().commit();

		em.close();
	}
}