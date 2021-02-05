package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.Cliente;

@Stateless
public class ClienteDAO {
	
	//Atributo de la clase
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit") 
	private EntityManager em;
	
	/** 
	 * Metodo que permite registrar un cliente en la base de datos
	 * @param c Cliente que se va a registrar en la base
	 */
	public void insert(Cliente c) {
		em.persist(c);
	}
	
	/** 
	 * Metodo que permite actualizar un cliente en la base de datos
	 * @param c Cliente que se va a actualizar en la base
	 */
	public void update(Cliente c) {
		em.merge(c);
	} 
	
	/** 
	 * Metodo que permite obtener un cliente de la base de datos
	 * @param cedulaCliente Cedula que se utilizara para obtener el cliente
	 * @return un cliente que se encuentre registrado en la base
	 */
	public Cliente read(String cedulaCliente) {
		return em.find(Cliente.class, cedulaCliente);
	} 
	
	/** 
	 * Metodo que permite eliminar un cliente de la base de datos
	 * @param cedulaCliente Cedula utilizaremos para poder eliminar el cliente
	 */
	public void delete(String cedulaCliente) {
		Cliente c = read(cedulaCliente);
		em.remove(c);
	}
	
	/** 
	 * Metodo que permite obtener los clientes que estan registrados en la base de datos
	 * @return Lista de clientes que estan registrados en la base de datos
	 */
	public List<Cliente> getClientes() {
		String jpql = "SELECT c FROM Cliente c ";

		Query q = em.createQuery(jpql, Cliente.class);
		return q.getResultList();
	} 
	
	/** 
	 * Metodo que permite obtener un cliente de la base de datos en base a su usuario y contraseña 
	 * @param usuario Usuario que utilizaremos para poder obtener el cliente
	 * @param contra Contraseña que se utilizara para obtener el cliente
	 * @return Cliente que se encuentre en la base de datos con un usuario y contraseña en especifico. 
	 * @throws Exception
	 */
	public Cliente obtenerClienteUsuarioContraseña(String usuario,String contra) throws Exception {
		try {
			String jpl = "select c from Cliente c Where c.usuario =:usu AND c.clave =:contr";
			Query q = em.createQuery(jpl, Cliente.class);
			q.setParameter("usu", usuario);
			q.setParameter("contr", contra);
			return (Cliente)q.getSingleResult();
			
		} catch (NoResultException e) {
			//System.out.println(e.getMessage());
			 throw new Exception("Credenciaales Inocorrectas"); 
		}
		//return null;
	}  
	/** 
	 * Metodo que permite obtener un cliente dependiendo de su correo y cotraseña 
	 * @param correo Variable de tipo String en donde se asigna el correo de la persona que se desea obtener
	 * @param contra Variable de tipo String en donde se asigna la contraseña de la persona que se desea obtener
	 * @return Cliente que tenga la el correo y contraseña que se han pasado como paramatro
	 * @throws Exception
	 */
	public Cliente obtenerClienteCorreoContraseña(String correo,String contra) throws Exception {
		try {
			String jpl = "select c from Cliente c Where c.correo =:corr AND c.clave =:contr";
			Query q = em.createQuery(jpl, Cliente.class);
			q.setParameter("corr", correo);
			q.setParameter("contr", contra);
			return (Cliente)q.getSingleResult();
			
		} catch (NoResultException e) {
			//System.out.println(e.getMessage());
			 throw new Exception("Revisar datos de cambio"); 
		}
		//return null;
	}
	
	
}
