package ec.edu.ups.controlador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.modelo.Empleado;
/**
 * Clase que me permite manejar la Entidad Empleado con la Base de Datos
 * Es para el manejo de los datos registrados en la Base de datos con respecto a la Entidad Empleado

 */
@Stateless
public class EmpleadoDAO {
	
	@PersistenceContext(name = "ProyectoFinalPolizaPersistenceUnit") 
	private EntityManager con;
	
	/**
	 * Metodo para el registro de un Empleado
	 * @param emleado El parametro empleado me permite registrar un Empleado en la Base de Datos
	 * @return Si se logro registrar el Empleado TRUE o FALSE
	 */
	public boolean insertarEmpleado(Empleado emleado) {
		con.persist(emleado);
		return true;
	}
	
	/**
	 * Metodo para obtener el Empleado
	 * @param id El parametro id me permite obtener el Empleado 
	 * @return EL empleado de acuerdo al parametro id
	 */
	public Empleado obtenerEmpleado(String id) {
		 return con.find(Empleado.class, id);
	}
	
	
	/**
	 * Metodo para modificar el Empleado
	 * @param empleado El parametro empleado permite modificar el Empleado que se tienen en el parametro
	 * @return Si se logro modificar el Empleado TRUE o FALSE
	 */
	public boolean editar_Empleado(Empleado empleado) {
		con.merge(empleado);
		return true;
	}
	
	/**
	 * Metodo para obtener la lista de los Empleados
	 * @return La lista con todos los empleados de Instituion
	 */
	public List<Empleado> obtener() {
		String jpl = "select p from Empleado p";
		Query q = con.createQuery(jpl, Empleado.class);
		return q.getResultList();
	
	}
	
	/**
	 * Metodo para eliminar un Empleado
	 * @param per El parametro per me permite eliminar un Empleado de acuedor al parametro
	 */
	public void eliminarEmpleado(Empleado per) {
		Empleado p = obtenerEmpleado(per.getCedula());
		con.remove(p);

    }
	
	/**
	 * Metodo para ontener un Empleado
	 * @param usuario El parametro usuario me permite obtener el Empleado de acuerdo al parametro
	 * @param contra El parametro contra me permite obtener el Empleado de acuedo al parametro 
	 * @return El empleado de acuedo al los parametros usuario y contra
	 * @throws Exception Para cuando se ingresa las credenciales incorrectas
	 */
	public Empleado obtenerUsuario(String usuario,String contra) throws Exception {
		try {
			String jpl = "select p from Empleado p Where p.usuario =:usu AND p.contrasena =:contr";
			Query q = con.createQuery(jpl, Empleado.class);
			q.setParameter("usu", usuario);
			q.setParameter("contr", contra);
			return (Empleado)q.getSingleResult();
			
		} catch (NoResultException e) {
			//System.out.println(e.getMessage());
			 throw new Exception("Credenciaales Incorrectas"); 
		}
		//return null;
	}


}
