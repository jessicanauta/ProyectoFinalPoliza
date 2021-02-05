package ec.edu.ups.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PolizaAdmin {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	private String timepoInicio;
	private String timepoFin;
	private Double interes;
	
	

	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getTimepoInicio() {
		return timepoInicio;
	}




	public void setTimepoInicio(String timepoInicio) {
		this.timepoInicio = timepoInicio;
	}




	public String getTimepoFin() {
		return timepoFin;
	}




	public void setTimepoFin(String timepoFin) {
		this.timepoFin = timepoFin;
	}




	public Double getInteres() {
		return interes;
	}




	public void setInteres(Double interes) {
		this.interes = interes;
	}




	@Override
	public String toString() {
		return "PolizaAdmin [timepoInicio=" + timepoInicio + ", timepoFin=" + timepoFin + ", interes=" + interes + "]";
	}
	
	
	
}
