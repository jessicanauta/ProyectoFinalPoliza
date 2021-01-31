package ec.edu.ups.modelo;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/** 
 * Esta clase representa una entidad o tabla llamada Cliente de la base de datos y sus columnas
 *
 */
@Entity
public class Cliente implements Serializable { 
	//Atributos de la entidad
	@Id 
	@Column(name="cedula_cliente")
	private String cedula; 
	private String nombre; 
	private String apellido; 
	private String direccion; 
	private String telefono1;
	private String correo;
	private String usuario; 
	private String clave;  
	
	/** 
	 * Constructor de la clase 
	 */
	public Cliente() {

	} 
	/** 
	 * Metodo que permite obtener el atributo cedula 
	 * @return El atributo cedula de esta clase
	 */
	public String getCedula() {
		return cedula;
	}  
	
	/** 
	 * Metodo que permite asignarle un valor al atributo cedula 
	 * @param cedula parametro para poder obtener 
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	} 
	
	/** 
	 *  Metodo que permite obtener el atributo nombre  
	 * @return El atributo nombre de esta clase
	 */
	public String getNombre() {
		return nombre;
	} 
	
	/** 
	 * Metodo que permite asignarle un valor al atributo nombre 
	 * @param cedula Variable que se asigna al atributo de la clase
	 */
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 
	
	/** 
	 * Metodo que permite obtener el atributo apellido 
	 * @return El atributo apellido de esta clase
	 */
	public String getApellido() {
		return apellido;
	} 
	
	/** 
	 * Metodo que me permite asignarle un valor al atributo apellido 
	 * @param apellido Variable que se asigna al atributo de la clase
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	} 
	
	/**  
	 * Metodo que permite obtener el atributo direccion 
	 * @return El atributo direccion de esta clase
	 */
	public String getDireccion() {
		return direccion;
	} 
	
	/** 
	 * Metodo que me permite asignarle un valor al atributo direccion 
	 * @param direccion Variable que se asigna al atributo de la clase
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	} 
	
	/** 
	 * Metodo que permite obtener el atributo telefono1 
	 * @return El atributo telefono1 de esta clase
	 */
	public String getTelefono1() {
		return telefono1;
	} 
	
	/** 
	 *  Metodo que me permite asignarle un valor al atributo telefono1 
	 * @param telefono1 Variable que se asigna al atributo de la clase
	 */
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	} 
	
 
	
	/** 
	 * Metodo que permite obtener el atributo correo 
	 * @return El atributo correo de esta clase
	 */
	public String getCorreo() {
		return correo;
	} 
	
	/** 
	 * Metodo que me permite asignarle un valor al atributo correo
	 * @param correo variable que se asigna al atributo de la clase
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	} 
	
	/** 
	 * Metodo que permite obtener el atributo correo 
	 * @return El atributo usuario de esta clase
	 */
	public String getUsuario() {
		return usuario;
	} 
	/** 
	 * Metodo que me permite asignarle un valor al atributo usuario 
	 * @param usuario Variable que se asigna al atributo de la clase
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	} 
	
	/** 
	 * Metodo que permite obtener el atributo clave
	 * @return El atributo clave de esta clase
	 */
	public String getClave() {
		return clave;
	} 
	
	/** 
	 * Metodo que me permite asignarle un valor al atributo clave 
	 * @param clave Variable que se asigna al atributo de la clase
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}	
	
}
