package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.SolicitudDeCredito;

/** 
 *  Esta clase me permite hacer las funciones basicas en una base de datos utilizando la clase SolicitudDeCredito
 */
@Stateless
public class SolicitudDeCreditoDAO {
	//Atributo de la clase
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit") 
	private EntityManager em;
	/** 
	 * Metodo que permite registrar una Solicitud de Credito en la base de datos
	 * @param s SolicitudDeCredito que se va a registrar en la base
	 */
	public void insert(SolicitudDeCredito s) {
		em.persist(s);
	}
	/** 
	 * Metodo que permite actualizar una Solicitud de Credito en la base de datos
	 * @param s Solicitud de Credito que se va a actualizar en la base
	 */
	public void update(SolicitudDeCredito s) {
		em.merge(s);
	} 
	/** 
	 * Metodo que permite obtener una Solicitud de Credito de la base de datos
	 * @param codigoCredito Codigo que se utilizara para obtener la Solicitud de Credito
	 * @return Una Solicitud de Credito que se encuentre registrado en la base
	 */
	public SolicitudDeCredito read(int codigoCredito) {
		return em.find(SolicitudDeCredito.class, codigoCredito);
	} 
	/** 
	 * Metodo que permite eliminar una Solicitud de Credito de la base de datos
	 * @param codigoCredito Codigo que se utiliza para poder eliminar la Solicitud de Credito
	 */
	public void delete(int codigoCredito) {
		SolicitudDeCredito c = read(codigoCredito);
		em.remove(c);
	}
	/** 
	 * Metodo que permite obtener las Solicitudes de Credito que estan registradas en la base de datos
	 * @return Lista de Solicitudes de Credito que estan registradas en la base de datos
	 */
	public List<SolicitudDeCredito> getSolicitudDeCreditos() {
		String jpql = "SELECT s FROM SolicitudDeCredito s ";

		Query q = em.createQuery(jpql, SolicitudDeCredito.class);
		return q.getResultList();
	} 
}
