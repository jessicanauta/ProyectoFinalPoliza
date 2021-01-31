package ec.edu.ups.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransferenciaExterna {
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_transferncia_externa")
	private int codigoTransferenciaExterna;   
	@Column(name="fecha_transaccion") 
	private Date fechaTransaccion;
	@Column(name="monto_transferencia")
	private double montoTransferencia;  
	@Column(name="nombre_Institucion")
	private String nombreInstitucionExterna; 
	@Column(name="cuenta_local")
	private String cuentaPersonaLocal;    
	@Column(name="cuenta_persona_externa") 
	private String cuentaPersonaExterna;
	@Column(name="nombre_persona")
	private String nombrePersonaExterna; 
	@Column(name="apellido_persona") 
	private String apellidoPersonaExterna;
	public int getCodigoTransferenciaExterna() {
		return codigoTransferenciaExterna;
	}
	public void setCodigoTransferenciaExterna(int codigoTransferenciaExterna) {
		this.codigoTransferenciaExterna = codigoTransferenciaExterna;
	}
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public double getMontoTransferencia() {
		return montoTransferencia;
	}
	public void setMontoTransferencia(double montoTransferenciaLocal) {
		this.montoTransferencia = montoTransferenciaLocal;
	}
	public String getNombreInstitucionExterna() {
		return nombreInstitucionExterna;
	}
	public void setNombreInstitucionExterna(String nombreInstitucionExterna) {
		this.nombreInstitucionExterna = nombreInstitucionExterna;
	}
	public String getCuentaPersonaLocal() {
		return cuentaPersonaLocal;
	}
	public void setCuentaPersonaLocal(String cuentaPersonaLocal) {
		this.cuentaPersonaLocal = cuentaPersonaLocal;
	}
	public String getNombrePersonaExterna() {
		return nombrePersonaExterna;
	}
	public void setNombrePersonaExterna(String nombrePersonaExterna) {
		this.nombrePersonaExterna = nombrePersonaExterna;
	}
	public String getApellidoPersonaExterna() {
		return apellidoPersonaExterna;
	}
	public void setApellidoPersonaExterna(String apellidoPersonaExterna) {
		this.apellidoPersonaExterna = apellidoPersonaExterna;
	}
	public String getCuentaPersonaExterna() {
		return cuentaPersonaExterna;
	}
	public void setCuentaPersonaExterna(String cuentaPersonaExterna) {
		this.cuentaPersonaExterna = cuentaPersonaExterna;
	} 
	
	
	
}
