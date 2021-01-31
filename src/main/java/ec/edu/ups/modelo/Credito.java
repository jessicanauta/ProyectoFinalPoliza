package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Credito implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_cab")
	private int codigoCre;
	
	private String estado;
	private double monto;
	private double interes;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaVencimiento;
	
	@OneToOne
	@JoinColumn(name="jefe_credito")
	private Empleado jefeC;
	
	@OneToOne
	@JoinColumn(name="codigo_Credito")
	private SolicitudDeCredito solicitud;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="codigo_cabezera")
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

	public SolicitudDeCredito getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudDeCredito solicitud) {
		this.solicitud = solicitud;
	}

	public Empleado getJefeC() {
		return jefeC;
	}

	public void setJefeC(Empleado jefeC) {
		this.jefeC = jefeC;
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
