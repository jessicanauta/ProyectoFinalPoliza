package ec.edu.ups.servicios;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.CuentaDeAhorro;

public class Respuesta {
	
	private int codigo;
	private String descripcion;
	private @JsonProperty("Cliente")Cliente cliente;   
	private @JsonProperty("Cuenta")CuentaDeAhorro cuentaDeAhorro; 
	private @JsonProperty("Creditos")List<CreditoRespuesta> listaCreditos;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public CuentaDeAhorro getCuentaDeAhorro() {
		return cuentaDeAhorro;
	}
	public void setCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro) {
		this.cuentaDeAhorro = cuentaDeAhorro;
	}
	public List<CreditoRespuesta> getListaCreditos() {
		return listaCreditos;
	}
	public void setListaCreditos(List<CreditoRespuesta> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}
	
	
	
	
}
