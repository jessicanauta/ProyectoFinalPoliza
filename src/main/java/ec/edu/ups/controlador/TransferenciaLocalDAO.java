package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.TransfereciaLocal;

/** 
 * Esta clase me permite hacer las funciones basicas en una base de datos utilizando la clase TransferenciaLocal 
 */
@Stateless
public class TransferenciaLocalDAO {
	//Atributo de la clase
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit")
	private EntityManager em;
	/** 
	 * Metodo que permite registrar una Transferencia Local en la base de datos
	 * @param t Transferencia Local que se va a registrar en la base
	 
	 */
	public void insert(TransfereciaLocal t) {
		em.persist(t);
	}
	/** 
	 * Metodo que permite actualizar una Transferencia Local en la base de datos
	 * @param t Transferencia Local que se va a actualizar en la base
	 */
	public void update(TransfereciaLocal t) {
		em.merge(t);
	}
	/** 
	 * Metodo que permite obtener una Transferencia Local de la base de datos
	 * @param codigoTra  Codigo que se utilizara para obtener la Transferencia Local
	 * @return Una Transferencia Local que se encuentre registrado en la base
	 */
	public TransfereciaLocal read(int codigoTra) {
		return em.find(TransfereciaLocal.class, codigoTra);
	}
	/** 
	 * Metodo que permite eliminar una Transferencia Local de la base de datos
	 * @param codigoTra Codigo que se utiliza para poder eliminar la Transferencia Local
	 */
	public void delete(int codigoTra) {
		TransfereciaLocal c = read(codigoTra);
		em.remove(c);
	}
	/** 
	 * Metodo que permite obtener las Transferencias Locales que estan registradas en la base de datos
	 * @return Lista de Transferencias Locales que estan registradas en la base de datos
	 */
	public List<TransfereciaLocal> getTransfereciaLocals() {
		String jpql = "SELECT t FROM TransfereciaLocal t ";

		Query q = em.createQuery(jpql, TransfereciaLocal.class);
		return q.getResultList();
	}

}
