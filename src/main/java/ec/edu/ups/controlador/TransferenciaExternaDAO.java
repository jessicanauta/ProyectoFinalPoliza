package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.TransferenciaExterna;

/** 
 * Esta clase me permite hacer las funciones basicas en una base de datos utilizando la clase TransferenciaExterna 
 */
@Stateless
public class TransferenciaExternaDAO {
	//Atributo de la clase
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit")
	private EntityManager em;
	/** 
	 * Metodo que permite registrar una Transferencia Externa en la base de datos
	 * @param t Transferencia Externa que se va a registrar en la base
	 
	 */
	public void insert(TransferenciaExterna t) {
		em.persist(t);
	}
	/** 
	 * Metodo que permite actualizar una Transferencia Externa en la base de datos
	 * @param t Transferencia Externa que se va a actualizar en la base
	 */
	public void update(TransferenciaExterna t) {
		em.merge(t);
	}
	/** 
	 * Metodo que permite obtener una Transferencia Externa de la base de datos
	 * @param codigoTransferenciaExterna  Codigo que se utilizara para obtener la Transferencia Externa
	 * @return Una Transferencia Externa que se encuentre registrado en la base
	 */
	public TransferenciaExterna read(int codigoTransferenciaExterna) {
		return em.find(TransferenciaExterna.class, codigoTransferenciaExterna);
	}
	/** 
	 * Metodo que permite eliminar una Transferencia Externa de la base de datos
	 * @param codigoTransferenciaExterna Codigo que se utiliza para poder eliminar la Transferencia Externa
	 */
	public void delete(int codigoTransferenciaExterna) {
		TransferenciaExterna t = read(codigoTransferenciaExterna);
		em.remove(t);
	}
	/** 
	 * Metodo que permite obtener las Transferencias Externas que estan registradas en la base de datos
	 * @return Lista de Transferencias Externas que estan registradas en la base de datos
	 */
	public List<TransferenciaExterna> getListaTransferenciasExterna() throws Exception {
		String jpql = "SELECT t FROM TransferenciaExterna";
		Query q = em.createQuery(jpql, TransferenciaExterna.class);
		return q.getResultList();

	}
}
