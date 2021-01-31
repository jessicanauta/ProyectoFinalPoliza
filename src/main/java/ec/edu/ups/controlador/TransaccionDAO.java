package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.Transaccion;

/**
 * Clase que me permite manejar la Entidad Transaccion con la Base de Datos
 * Es para el manejo de los datos registrados en la Base de datos con respecto a la Entidad Trnasaccion
 */

@Stateless
public class TransaccionDAO {

	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit")
	private EntityManager em;
	
	/**
	 * Metodo para registro de la Transaccion
	 * @param s El parametro s me permite registrar la Transaccion en la Base de Datos
	 */
	public void insert(Transaccion s) {
		em.persist(s);
	}
	
	/**
	 * Metodo para actualiza la Transaccion
	 * @param s El parametro s me permite actualiza la Transaccion con nuevos valores
	 */
	public void update(Transaccion s) {
		em.merge(s);
	}
	
	/**
	 * Metodo para buscar una Transaccion
	 * @param codigoTransaccion El parametro codigoTransaccion me permite buscar una Transaccion con dicho codigo.
	 * @return La transaccion con sus respectivos valores
	 */
	public Transaccion read(int codigoTransaccion) {
		return em.find(Transaccion.class, codigoTransaccion);
	}
	
	
	/**
	 * Metodo para eliminar un
	 * @param codT El parametro codT me permite eliminar una Transaccion con el codigo igual al parametro
	 */
	public void delete(int codT) {
		Transaccion c = read(codT);
		em.remove(c);
	}
	
	/**
	 * Metodo para obtener las Transacciones
	 * @param cedula El parametro cedula me permite obtener todas las transacciones realizadas por esa cedula perteneciente a un Cliente
	 * @return Una lista de Transacciones realizadas de acuerdo al parametro
	 * @throws Exception Excepcion para cuando no se encuentre ninguna transaccion que se haya realizado.
	 */
	public List<Transaccion> getListaTransacciones(String cedula) throws Exception {
		try {
			String jpql = "SELECT s FROM Transaccion s Where s.cliente.cedula =:ced";
			Query q = em.createQuery(jpql, Transaccion.class);
			q.setParameter("ced", cedula);
			return q.getResultList();
		} catch (NoResultException e) {
			// System.out.println(e.getMessage());
			throw new Exception("Credenciaales Inocorrectas");
		}

	}
	
	public List<Transaccion> getListaTransaccionesFechas(String cedula,String fechI , String fechaF ) throws Exception {
		//select * from transaccion where cedula_cliente = 0105011399 AND fecha BETWEEN '2020-06-03 20:21:40.090000' AND '2020-06-03 23:22:39.160000';
		String tl = "select s from Transaccion s where s.cliente.cedula = '"+cedula+"' AND s.fecha BETWEEN '"+fechI+"' AND '"+fechaF+"' ORDER BY s.fecha DESC";
		try {
			String jpql = tl;
			Query q = em.createQuery(jpql, Transaccion.class);
			//q.setParameter("ced", cedula);
			//q.setParameter("fcI", fechI);
			//q.setParameter("fcF", fechaF);
			return q.getResultList();
		} catch (NoResultException e) {
			throw new Exception("Erro Consultas Entre Fechas");
		}

	}
	

}
