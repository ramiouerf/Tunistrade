package edu.esprit.service.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.esprit.domain.entities.Admin;

/**
 * Session Bean implementation class ClientServiceEJB
 */
@Stateless
public class AdminServiceEJB implements AdminServiceEJBRemote,
		AdminServiceEJBLocal {

	/**
	 * Default constructor.
	 */
	@PersistenceContext(unitName = "Tunitrade1")
	private EntityManager em;

	public AdminServiceEJB() {

	}

	/**
 * 
 */
	@Override
	public void add(Admin admin) {
		em.persist(admin);

	}

	@Override
	public void update(Admin admin) {
		em.merge(admin);

	}

	@Override
	public Admin findBY(int id) {

		return em.find(Admin.class, id);
	}

	@Override
	public List<Admin> findall() {

		return em.createQuery("select a from admin a", Admin.class)
				.getResultList();
	}

	@Override
	public boolean delete(Admin admin) {
		em.remove(em.merge(admin));
		return true;

	}
	@Override
	public Admin login(String login, String password) {
		Admin found = null;
		String jpql = "select u from Admin u where u.login=:login and u.password=:password";
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		query.setParameter("password", password);
		try{
			found = (Admin) query.getSingleResult();
		}catch(Exception ex){
		}
		return found;
	}
	
}