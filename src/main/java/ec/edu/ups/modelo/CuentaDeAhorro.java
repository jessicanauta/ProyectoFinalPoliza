 package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
/** 
 * Esta clase representa una entidad o tabla llamada CuentaDeAhorro de la base de datos y sus columnas
 *
 */
@Entity
public class CuentaDeAhorro implements Serializable {
	//Atributos de la clase
	@Id  
	@Column(name="numero_cuenta")
	private String numeroCuentaDeAhorro;
	private Date fechaDeRegistro;  
	private Double saldoCuentaDeAhorro; 
	private String tipo;
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL })
	@JoinColumn(name="cedula_cliente")
	private Cliente cliente;
	
	/** 
	 * Constructor de la clase
	 */
	public CuentaDeAhorro() {
	
	} 
	
	/** 
	 * Metodo que permite obtener el atributo numeroCuentaDeAhorro 
	 * @return El atributo numeroCuentaDeAhorro de esta clase
	 */
	public String getNumeroCuentaDeAhorro() {
		return numeroCuentaDeAhorro;
	} 
	
	/** 
	 * Metodo que permite asignarle un valor al atributo numeroCuentaDeAhorro
	 * @param numeroCuentaDeAhorro Variable que se asigna al atributo
	 */
	public void setNumeroCuentaDeAhorro(String numeroCuentaDeAhorro) {
		this.numeroCuentaDeAhorro = numeroCuentaDeAhorro;
	} 
	
	/** 
	 * Metodo que permite obtener el atributo fechaDeRegistro
	 * @return El atributo fechaDeRegistro de esta clase
	 */
	public Date getFechaDeRegistro() {
		return fechaDeRegistro;
	} 
	
	/** 
	 * Metodo que permite asignarle un valor al atributo fechaRegistro
	 * @param fechaDeRegistro Variable que se asigna al atributo
	 */
	public void setFechaDeRegistro(Date fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	} 
	
	/** 
	 * Metodo que permite obtener el atributo saldoCuentaDeAhorro
	 * @return El atributo saldoCuentaDeAhorro de esta clase
	 */
	public Double getSaldoCuentaDeAhorro() {
		return saldoCuentaDeAhorro;
	} 
	
	/** 
	 * Metodo que permite asignarle un valor al atributo saldoCuentaDeAhorro
	 * @param saldoCuentaDeAhorro Variable que se asigna al atributo
	 */
	public void setSaldoCuentaDeAhorro(Double saldoCuentaDeAhorro) {
		this.saldoCuentaDeAhorro = saldoCuentaDeAhorro;
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
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

}
