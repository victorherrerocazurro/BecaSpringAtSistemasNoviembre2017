package com.atsistemas.persistencia.core.persistencia.hibernate;

import java.util.List;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.atsistemas.persistencia.core.entidades.Cliente;
import com.atsistemas.persistencia.core.persistencia.ClienteDao;

@Repository
@Transactional
public class ClienteHibernateDao extends HibernateDaoSupport implements ClienteDao {

	@Autowired
	public ClienteHibernateDao(SessionFactory sessionFactory) {
		super();
		setSessionFactory(sessionFactory);
	}
	
	/*@Autowired
	public ClienteHibernateDao(HibernateTemplate hibernateTemplate) {
		super();
		setHibernateTemplate(hibernateTemplate);
	}*/

	@Override
	public List<Cliente> findAll() {
		//HQL
		//List<?> find = getHibernateTemplate().find("select c from Cliente c");
		List<Cliente> list = getSessionFactory().getCurrentSession()
				.createQuery("select c from Cliente c", Cliente.class).list();
		return list;
	}

	@Override
	public Cliente getOne(Integer id) {
		//HQL
		//Cliente cliente = getHibernateTemplate().get(Cliente.class, id);
		Query<Cliente> query = getSessionFactory().getCurrentSession()
				.createQuery("select c from Cliente c where c.id = :id", Cliente.class);
		query.setParameter("id", id);
		Cliente cliente = query.getSingleResult();
		return cliente;
	}

	@Override
	public List<Cliente> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
