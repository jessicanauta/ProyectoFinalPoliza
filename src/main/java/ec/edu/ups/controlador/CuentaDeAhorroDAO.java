package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.CuentaDeAhorro;

/** 
 * Esta clase me permite hacer las funciones basicas en una base de datos utilizando la clase CuentaDeAhorro
 * @version 1.0
 */
@Stateless
public class CuentaDeAhorroDAO {
	//Atributo de la clase
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit")  
	private EntityManager em;
	
	/** 
	 * Metodo que permite registrar una cuenta de ahorro en la base de datos
	 * @param c Cuenta que se registrar
	 */
	public void insert(CuentaDeAhorro c) {
		em.persist(c);
	}
	
	/**  
	 *  Metodo que permite actualizar una cuenta de ahorro en la base de datos
	 * @param c Cuenta que se actualiza
	 */
	public void update(CuentaDeAhorro c) {
		em.merge(c);
	}
	
	/** 
	 * Metodo que permite obtener una cuenta de ahorro de la base de datos
	 * @param numeroCuentaDeAhorro Numero de la cuenta que se busca
	 * @return una cuenta de ahorro que este registrada en la base
	 */
	public CuentaDeAhorro read(String numeroCuentaDeAhorro) {
		return em.find(CuentaDeAhorro.class, numeroCuentaDeAhorro);
	}
	
	/** 
	 * Metodo que permite eliminar una cuenta de ahorro de la base de datos
	 * @param numeroCuentaDeAhorro Numero de la cuenta que queremos eliminar
	 */
	public void delete(String numeroCuentaDeAhorro) {
		CuentaDeAhorro c = read(numeroCuentaDeAhorro);
		em.remove(c);
	}
	
	/** 
	 * Metodo que permite obtener las cuentas de ahorro que estan registrados en la base de datos
	 * @return Lista de cuentas de ahorros que estan registradas en la base de datos
	 */
	public List<CuentaDeAhorro> getCuentaDeAhorros() {
		String jpql = "SELECT c FROM CuentaDeAhorro c ";

		Query q = em.createQuery(jpql, CuentaDeAhorro.class);
		return q.getResultList();
	}  
	
	/** 
	 * Metodo que permite obtener una cuenta de ahorro en base a su codigo registrado en la base de datos 
	 * @param cedulaCliente Cedula del cliente que queremos buscar
	 * @return Cuenta de ahorro que tenga un cliente registrado en la base
	 */
	public CuentaDeAhorro getCuentaCedulaCliente(String cedulaCliente) {
		String jpql = "SELECT c FROM CuentaDeAhorro c WHERE c.cliente.cedula = :cedulaCliente";
		Query q = em.createQuery(jpql, CuentaDeAhorro.class);  
		q.setParameter("cedulaCliente",cedulaCliente);
		CuentaDeAhorro cuentaDeAhorro = (CuentaDeAhorro)q.getSingleResult();
		return cuentaDeAhorro;
	}  
	
}
