package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/** 
 * Esta clase representa una entidad o tabla llamada SesionCliente de la base de datos y sus columnas
 *
 */
@Entity
public class SesionCliente implements Serializable{ 
	//Atributos de la clase
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int codigoSesion;  
	private String estado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSesion; 
	@OneToOne
	@JoinColumn(name="cedula_cliente")
	private Cliente cliente; 
	
	/** 
	 * Metodo que permite obtener el atributo codigoSesion 
	 * @return El atributo codigoSesion de esta clase
	 */
	public int getCodigoSesion() {
		return codigoSesion;
	} 
	
	/** 
	 * Metodo que permite asignarle un valor al atributo codigoSesion
	 * @param codigoSesion Variable que se asigna al atributo
	 */
	public void setCodigoSesion(int codigoSesion) {
		this.codigoSesion = codigoSesion;
	} 
	
	/** 
	 * Metodo que permite obtener el atributo estado
	 * @return El atributo estado de esta clase
	 */
	public String getEstado() {
		return estado;
	} 
	
	/** 
	 * Metodo que permite asignarle un valor al atributo estado
	 * @param estado Variable que se asigna al atributo
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}  
	
	/** 
	 * Metodo que permite obtener el atributo fechaSesion
	 * @return El atributo fechaSesion de esta clase
	 */
	public Date getFechaSesion() {
		return fechaSesion;
	} 
	
	/** 
	 * Metodo que permite asignarle un valor al atributo fechaSesion
	 * @param fechaSesion Variable que se asigna al atributo
	 */
	public void setFechaSesion(Date fechaSesion) {
		this.fechaSesion = fechaSesion;
	} 
	
	/** 
	 * Metodo que permite obtener el atributo cliente
	 * @return El atributo cliente de esta clase
	 */
	public Cliente getCliente() {
		return cliente;
	} 
	
	/** 
	 * Metodo que permite asignarle un valor al atributo cliente
	 * @param cliente Variable que se asigna al atributo
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	} 

	
	
	
}
