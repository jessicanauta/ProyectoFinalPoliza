package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.Credito;


/**
 * Clase que me permite manejar la Entidad Credito con la Base de Datos
 * Es para el manejo de los datos registrados en la Base de datos con respecto a la Entidad Credito
 * @author Malki Yupanki
 * @version 1.0
 */
@Stateless
public class CreditoDAO {
	
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit") 
	private EntityManager em;
	
	/**
	 * Metodo para guardar un Credito
	 * @param s El parametro s me permite asignar los datos del credito
	 */
	public void insert(Credito s) {
		em.persist(s);
	}
	
	/**
	 *Metodo para actualizar el credito
	 * @param s El parametro s me permite asignar los nuevos valores 
	 * a un credito
	 */
	public void update(Credito s) {
		em.merge(s);
	} 
	
	/**
	 * Metodo para obtener un credito
	 * @param codigoCredito El parametro codigocredito me permite obtener el credito con el codigo
	 * igual al paremetro 
	 * @returnn Un credito
	 */
	public Credito read(int codigoCredito) {
		return em.find(Credito.class, codigoCredito);
	} 
	
	/**
	 * Metodo para eliminar un credito
	 * @param codigoCredito
	 * El parametro codigocredito me permite eliminar el credito con el codigo
	 * igual al paremetro 
	 */
	public void delete(int codigoCredito) {
		Credito c = read(codigoCredito);
		em.remove(c);
	}
	
	/**
	 * Metodo para obtener los creditos de la aplicacion
	 * @return Una lista de creditos
	 */
	public List<Credito> getCreditos() {
		String jpql = "SELECT s FROM Credito s ";

		Query q = em.createQuery(jpql, Credito.class);
		return q.getResultList();
	}

}
