package ec.edu.ups.negocio;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Credito;
import ec.edu.ups.modelo.CuentaDeAhorro;
import ec.edu.ups.modelo.DetalleCredito;
import ec.edu.ups.modelo.Empleado;
import ec.edu.ups.modelo.Poliza;
import ec.edu.ups.modelo.PolizaAdmin;
import ec.edu.ups.modelo.SesionCliente;
import ec.edu.ups.modelo.SolicitudDeCredito;
import ec.edu.ups.modelo.Transaccion;
import ec.edu.ups.modelo.TransfereciaLocal;
import ec.edu.ups.modelo.TransferenciaExterna;
import ec.edu.ups.servicios.Respuesta;
import ec.edu.ups.servicios.RespuestaTransferenciaExterna;

@Local
public interface GestionUsuarioLocal {
	
	public String generarNumeroDeCuenta();
	public String getUsuario(String cedula, String nombre, String apellido);
	public String getContraseña();
	public void enviarCorreo(String destinatario, String asunto, String cuerpo);
	public String fecha();
	public String obtenerFecha(Date fecha);
	public void guardarCliente(Cliente c);
	public Cliente buscarCliente(String cedulaCliente);
	public Cliente buscarClienteUsuarioContraseña(String usuario, String contraseña);
	public void eliminarCliente(String cedulaCliente);
	public void actualizarCliente(Cliente cliente);
	public List<Cliente> listaClientes();
	public void guardarCuentaDeAhorros(CuentaDeAhorro c);
	public CuentaDeAhorro buscarCuentaDeAhorro(String numeroCuentaDeAhorro);
	public CuentaDeAhorro buscarCuentaDeAhorroCliente(String cedulaCliente);
	public void eliminarCuentaDeAhorro(String numeroCuentaDeAhorro);
	public void actualizarCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro);
	public List<CuentaDeAhorro> listaCuentaDeAhorros();
	public void guardarSesion(SesionCliente sesionCliente);
	public SesionCliente buscarSesionCliente(int codigoSesionCliente);
	public List<SesionCliente> obtenerSesionesCliente(String cedulaCliente);
	public boolean validadorDeCedula(String cedula)throws Exception;
	public void guardarEmpleado(Empleado empleado)throws SQLException, Exception;
	public Empleado usuarioRegistrado(String cedula);
	public List<Empleado> listadoEmpleados();
	public Empleado usuario(String usuario,String contra)throws Exception ;
	public List<Transaccion> listadeTransacciones(String cedula);
	public void guardarTransaccion(Transaccion t)throws Exception;
	public List<Transaccion> obtenerTransaccionesFechaHora(String cedula, String fechaI, String fechaF);
	public String realizarTransaccion(String cuenta, double monto, String tipoTransaccion); 
	public Respuesta realizarTransferencia(String cedula, String cuentaDeAhorro, double monto); 
	public void guardarTransferenciaLocal(TransfereciaLocal transfereciaLocal); 
	public void guardarSolicitudCredito(SolicitudDeCredito solicitudDeCredito); 
	public void actualizarSolicitudCredito(SolicitudDeCredito solicitudDeCredito); 
	public List<SolicitudDeCredito> listadoSolicitudDeCreditos(); 
	public Respuesta obtenerClienteCuentaAhorro(String numeroCuenta); 
	public byte[] toByteArray(InputStream in) throws IOException;
	public void guardarCredito(Credito credito);
	public void actualizarCredito(Credito credito);
	public List<Credito> listarCreditos();
	public List<DetalleCredito> crearTablaAmortizacion(int cuota, double monto, double interes);
	public void aprobarCredito(Credito credito, Cliente cliente);
	//public String getDatos();
	public List<Credito> listarCreditosCedula(String cedula);
	public Credito verCredito(int codigo);
	public void actualizarDetalle(DetalleCredito credito);
	public void actualiza(Credito credito); 
	public boolean verificarSolicitudSolicitando(String cedulaCliente); 
	public List<Credito> creditosAprovados(String cedulaCliente);
	//public void registrarCuotaVencida() throws ParseException; 
	public Respuesta loginServicio(String username, String password); 
	public Respuesta cambioContraseña(String correo, String contraseñaActual, String nuevaContraseña);
	public void rechazarCredito(Cliente cliente, String razon); 
	public RespuestaTransferenciaExterna realizarTransferenciaExterna(TransferenciaExterna transferenciaExterna);
	
	public List<PolizaAdmin> listaAdministrador();
	public void crearPolizaParam(PolizaAdmin param);
	public void actualizarparam(PolizaAdmin param);
	public boolean registrarPoliza(Poliza poliza) throws Exception;
	public boolean actualizarPoliza(Poliza poliza) throws Exception;
	public Poliza buscarPoliza(int id) throws Exception;
	public boolean eliminarPoliza(int id) throws Exception;
	public double calcularInteres(double capital, double tasaInteres, int plazoPoliza);
	public double validar_piliz(int tiempo, double valor);
}
