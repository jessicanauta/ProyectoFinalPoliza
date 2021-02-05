package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.Poliza;




@Stateless
public class PolizaDAO {
	
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit") 
	private EntityManager em;
	
	
	public void insert(Poliza s) {
		em.persist(s);
	}
	
	public void update(Poliza s) {
		em.merge(s);
	} 
	
	public Poliza read(int codigoPoliza) {
		return em.find(Poliza.class, codigoPoliza);
	} 
	
	public void delete(int codigoPoliza) {
		Poliza c = read(codigoPoliza);
		em.remove(c);
	}
	
	public List<Poliza> getPoliza() {
		String jpql = "SELECT s FROM Poliza s ";

		Query q = em.createQuery(jpql, Poliza.class);
		return q.getResultList();
	}
	
	

}
