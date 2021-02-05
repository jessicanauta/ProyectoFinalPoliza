package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Poliza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_Poliz")
	private int codigo;
	private double monto;
	private int plazo;
	private double interes;
	
//	private CuentaDeAhorro cuenta;

	public Poliza() {
		super();
		// TODO Auto-generated constructor stub
	}
		public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	
	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}
/*	public CuentaDeAhorro getCuenta() {
		return cuenta;
	}
	public void setCuenta(CuentaDeAhorro cuenta) {
		this.cuenta = cuenta;
	}
	

	*/
	
	
}
