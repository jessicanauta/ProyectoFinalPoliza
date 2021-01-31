package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.DetalleCredito;


/**
 * Clase que me permite manejar la Entidad Detalle de Credito con la Base de Datos
 * Es para el manejo de los datos registrados en la Base de datos con respecto a la Entidad DetalleCredito
 */
@Stateless
public class DetalleCreditoDAO {
	
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit") 
	private EntityManager em;
	
	/**
	 * Metodo para guardar un DetalleCredito
	 * @param s El parametro s me permite asignar los datos del Detalle Credito
	 */
	public void insert(DetalleCredito s) {
		em.persist(s);
	}
	
	/**
	 *Metodo para actualizar el Detalle Credito
	 * @param s El parametro s me permite asignar los nuevos valores 
	 * a un Detalle Credito
	 */
	public void update(DetalleCredito s) {
		em.merge(s);
	} 
	
	/**
	 * Metodo para obtener un Detalle Credito
	 * @param codigoCredito El parametro codigocredito me permite obtener el Detalle Credito con el codigo
	 * igual al paremetro 
	 * @returnn Un Detalle Credito
	 */
	public DetalleCredito read(int codigoCredito) {
		return em.find(DetalleCredito.class, codigoCredito);
	} 
	
	/**
	 * Metodo para eliminar un credito
	 * @param codigoCredito El parametro codigocredito me permite eliminar el Detalle Credito con el codigo
	 * igual al paremetro 
	 */
	public void delete(int codigoCredito) {
		DetalleCredito c = read(codigoCredito);
		em.remove(c);
	}
	
	/**
	 * Metodo para obtener los Detalle Credito de la aplicacion
	 * @return Una lista de Detalles de Creditos
	 */
	public List<DetalleCredito> getDetallesCreditos() {
		String jpql = "SELECT s FROM DetalleCredito s ";

		Query q = em.createQuery(jpql, DetalleCredito.class);
		return q.getResultList();
	}

}
