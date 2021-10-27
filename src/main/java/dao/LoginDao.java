package dao;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;

import entities.Usuarios;
import utils.HibernateUtil;

public class LoginDao {

	static Session s = HibernateUtil.getSessionFactory().openSession();

	private static Logger logger = LogManager.getLogger(LoginDao.class);


	public static boolean checkUserLogin (String uname,String pass) {

		boolean login = false;

		if ( uname != null) {

			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			URL url = loader.getResource("log4j.properties");
			PropertyConfigurator.configure(url);

			Transaction tx = null;

			try {
				tx = s.beginTransaction();

				String hQuery = "from Usuarios";
				List<Usuarios> usuariosList = s.createQuery(hQuery, Usuarios.class).getResultList();				

				for (Usuarios usuarios : usuariosList) {

					if (usuarios.getEmail().equals(uname) && usuarios.getClave().equals(pass)) {
						login = true;
					}
				}
				tx.commit();

			} catch (HibernateException e){
				if (tx != null) {
					tx.rollback();   

					logger.error("Error al acceder a la BD" + e);
				}
			}

		} else {

			logger.error("El codigo no existe en la base de datos ");

		}

		return login;
	}


}
