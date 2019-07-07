package io.ryanluoxu.orderbook.common.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * For GenericDaoImpl
 * @author luoxu
 *
 */
public class JpaUtil {
	
	private static EntityManagerFactory emFactory;

	static{
		emFactory = Persistence.createEntityManagerFactory("myJPA");
	}

	public static EntityManager createEntityManager(){
		return emFactory.createEntityManager();
	}

	/**
	 * run this to create tables
	 * @param args
	 */
	public static void main(String[] args) {
		createEntityManager();
	}

}
