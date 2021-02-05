package ec.edu.ups.servicios;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.modelo.TransferenciaExterna;
import ec.edu.ups.negocio.GestionUsuarioLocal;


@Path("/banco")
public class ServiciosBancoREST {

	@Inject
	private GestionUsuarioLocal on;

	@GET 
	@Path("/obtenerCliente") 
	@Produces("application/json") 
	public Respuesta obtenerCliente(@QueryParam("numeroCuenta") String numeroCuenta) { 
		return on.obtenerClienteCuentaAhorro(numeroCuenta);
	}
	
	
	@POST
	@Path("/login")
	@Produces("application/json;charset=utf-8")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Respuesta login(@FormParam("username") String username, @FormParam("password") String password) {
		Respuesta respuesta = on.loginServicio(username, password);
		return respuesta;
	} 
	
	@POST
	@Path("/cambiocontraseña")
	@Produces("application/json;charset=utf-8")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Respuesta cambioContraseña(@FormParam("correo") String correo, @FormParam("contraAntigua") String contraAntigua, @FormParam("contraActual") String contraActual) {
		Respuesta respuesta = on.cambioContraseña(correo, contraAntigua, contraActual);
		return respuesta;
	} 
		
	@POST
	@Path("/transaccion")
	@Produces("application/json")
	@Consumes("application/json")
	public String realizarTransaccionBancaria(TransaccionRest transaccionRest) {
		return on.realizarTransaccion(transaccionRest.getCuenta(), transaccionRest.getMonto(),
				transaccionRest.getTipo());
	}

	@POST
	@Path("/transferencia")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta realizarTransferencia(TransferenciaRest transferenciaRest) {
		return on.realizarTransferencia(transferenciaRest.getCedula(), transferenciaRest.getCuentaDeAhorro(),
				transferenciaRest.getMonto());
	} 
	
	
	@POST 
	@Path("/transferenciaExterna") 
	@Produces("application/json") 
	@Consumes("application/json") 
	public RespuestaTransferenciaExterna realizarTransferenciaExterna(TransferenciaExterna transferenciaExterna) { 
		return on.realizarTransferenciaExterna(transferenciaExterna);
	} 
	

}
