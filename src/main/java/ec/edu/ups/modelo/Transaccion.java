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
 * Esta clase Representa una Entidad (Tabla) en la Base de Datos.
 * Es para el registro de las transacciones que puede realizar un Cliente
 */
@Entity
public class Transaccion implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoTransaccion;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private Double monto;
	private String tipo;
	private Double saldoCuenta;
	@OneToOne
	@JoinColumn(name = "cedula_cliente")
	private Cliente cliente;
	
	/**
	 * Constructor vacio de acuerdo a JPA
	 */
	public Transaccion() {
	}
	
	/**
	 *Metodo para obtener el codigo a la transaccion
	 *@return codigoTransaccion Me perrmite devolver el codigo de la Transaccion
	 *
	 */
	public int getCodigoTransaccion() {
		return codigoTransaccion;
	}
	
	/**
	 * Metodo para asignar el codigo a la transaccion
	 * @param codigoTransaccion El parametro codigoTransaccion asigna un codigo a la transaccion
	 */
	public void setCodigoTransaccion(int codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}
	
	/**
	 * Metodo que me permite obtener la fecha de la Transaccion
	 * @return fecha Me devuelvee la fecha en la que se realizo la transaccion
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Metodo para asignar la fecha
	 * @param fecha El parametro fecha me permite asignar la fecha en la que se realiza la transaccion
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Metodo para obtener el monto de la Transaccion
	 * @return monto Me devuelve el valor o monto por la cual se realizo la transaccion;
	 */
	public Double getMonto() {
		return monto;
	}
	
	/**
	 * Metodo para asignar el monto a la Transaccion
	 * @param monto El parametro monto me permite asignar el valor por el cual se realiza la transaccion
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	/**
	 * Metodo para obtener el cliente de la Transaccion
	 * @return cliente Me permite obtener la cedula del cliente que realizo la transaccion
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * Metodo para asignar el cliente a la Transaccion
	 * @param cliente El parametro cliente me permite asignar el cliente que realiza la transaccion
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * Metodo para obtener el tipo de Transaccion
	 * @return tipo Me permito obtener el tipo de transaccion que se realizo, Deposito o Retiro.
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Metodo para asignar el tipo de Transaccion
	 * @param tipo El parametro tipo me permite asignar el tipo de Transaccion que se reliza
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Double getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(Double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}
	
	
	
	
	
	

}
