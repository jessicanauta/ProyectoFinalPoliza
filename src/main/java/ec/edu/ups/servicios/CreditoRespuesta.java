package ec.edu.ups.servicios;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ec.edu.ups.modelo.DetalleCredito;

public class CreditoRespuesta {
	private int codigoCre;
	private String estado;
	private double monto;
	private double interes;
	private Date fechaRegistro;
	private Date fechaVencimiento;
    private List<DetalleCredito> detalles;

	public int getCodigoCredito() {
		return codigoCre;
	}

	public void setCodigoCredito(int codigoCredito) {
		this.codigoCre = codigoCredito;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<DetalleCredito> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleCredito> detalles) {
		this.detalles = detalles;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
