package ec.edu.ups.servicios;

import java.util.Date;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.edu.ups.modelo.CuentaDeAhorro;
import ec.edu.ups.modelo.Transaccion;
import ec.edu.ups.negocio.GestionUsuarioLocal;


@WebService
public class ServicioSOAP {
	
	@Inject
	private GestionUsuarioLocal on;
	
	@WebMethod
	public String transaccionservicio(String cuenta,double monto, String tipo) {
		CuentaDeAhorro vn = on.buscarCuentaDeAhorro(cuenta);
		
		if (tipo.equalsIgnoreCase("retiro")) {
			double nm = vn.getSaldoCuentaDeAhorro() - monto;
			vn.setSaldoCuentaDeAhorro(nm);
			
			
			
		}else if (tipo.equalsIgnoreCase("deposito")) {
			double nm = vn.getSaldoCuentaDeAhorro() + monto;
			vn.setSaldoCuentaDeAhorro(nm);
			
			
		}
		on.actualizarCuentaDeAhorro(vn);
		Transaccion transaccion = new Transaccion();
		transaccion.setCliente(vn.getCliente());
		transaccion.setFecha(new Date());
		transaccion.setMonto(monto);
		transaccion.setTipo(tipo);
		
		return null;
		
		
	}
	
	
	
	

}
