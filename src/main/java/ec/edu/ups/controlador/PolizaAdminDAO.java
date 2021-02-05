package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.PolizaAdmin;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PolizaAdminDAO {

	
	
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit") 
	private EntityManager em;
	
	private PolizaAdmin ap;
	

	public boolean crearPolizaAdmin(PolizaAdmin entity) {
		em.persist(entity);
		return true;
	}

	
	public void actualizarPolizaAdmin(PolizaAdmin poladm) {
		//String jpql = "UPDATE PolizaAdmin p set p.timepoInicio=:timepoInicio,p.timepoInicio=:timepoInicio,p.timepoInicio=:timepoInicio WHERE p.id=:id";
		//PolizaAdmin pl=em.find(PolizaAdmin.class,id);
		//Query query=em.createQuery(jpql, PolizaAdmin.class);
		//pl.setInteres(ap.getInteres());
		//pl.setTimepoInicio(ap.getTimepoInicio());
		//pl.setTimepoFin(ap.getTimepoFin());
		em.merge(poladm);
		//return pl;
	}

	public PolizaAdmin consultarPolizaAdmin(String parametrizar) {
		return em.find(PolizaAdmin.class, parametrizar);
	}

	
	public List<PolizaAdmin> listarPolizaAdm() {
		String jpql = "SELECT p FROM PolizaAdmin p";
 		Query query = em.createQuery(jpql, PolizaAdmin.class);
		return query.getResultList();
	}
}
