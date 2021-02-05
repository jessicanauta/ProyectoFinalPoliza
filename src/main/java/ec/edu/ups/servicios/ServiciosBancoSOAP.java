package ec.edu.ups.servicios;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.edu.ups.negocio.GestionUsuarioLocal;


@WebService
public class ServiciosBancoSOAP {

	@Inject 
	private GestionUsuarioLocal gestionUsuario; 
	
	@WebMethod 
	public String realizarTransaccionBancaria(String cuenta, double monto, String tipo) { 
		return gestionUsuario.realizarTransaccion(cuenta, monto, tipo);
	} 
	
	@WebMethod 
	public Respuesta realizarTransferencia(String cedula, String cuentaDeAhorro, double monto) { 
		return gestionUsuario.realizarTransferencia(cedula, cuentaDeAhorro, monto);
	}		 

}
