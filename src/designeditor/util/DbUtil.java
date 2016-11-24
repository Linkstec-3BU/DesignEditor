package designeditor.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import designeditor.editors.constant.ConstantManager;

/**
 * 应该用singleton模式
 * @author LKG
 *
 */
public class DbUtil {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static EntityManager init() {
		entityManagerFactory = Persistence.createEntityManagerFactory(ConstantManager.PERSISTENCE_UNIT_NAME);
		entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}

	public static void close() {
		entityManager.close();
		entityManagerFactory.close();
	}

	public static void save(Object obj) {
		entityManager.merge(obj);
	}

	public static void executeUpdateSql(String sql, String[] parameters) {
		Query query = entityManager.createQuery(sql);
		for (int i = 1; i <= parameters.length; i++) {
			query.setParameter(i, parameters[i - 1]);
		}
		query.executeUpdate();
	}

	public static Object selectByOthers(String sql, Object[] parameters) {
		Query query = entityManager.createQuery(sql);
		for (int i = 1; i <= parameters.length; i++) {
			query.setParameter(i, parameters[i - 1]);
		}
		Object obj = query.getResultList();
		return obj;
	}

	public static Object selectByKey(Object obj, String key) {
		Object result = entityManager.find(obj.getClass(), key);
		return result;
	}

	public static List<?> selectAll(Object obj) {
		return entityManager.createQuery("select c from " + obj.getClass().getName() + " c", obj.getClass())
				.getResultList();
	}

}
