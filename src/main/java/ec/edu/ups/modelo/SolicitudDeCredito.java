package ec.edu.ups.modelo;

import java.io.File;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

//import org.primefaces.model.file.UploadedFile;

@Entity
public class SolicitudDeCredito {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoCredito; 
	@OneToOne
	@JoinColumn(name="cedula_cliente")
	private Cliente clienteCredito;  
	private double montoCredito; 
	private String mesesCredito;
	private String estadoCredito;   
	@Lob 
	@Column(length=16777216)
    private byte[] arCedula; 
	@Lob 
	@Column(length=16777216)
    private byte[] arPlanillaServicios;  
	private String historialCredito; 
	private String saldoCuenta;  
	private double tasaPago; 
	
	
	
	public int getCodigoCredito() {
		return codigoCredito;
	}
	public void setCodigoCredito(int codigoCredito) {
		this.codigoCredito = codigoCredito;
	}
	public Cliente getClienteCredito() {
		return clienteCredito;
	}
	public void setClienteCredito(Cliente clienteCredito) {
		this.clienteCredito = clienteCredito;
	}

	public double getMontoCredito() {
		return montoCredito;
	}
	public void setMontoCredito(double montoCredito) {
		this.montoCredito = montoCredito;
	} 
	
	public String getMesesCredito() {
		return mesesCredito;
	}
	public void setMesesCredito(String mesesCredito) {
		this.mesesCredito = mesesCredito;
	}
	
	public String getEstadoCredito() {
		return estadoCredito;
	}
	public void setEstadoCredito(String estadoCredito) {
		this.estadoCredito = estadoCredito;
	}
	public byte[] getArCedula() {
		return arCedula;
	}
	public void setArCedula(byte[] arCedula) {
		this.arCedula = arCedula;
	}
	public byte[] getArPlanillaServicios() {
		return arPlanillaServicios;
	}
	public void setArPlanillaServicios(byte[] arPlanillaServicios) {
		this.arPlanillaServicios = arPlanillaServicios;
	}
	
	public String getHistorialCredito() {
		return historialCredito;
	}
	public void setHistorialCredito(String historialCredito) {
		this.historialCredito = historialCredito;
	}
	
	public String getSaldoCuenta() {
		return saldoCuenta;
	}
	public void setSaldoCuenta(String saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}
	public double getTasaPago() {
		return tasaPago;
	}
	public void setTasaPago(double tasaPago) {
		this.tasaPago = tasaPago;
	}
	
}
