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
	private int timepoInicio;
	private int timepoFin;
	private Double interes;
	
	

	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}

	public int getTimepoInicio() {
		return timepoInicio;
	}




	public void setTimepoInicio(int timepoInicio) {
		this.timepoInicio = timepoInicio;
	}




	public int getTimepoFin() {
		return timepoFin;
	}




	public void setTimepoFin(int timepoFin) {
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
