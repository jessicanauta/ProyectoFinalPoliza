package ec.edu.ups.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Clase representa un Entidad (tabla) en la Base de Datos
 * Es para el registro de Empleados de la Institucion
 */
@Entity
public class Empleado implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula")
	private String cedula;
	private String rol;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private String correo;
	private String usuario;
	private String contrasena;
	
	/**
	 * Contructor vacio de acuerdo a JPA
	 */
	public Empleado() {
	}
	
	/**
	 * Metodo para obtener el rol que tiene un Empleado
	 * @return Me duvuelve el rol asignado a cada empleado creado
	 */
	public String getRol() {
		return rol;
	}
	
	/**
	 * Metodo para asignar el rol del Empleado
	 * @param rol El parametro rol me permite asignar el rol del empleado
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	/**
	 * Metodo para obtener la cedula del Empleado
	 * @return El rol que se posee cada empleado
	 */
	public String getCedula() {
		return cedula;
	}
	
	/**
	 * Metodo para asignar la cedula al Empleado
	 * @param cedula El parametro cedula me permite asignar a cada empleado su cedula correspondiente
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	/**
	 * Metodo para obtener el Nombre del Empleado
	 * @return El nombre que posee cada Empleado
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo la asignacion del Nombre de Empleado
	 * @param nombre El parametro nombre me permite asignarle un Nombre al Empleado
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo para obtener el apellido
	 * @return El apellido asignado al cliente
	 */
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Metodo para asignar el apellido
	 * @param apellido El parametro apellido me asigna a cada Empleado su apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	/**
	 * Metodo para obtener la direccion del Empleado
	 * @return La direccion de cada empleado
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Metodo para asignar la direccion al Empleado
	 * @param direccion El parametro direccion me permite asignar la direccion de cada Empleado
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Metodo para obtener el telefono del Empleado
	 * @return El telefono que pertenece a cada empleado
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Metodo para asignar el teledono al Empleado
	 * @param telefono El parametro telefono me permite asignar un numero telefonico al Empleado
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Metodo para obtener el correo del Empleado
	 * @return El correo que pertenece al Empleado
	 */
	public String getCorreo() {
		return correo;
	}
	
	/**
	 * Metodo para asignar el correo al Empleado
	 * @param correo El parametro correo me permite asignar un correo al Empleado
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	/**
	 * Metodo para asignar el usuario al Empleado
	 * @return El usuario asignado al empleado para el su acceso
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Metodo para asignar el usuario al Empleado
	 * @param usuario El parametro usuario asigna el usuario con el que podra acceder a sus tareas
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Metodo para obtener la contraseña del Empleado
	 * @return La contraseña asignada al Empleado para su acceso a sus tareas
	 */
	public String getContrasena() {
		return contrasena;
	}
	
	/**
	 * Metodo para asignar la contraseña al Empleado
	 * @param contrasena EL parmetro contraseña permite asignar una clave al usuario del Empleado
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	

}
