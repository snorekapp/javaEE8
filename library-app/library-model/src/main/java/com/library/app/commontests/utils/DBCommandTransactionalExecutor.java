package com.library.app.commontests.utils;

import javax.persistence.EntityManager;

import org.junit.Ignore;

@Ignore
public class DBCommandTransactionalExecutor {

	private EntityManager em;

	public DBCommandTransactionalExecutor(final EntityManager em) {
		this.em = em;
	}

	public <T> T executeCommand(final DBCommand<T> dbCommand) {

		try {
			em.getTransaction().begin();
			final T toReturn = dbCommand.execute();
			em.getTransaction().commit();
			em.clear();
			return toReturn;
		} catch (final Exception e) {
			e.printStackTrace();
			;
			em.getTransaction().rollback();
			throw new IllegalStateException(e);
		}

		// try {
		// em.getTransaction().begin();
		// categoryAddedId = categoryRepository.add(java()).getId();
		// assertThat(categoryAddedId, is(notNullValue()));
		// em.getTransaction().commit();
		// em.clear();
		// } catch (final Exception e) {
		// fail("This exception should not have been thrown");
		// e.printStackTrace();
		// em.getTransaction().rollback();
		// }

	}

}
