package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DetalleCredito implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_desc")
	private int codigoDetalle;
	private double interes;
	private double monto;
	private double saldo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;
	private String estado;
	
	
	
	public int getCodigoDetalle() {
		return codigoDetalle;
	}
	public void setCodigoDetalle(int codigoDetalle) {
		this.codigoDetalle = codigoDetalle;
	}
	
	public double getInteres() {
		return interes;
	}
	public void setInteres(double interes) {
		this.interes = interes;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	

}
