package ec.edu.ups.negocio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
import javax.management.remote.NotificationResult;
import javax.persistence.NoResultException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import ec.edu.ups.controlador.ClienteDAO;
import ec.edu.ups.controlador.CuentaDeAhorroDAO;
import ec.edu.ups.controlador.TransaccionDAO;
import ec.edu.ups.controlador.TransferenciaLocalDAO;
import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.CuentaDeAhorro;
import ec.edu.ups.modelo.Transaccion;
import ec.edu.ups.modelo.TransfereciaLocal;



//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;



/**
 * Esta clase me permite hacer diferentes validaciones o metodos necesarios
 * antes de poder realizar las diferentes funciones basicas en la base de datos
 */
@Stateless
public class GestionUsuarios implements GestionUsuarioLocal {
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private CuentaDeAhorroDAO cuentaDeAhorroDAO;
//	@Inject
//	private SesionClienteDAO sesionClienteDAO;
	@Inject
	private TransaccionDAO transaccionDAO;
//	@Inject
//	private EmpleadoDAO empleadoDAO;
	@Inject
	private TransferenciaLocalDAO transferenciaLocalDAO;
//	@Inject
//	private SolicitudDeCreditoDAO solicitudDeCreditoDAO;
	@Inject
//	private CreditoDAO creditoDAO;
//	@Inject
//	private DetalleCreditoDAO detalleCreditoDAO; 
//	@Inject 
//	private TransferenciaExternaDAO transferenciaExternaDAO;

	/**
	 * Metodo que permite la validacion de una cedula correcta
	 * 
	 * @param ced Cedula que se valida
	 * @return Es correcta o no la cedula
	 */
	/*
	 * public boolean verificarCedula(String ced) { int longitud = 0; char digitoN;
	 * int ultimo = 0; int suma = 0; int digitoVerificador = 0; longitud =
	 * ced.length();
	 * 
	 * for (int i = 0; i < longitud - 1; i++) { digitoN = ced.charAt(i); int
	 * digitoAscii = (int) ced.charAt(i); int resultado = 0;
	 * 
	 * switch (digitoAscii) { case 48: digitoN = 0; break; case 49: digitoN = 1;
	 * break; case 50: digitoN = 2; break; case 51: digitoN = 3; break; case 52:
	 * digitoN = 4; break; case 53: digitoN = 5; break; case 54: digitoN = 6; break;
	 * case 55: digitoN = 7; break; case 56: digitoN = 8; break; case 57: digitoN =
	 * 9; break; } if (i % 2 == 0) { resultado = digitoN * 2; if (resultado >= 10) {
	 * resultado -= 9;
	 * 
	 * } } else { resultado = digitoN * 1; } suma += resultado; } digitoVerificador
	 * = (((suma / 10) + 1) * 10) - suma; if (digitoVerificador == 10) {
	 * digitoVerificador = 0; } ultimo = (int) ced.charAt(9);
	 * 
	 * switch (ultimo) { case 48: ultimo = 0; break; case 49: ultimo = 1; break;
	 * case 50: ultimo = 2; break; case 51: ultimo = 3; break; case 52: ultimo = 4;
	 * break; case 53: ultimo = 5; break; case 54: ultimo = 6; break; case 55:
	 * ultimo = 7; break; case 56: ultimo = 8; break; case 57: ultimo = 9; break; }
	 * if (ultimo == digitoVerificador) { return true;
	 * 
	 * } else { return false; } }
	 */

	/**
	 * Metodo que permite generar una numero de cuenta automatico
	 * 
	 * @return Numero de cuenta generado
	 */
	public String generarNumeroDeCuenta() {
		int numeroInicio = 4040;
		List<CuentaDeAhorro> listaCuentas = listaCuentaDeAhorros();
		int numero = listaCuentas.size() + 1;
		String resultado = String.format("%08d", numero);
		String resultadoFinal = String.valueOf(numeroInicio) + resultado;
		return resultadoFinal;
	}

	/**
	 * Metodo que permite generar un usuario aletorio
	 * 
	 * @param cedula   Cedula del usuario
	 * @param nombre   Nombre del usario
	 * @param apellido Apellido del usuario
	 * @return Usuario que se ha creado
	 */
	public String getUsuario(String cedula, String nombre, String apellido) {
		System.out.println(cedula);
		System.out.println(nombre);
		System.out.println(apellido);
		String ud = cedula.substring(cedula.length() - 1);
		String pln = nombre.substring(0, 1);
		int it = 0;
		for (int i = 0; i < apellido.length(); i++) {
			if (apellido.charAt(i) == 32) {
				it = i;
			}
		}
		String a = "";
		if (it == 0) {
			a = apellido.substring(0, apellido.length());
		} else {
			a = apellido.substring(0, it);
		}
		return pln.toLowerCase() + a.toLowerCase() + ud;
	}

	/**
	 * Metodo que permite la creacion de una contraseña aleatoria
	 * 
	 * @return Contraseña aleatoria
	 */
	public String getContraseña() {
		String simbolos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefjhijklmnopqrstuvwxyz";

		int tam = simbolos.length() - 1;
		System.out.println(tam);
		String clave = "";
		for (int i = 0; i < 10; i++) {
			int v = (int) Math.floor(Math.random() * tam + 1);
			clave += simbolos.charAt(v);
		}

		return clave;
	}

	/**
	 * Metodo que permite el envio de un correo
	 * 
	 * @param destinatario Destinario que se envia el correo
	 * @param asunto       Asunto del correo
	 * @param cuerpo       Cuerpo del correo
	 */
	public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
//		Properties propiedad = new Properties();
//		propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
//		propiedad.setProperty("mail.smtp.starttls.enable", "true");
//		propiedad.setProperty("mail.smtp.port", "587");
//
//		Session sesion = Session.getDefaultInstance(propiedad);
//		String correoEnvia = "jecbank@gmail.com";
//		String contrasena = "Cuenca123";
//
//		MimeMessage mail = new MimeMessage(sesion);
//		try {
//			mail.setFrom("JE BANCK<" + correoEnvia + ">");
//			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
//			mail.setSubject(asunto);
//			mail.setText(cuerpo);
//
//			Transport transportar = sesion.getTransport("smtp");
//			transportar.connect(correoEnvia, contrasena);
//			transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
//		} catch (AddressException ex) {
//			System.out.println(ex.getMessage());
//		} catch (MessagingException ex) {
//			System.out.println(ex.getMessage());
//		}
	}

	/**
	 * Metodo que permite cambiar el formato de la fecha
	 * 
	 * @return Fecha con nuevo formato
	 */
	public String fecha() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return hourdateFormat.format(date);
	}

	/**
	 * Metodo que permite cambiar el formato de la fecha
	 * 
	 * @param fecha Fecha que se cambiara el formato
	 * @return La fecha en un formato requerido de tipo texto.
	 */
	public String obtenerFecha(Date fecha) {
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return hourdateFormat.format(fecha);
	}

	/**
	 * Metodo que me permite guardar el cliente en la base de datos
	 * 
	 * @param c Cliente que se guarda en la base de datos
	 */
	public void guardarCliente(Cliente c) {
		clienteDAO.insert(c);

	}

	/**
	 * Metodo que permite la busqueda de un cliente
	 * 
	 * @param cedulaCliente Cedula del cliente que se busca
	 * @return Cliente obtenido de la busqueda
	 */
	public Cliente buscarCliente(String cedulaCliente) {
		Cliente cliente = clienteDAO.read(cedulaCliente);
		return cliente;
	}

	/**
	 * Metodo que permite la busqueda del cliente en base a su usuario y contraseña
	 * 
	 * @param usuario    Usuario del cliente
	 * @param contraseña Contraseña del cliente
	 * @return Cliente obtenido de la busqueda
	 */
	public Cliente buscarClienteUsuarioContraseña(String usuario, String contraseña) {
		try {
			return clienteDAO.obtenerClienteUsuarioContraseña(usuario, contraseña);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo que permite eliminar un cliente
	 * 
	 * @param cedulaCliente Cedula del cliente que se elimina
	 */
	public void eliminarCliente(String cedulaCliente) {
		clienteDAO.delete(cedulaCliente);
	}

	/**
	 * Metodo que permite actualizar un cliente
	 * 
	 * @param cliente Cliente que se actualiza
	 */
	public void actualizarCliente(Cliente cliente) {
		clienteDAO.update(cliente);
	}

	/**
	 * Metodo que permite listar los clientes
	 * 
	 * @return Lista de todos los clientes
	 */
	public List<Cliente> listaClientes() {
		List<Cliente> clientes = clienteDAO.getClientes();
		return clientes;
	}

	/**
	 * Metodo que permite guardar una cuenta de ahorro
	 * 
	 * @param c Cuenta de ahorro que se guarda
	 */
	public void guardarCuentaDeAhorros(CuentaDeAhorro c) {
		Cliente cliente = clienteDAO.read(c.getCliente().getCedula());
		if (cliente == null) {
			Cliente cli = c.getCliente();
			String usuario = getUsuario(cli.getCedula(), cli.getNombre(), cli.getApellido());
			String contraseña = getContraseña();
			cli.setUsuario(usuario);
			cli.setClave(contraseña);
			c.setCliente(cli);
			String destinatario = cli.getCorreo(); // A quien le quieres escribir.

			String asunto = "CREACION DE USUARIO";
			String cuerpo = "COOPJE                                               SISTEMA TRANSACCIONAL\n"
					+ "------------------------------------------------------------------------------\n"
					+ "              Estimado(a): " + cli.getNombre().toUpperCase() + " "
					+ cli.getApellido().toUpperCase() + "\n"
					+ "------------------------------------------------------------------------------\n"
					+ "COOPJE le informa que el usuario ha sido habilitado exitosamente.    \n"
					+ "                                                                              \n"
					+ "                       Su usuario es : " + usuario + "                          \n"
					+ "                   	Su clave de acceso es:   " + contraseña + "               \n"
					+ "                       Fecha: " + fecha() + "                                     \n"
					+ "                                                                              \n"
					+ "------------------------------------------------------------------------------\n";
			CompletableFuture.runAsync(() -> {
				try {
					enviarCorreo(destinatario, asunto, cuerpo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			cuentaDeAhorroDAO.insert(c);
		}

	}

	/**
	 * Metodo que permite buscar una cuenta de ahorros
	 * 
	 * @param numeroCuentaDeAhorro Numero de la cuenta de ahorros que se desea
	 *                             buscar
	 * @return Cuenta de ahorros que se obtiende de la busqueda
	 */
	public CuentaDeAhorro buscarCuentaDeAhorro(String numeroCuentaDeAhorro) {
		CuentaDeAhorro cuentaDeAhorro = cuentaDeAhorroDAO.read(numeroCuentaDeAhorro);
		return cuentaDeAhorro;
	}

	/**
	 * Metodo que me permite buscar una cuenta de ahorros
	 * 
	 * @param cedulaCliente Cedula del cliente de la cuenta de ahorros
	 * @return Cuenta de ahorro obtenida de la busqueda
	 */
	public CuentaDeAhorro buscarCuentaDeAhorroCliente(String cedulaCliente) {
		CuentaDeAhorro cuentaDeAhorro = cuentaDeAhorroDAO.getCuentaCedulaCliente(cedulaCliente);
		return cuentaDeAhorro;

	}

	/**
	 * Metodo que me permite eliminar una cuenta de ahorros
	 * 
	 * @param numeroCuentaDeAhorro Numero de la cuenta de ahorros que se desea
	 *                             eliminar
	 */
	public void eliminarCuentaDeAhorro(String numeroCuentaDeAhorro) {
		cuentaDeAhorroDAO.delete(numeroCuentaDeAhorro);
	}

	/**
	 * Metodo que permite actualizar una cuenta de ahorros
	 * 
	 * @param cuentaDeAhorro Cuenta de Ahorros que se desea actualizar
	 */
	public void actualizarCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro) {
		cuentaDeAhorroDAO.update(cuentaDeAhorro);
	}

	/**
	 * Metodo que me permite listar las cuentas de ahorros
	 * 
	 * @return Lista de todas las cuentas de ahorros
	 */
	public List<CuentaDeAhorro> listaCuentaDeAhorros() {
		List<CuentaDeAhorro> clientes = cuentaDeAhorroDAO.getCuentaDeAhorros();
		return clientes;
	}

	/**
	 * Metodo que permite guardar la sesion y enviar un correo al usuario que se le
	 * ha asignado esa sesion
	 * 
	 * @param sesionCliente Sesion que se guarda
	 */
//	public void guardarSesion(SesionCliente sesionCliente) {
//		Cliente cli = sesionCliente.getCliente();
//		String destinatario = cli.getCorreo();
//		if (sesionCliente.getEstado().equalsIgnoreCase("Incorrecto")) {
//			// A quien le quieres escribir.
//
//			String asunto = "INICIO DE SESION FALLIDA";
//			String cuerpo = "COOPEJE\n"
//					+ "------------------------------------------------------------------------------\n"
//					+ "              Estimado(a): " + cli.getNombre().toUpperCase() + " "
//					+ cli.getApellido().toUpperCase() + "\n"
//					+ "------------------------------------------------------------------------------\n"
//					+ "COOPJE le informa que el acceso a su cuenta ha sido fallida.    \n"
//					+ "                       Fecha: " + obtenerFecha(sesionCliente.getFechaSesion())
//					+ "                                     \n"
//					+ "                                                                              \n"
//					+ "------------------------------------------------------------------------------\n";
//			CompletableFuture.runAsync(() -> {
//				try {
//					enviarCorreo(destinatario, asunto, cuerpo);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			});
//
//		} else {
//			// A quien le quieres escribir.
//
//			String asunto = "INICIO DE SESION CORRECTA";
//			String cuerpo = "JAMVirtual SISTEMA TRANSACCIONAL\n"
//					+ "------------------------------------------------------------------------------\n"
//					+ "              Estimado(a): " + cli.getNombre().toUpperCase() + " "
//					+ cli.getApellido().toUpperCase() + "\n"
//					+ "------------------------------------------------------------------------------\n"
//					+ "COOPERATIVA JAM le informa que el acceso a su cuenta ha sido correcta.    \n"
//					+ "                       Fecha: " + obtenerFecha(sesionCliente.getFechaSesion())
//					+ "                                     \n"
//					+ "                                                                              \n"
//					+ "------------------------------------------------------------------------------\n";
//
//			CompletableFuture.runAsync(() -> {
//				try {
//					enviarCorreo(destinatario, asunto, cuerpo);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			});
//		}
//
//		sesionClienteDAO.insert(sesionCliente);
//
//	}

	/**
	 * Metodo que permite buscar una Sesion
	 * 
	 * @param codigoSesionCliente Codigo de la sesion que se desea buscar
	 * @return Sesion obtenida de la busqueda
	 */

//	public SesionCliente buscarSesionCliente(int codigoSesionCliente) {
//		return sesionClienteDAO.read(codigoSesionCliente);
//	}

	/**
	 * Metodo que permite obtener las sesiones de un cliente
	 * 
	 * @param cedulaCliente Cedula del cliente que tiene la sesion que se desea
	 *                      buscar
	 * @return Lista de sesiones de un cliente
	 */
//	public List<SesionCliente> obtenerSesionesCliente(String cedulaCliente) {
//		try {
//			return sesionClienteDAO.obtenerSesionCliente(cedulaCliente);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	/**
	 * Metodo para validacion
	 * 
	 * @param cedula El parmetro cedula sirve para la validacion de la una cedula
	 *               Ecuatoriana
	 * @return Si la cedula esta correcta o incorrecta en una variable booleana TRUE
	 *         o FALSE
	 * @throws Exception
	 */
	public boolean validadorDeCedula(String cedula) throws Exception {
		System.out.println(cedula + "    En Metodo ");
		boolean cedulaCorrecta = false;
		try {
			if (cedula.length() == 10) // ConstantesApp.LongitudCedula
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}
					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			cedulaCorrecta = false;
			throw new Exception("Error cedula");
		}
		if (!cedulaCorrecta) {
			return cedulaCorrecta;
			// throw new Exception("Cedula Incorrecta");

		}
		return cedulaCorrecta;
	}

	/**
	 * Metodo para guardar Empleado
	 * 
	 * @param empleado El parametro empleado me permite registrarlo en la Base de
	 *                 Datos un Empleado
	 * @throws SQLException Excepcion para un fallo de ingreso en la base de datos
	 * @throws Exception    Excepcion de registro en la base de datos
	 */
//	public void guardarEmpleado(Empleado empleado) throws SQLException, Exception {
//
//		if (!validadorDeCedula(empleado.getCedula())) {
//			throw new Exception("Cedula Incorrecta");
//		} else {
//
//			try {
//				empleadoDAO.insertarEmpleado(empleado);
//			} catch (Exception e) {
//				throw new Exception(e.toString());
//			}
//		}
//	}

	/**
	 * Metodo para obtener un Empleado
	 * 
	 * @param cedula El parametro cedula me permite obtener un Empleado que contenga
	 *               la cedual igual al parametro
	 * @return Un Empleado registrado en la Base de Datos
	 */
//	public Empleado usuarioRegistrado(String cedula) {
//		return empleadoDAO.obtenerEmpleado(cedula);
//	}

	/**
	 * Metodo para obtener una Lista de Empleados
	 * 
	 * @return La lita con todos los empleado registrados en la Institucion
	 */
//	public List<Empleado> listadoEmpleados() {
//		return empleadoDAO.obtener();
//	}

	/**
	 * Metodo para obtener un Empleado
	 * 
	 * @param usuario El parametro usuario me permite obtener un Empleado que
	 *                contenga el usuario pasado como parametro
	 * @param contra  El parametro contra permite obtener un Empleado que contenga
	 *                el usuario pasado como parametro
	 * @return Un Empleado con los usuario y contraseña de acuerdo a los parametros
	 * @throws Exception Excepcion cuando no se obtiene ningun usuario
	 */
//	public Empleado usuario(String usuario, String contra) throws Exception {
//		try {
//			Empleado em = empleadoDAO.obtenerUsuario(usuario, contra);
//			if (em != null) {
//				return em;
//			}
//		} catch (NoResultException e) {
//			throw new Exception("Credenciales Incorrectas");
//		}
//		return null;
//
//	}

	/**
	 * Metodo para obtener una Lista de Transacciones
	 * 
	 * @param cedula El parametro cedula me permite obtener la lista de
	 *               transacciones de acuedo al parametro
	 * @return Lista de Transacciones que realizo un Cliente de acuerdo al parametro
	 */
	public List<Transaccion> listadeTransacciones(String cedula) {
		try {
			return transaccionDAO.getListaTransacciones(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Metodo para guardad una Transaccion
	 * 
	 * @param t El parametro t me permite registrar una Transaccion de acuerdo al
	 *          parametro
	 * @throws Exception Excepcion para un fallo en el registro de la Transaccion
	 */
	public void guardarTransaccion(Transaccion t) throws Exception {

		try {
			transaccionDAO.insert(t);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	/**
	 * Metodo que permite buscar las transacciones de un usuario entre fechas
	 * 
	 * @param cedula Numero de cedula de la persona que busca
	 * @param fechaI La fecha de inicio desde donde se quieren ver las transacciones.
	 * @param fechaF La fecha de fin hasta donde se quieren ver las transacciones.
	 * @return Una lista de las transacciones/movimientos del usuario entre las fechas indicadas.
	 * @throws Exception Excepción por si el cliente no tiene transacciones.
	 */
	public List<Transaccion> obtenerTransaccionesFechaHora(String cedula, String fechaI, String fechaF) {
		String fechaInicio = fechaI + " 00:00:00.000000";
		String fechaFinal = fechaF + " 23:59:59.000000";
		try {
			return transaccionDAO.getListaTransaccionesFechas(cedula, fechaInicio, fechaFinal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo que permite realizar una transacción por parte del cajero
	 * 
	 * @param cuenta Numero de cuenta de la persona a la que se hace la transacción.
	 * @param monto El valor de transacción.
	 * @param tipoTransaccion El tipo de transacción que se realiza depósito o retiro;
	 * @return Un mensaje indicado si se completo correctamente el proceso o algo error que pueda ocurrir.
	 * @throws Exception Excepción por si sucede algún error.
	 */
	
	public String realizarTransaccion(String cuenta, double monto, String tipoTransaccion) {
		CuentaDeAhorro clp = cuentaDeAhorroDAO.read(cuenta);
		if (clp != null) {
			if (tipoTransaccion.equalsIgnoreCase("deposito")) {
				Double nvmonto = clp.getSaldoCuentaDeAhorro() + monto;
				clp.setSaldoCuentaDeAhorro(nvmonto);
				actualizarCuentaDeAhorro(clp);
				Transaccion t = new Transaccion();
				t.setCliente(clp.getCliente());
				t.setMonto(monto);
				t.setFecha(new Date());
				t.setTipo("deposito");
				t.setSaldoCuenta(nvmonto);
				try {
					// editable = false;
					guardarTransaccion(t);
					return "Hecho";
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
			} else if (tipoTransaccion.equalsIgnoreCase("retiro") && monto <= clp.getSaldoCuentaDeAhorro()) {
				Double nvmonto2 = clp.getSaldoCuentaDeAhorro() - monto;
				clp.setSaldoCuentaDeAhorro(nvmonto2);
				actualizarCuentaDeAhorro(clp);
				Transaccion t2 = new Transaccion();
				t2.setCliente(clp.getCliente());
				t2.setMonto(monto);
				t2.setFecha(new Date());
				t2.setTipo("retiro");
				t2.setSaldoCuenta(nvmonto2);
				try {
					guardarTransaccion(t2);
					return "Hecho";
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
			} else {
				return "Monto exedido";
			}
		} else {
			return "Cuenta Inexistente";
		}
		return "Fallido";
	}

	/**
	 * Metodo que permite realizar una transferencia
	 * 
	 * @param cedula Numero de cedula de la persona que hace la transferencia.
	 * @param cuentaAhorro2 El numero de cuenta de la persona a la que se hace la transferencia.
	 * @param monto El valor de la transferencia.
	 * @return Un clase Respuesta indicando los datos del desarrollo del proceso, con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */
//	public Respuesta realizarTransferencia(String cedula, String cuentaAhorro2, double monto) {
//		Respuesta respuesta = new Respuesta(); 
//		CuentaDeAhorro cuentaAhorro = cuentaDeAhorroDAO.getCuentaCedulaCliente(cedula);
//		CuentaDeAhorro cuentaAhorroTransferir = cuentaDeAhorroDAO.read(cuentaAhorro2);
//		try {
//			if (cuentaAhorro.getSaldoCuentaDeAhorro() >= monto) {
//				cuentaAhorro.setSaldoCuentaDeAhorro(cuentaAhorro.getSaldoCuentaDeAhorro() - monto);
//				actualizarCuentaDeAhorro(cuentaAhorro);
//				cuentaAhorroTransferir.setSaldoCuentaDeAhorro(cuentaAhorroTransferir.getSaldoCuentaDeAhorro() + monto);
//				actualizarCuentaDeAhorro(cuentaAhorroTransferir);
//				TransfereciaLocal transfereciaLocal = new TransfereciaLocal();
//				transfereciaLocal.setCliente(cuentaAhorro.getCliente());
//				transfereciaLocal.setCuentaDeAhorroDestino(cuentaAhorroTransferir);
//				transfereciaLocal.setMonto(monto);
//				guardarTransferenciaLocal(transfereciaLocal); 
//				respuesta.setCodigo(1); 
//				respuesta.setDescripcion("Transferencia Satisfactoria");
//			} else { 
//				respuesta.setCodigo(2); 
//				respuesta.setDescripcion("Monto Excedido");
//			}
//		} catch (Exception e) {
//			respuesta.setCodigo(3); 
//			respuesta.setDescripcion(e.getMessage());
//		} 
//		return respuesta;
//	}

	/**
	 * Método que permite guardar una transferencia local.
	 * 
	 * @param transfereciaLocal Una clase TransferenciaLocal para realizar el proceso de guardado.
	 */
	
	public void guardarTransferenciaLocal(TransfereciaLocal transfereciaLocal) {
		transferenciaLocalDAO.insert(transfereciaLocal);
	}

	/**
	 * Método que permite guardar una solicitud de credito conjuntamente con el procesado para determinar el tipo de cliente.
	 * 
	 * @param solicituDeCredito Una clase SolicitudDeCredito para realizar el proceso de guardado.
	 * @throws ForbiddenException Una excepción de tiempo de ejecución que indica que el servidor 
	 * 								ha prohibido el acceso a un recurso solicitado por un cliente.
	 * @throws InterruptedException Se lanza cuando un hilo está esperando, durmiendo u ocupado de otra manera, 
	 * 									y el hilo se interrumpe, ya sea antes o durante la actividad.
	 * @throws ExecutionException Se lanza una excepción al intentar recuperar el resultado de una 
	 * 								tarea que se canceló al lanzar una excepción
	 */
	
//	public void guardarSolicitudCredito(SolicitudDeCredito solicituDeCredito) {
//		solicituDeCredito.setHistorialCredito(historialCredito(solicituDeCredito));
//		solicituDeCredito.setSaldoCuenta(saldoCuenta(solicituDeCredito));
//		solicituDeCredito.setGaranteEstado(garanteCreditos(solicituDeCredito));
//		solicituDeCredito.setAñosCliente(obtenerEdad(solicituDeCredito.getClienteCredito().getFechaNacimiento()));
//		solicituDeCredito.setCantidadCreditos(numeroCreditos(solicituDeCredito));
//
//		String credito = "{\"credito\":\"" + solicituDeCredito.getClienteCredito().getCedula() + ";"
//				+ String.valueOf(solicituDeCredito.getMesesCredito()) + ";" + solicituDeCredito.getHistorialCredito()
//				+ ";" + obtenerCodigo(solicituDeCredito.getPropositoCredito()) + ";"
//				+ String.valueOf(solicituDeCredito.getMontoCredito()) + ";" + solicituDeCredito.getSaldoCuenta() + ";"
//				+ obtenerCodigo(solicituDeCredito.getTiempoEmpleo()) + ";"
//				+ String.valueOf(solicituDeCredito.getTasaPago()) + ";"
//				+ obtenerCodigo(solicituDeCredito.getEstadoCivilSexo()) + ";" + solicituDeCredito.getGaranteEstado()
//				+ ";" + String.valueOf(solicituDeCredito.getAvaluoDeVivienda()) + ";"
//				+ obtenerCodigo(solicituDeCredito.getActivo()) + ";"
//				+ String.valueOf(solicituDeCredito.getAñosCliente()) + ";"
//				+ obtenerCodigo(solicituDeCredito.getTipoVivienda()) + ";"
//				+ String.valueOf(solicituDeCredito.getCantidadCreditos()) + ";"
//				+ obtenerCodigo(solicituDeCredito.getTipoEmpleo()) + ";"
//				+ obtenerCodigo(solicituDeCredito.getTrabajadorExtranjero()) + ";0\"}";
//		enviarEntidad(credito);
//		try {
//			solicituDeCredito.setTipoCliente(
//					String.valueOf(obtenerTipoCliente(solicituDeCredito.getClienteCredito().getCedula())));
//		} catch (ForbiddenException | InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		solicitudDeCreditoDAO.insert(solicituDeCredito);
//	}

	/**
	 * Método que permite actualizar una solicitud de crédito.
	 * 
	 * @param solicitudDeCredito Una clase SolicitudDeCredito para realizar el proceso de actualización.
	 */
	
//	public void actualizarSolicitudCredito(SolicitudDeCredito solicitudDeCredito) {
//		solicitudDeCreditoDAO.update(solicitudDeCredito);
//	}

	/**
	 * Método que permite listar las solicitudes de crédito.
	 * 
	 * @return Un lista con clases SolicitudDeCredito con los datos de las solicitudes de credito;
	 */
	
//	public List<SolicitudDeCredito> listadoSolicitudDeCreditos() {
//		return solicitudDeCreditoDAO.getSolicitudDeCreditos();
//	}

	/**
	 * Metodo que permite obtener un cliente para el proceso de transacciones o transferencias.
	 * 
	 * @param numeroCuenta El numero de cuenta de la persona a la que se hace la transaccion o transferencia.
	 * @return Un clase Respuesta indicando los datos del desarrollo del proceso, con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */
//	public Respuesta obtenerClienteCuentaAhorro(String numeroCuenta) {
//		Respuesta respuesta = new Respuesta();
//		CuentaDeAhorro cuentaDeAhorro = cuentaDeAhorroDAO.read(numeroCuenta); 
//		try {
//			if(cuentaDeAhorro!=null) {
//				 respuesta.setCodigo(1); 
//				 respuesta.setDescripcion("Se ha obtenido la cuenta exitosamente"); 
//				 respuesta.setCuentaDeAhorro(cuentaDeAhorro);
//			}else{ 
//				respuesta.setCodigo(2); 
//				respuesta.setDescripcion("La Cuenta de Ahorro no existe");
//			}
//		} catch (Exception e) {
//			respuesta.setCodigo(3); 
//			respuesta.setDescripcion("Error "+e.getMessage());
//		}
//		return respuesta;
//	}

	/**
	 * Metodo que permite convertir una clase InputStream en un byte [] arreglo de bytes para su posterior guardado en la base de datos.
	 * 
	 * @param in Una clase InputStream que continue la información de un archivo que se selecciona en el proceso de la solicitud de credito.
	 * @return Un clase byte [] un arreglo de bytes del InputStream pasado como parametro.
	 * @throws IOException Excepción para el manejo de clases que tengan que ver con archivos.
	 */
	
//	public byte[] toByteArray(InputStream in) throws IOException {
//
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//
//		byte[] buffer = new byte[1024];
//		int len;
//
//		// read bytes from the input stream and store them in buffer
//		while ((len = in.read(buffer)) != -1) {
//			// write bytes from the buffer into output stream
//			os.write(buffer, 0, len);
//		}
//
//		return os.toByteArray();
//	}

	/**
	 * Método que permite guardar un crédito;
	 * 
	 * @param credito Una clase Credito para realizar el proceso de guardado.
	 */
	
//	public void guardarCredito(Credito credito) {
//		creditoDAO.insert(credito);
//	}

	/**
	 * Método que permite actualizar un crédito;
	 * 
	 * @param credito Una clase Credito para realizar el proceso de actualización.
	 */
	
//	public void actualizarCredito(Credito credito) {
//		creditoDAO.update(credito);
//	}

	/**
	 * Método que permite listar los créditos.
	 * 
	 * @return Una lista con clases Credito con los datos de los créditos.
	 */
	
//	public List<Credito> listarCreditos() {
//		List<Credito> cred = creditoDAO.getCreditos();
//		return cred;
//	}

	/**
	 * Metodo que permite crear la tabla de amortización de un crédito aprobado que se convertiran en los detalles de un crédito.
	 * 
	 * @param cuotas El numero de meses que el cliente indica cuando solicita un credito.
	 * @param monto El valor del credito indicado por el cliente en la solicitud.
	 * @param interes El valor calculado de los datos de la solicitud indicados por el cliente en base a sus ingresos y egresos.
	 * @return Una lista con clases DetalleCredito con los datos de la tabla de amortización.
	 */
	
//	public List<DetalleCredito> crearTablaAmortizacion(int cuotas, double monto, double interes) {
//		List<DetalleCredito> listaDet = new ArrayList<>();
//		Date fecha = new Date();
//		List<Date> fechas = new ArrayList<>();
//		double vcuota = monto / cuotas;
//		double icuota = monto * (interes / 100);
//		for (int i = 0; i < cuotas; i++) {
//			DetalleCredito detalle = new DetalleCredito();
//			detalle.setEstado("Pendiente");
//			Calendar calendar1 = Calendar.getInstance();
//			calendar1.setTime(fecha); // Configuramos la fecha que se recibe
//			calendar1.add(Calendar.MONTH, 1);
//			fecha = calendar1.getTime();// numero de horas a añadir, o restar en caso de horas<0
//			fechas.add(fecha);
//			monto -= vcuota;
//			detalle.setNumeroCuota(i + 1);
//			detalle.setFechaPago(fecha);
//			detalle.setInteres(valorDecimalCr(icuota));
//			detalle.setSaldo(valorDecimalCr(vcuota + icuota));
//			detalle.setMonto(valorDecimalCr(monto));
//			detalle.setCuota(valorDecimalCr(vcuota));
//			listaDet.add(detalle);
//		}
//		return listaDet;
//	}

	/**
	 * Metodo que permite cambiar el formato de la fecha
	 * 
	 * @param fecha Fecha que se cambiara el formato
	 * @return La fecha en un formato requerido de tipo texto.
	 */
	
//	public String obtenerFecha2(Date fecha) {
//		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		return hourdateFormat.format(fecha);
//	}

	/**
	 * Metodo que permite indicar los datos para enviar mediante el correo de la aprobación de crédito.
	 * 
	 * @param credito Una clase Credito con los datos del credito.
	 * @param cliente Una clase Cliente con los datos del cliente.
	 * @throws Exception Excepción por si sucede algún error en el proceso de envio.
	 */
//	public void aprobarCredito(Credito credito, Cliente cliente) {
//		String destinatario = cliente.getCorreo();
//		String asunto = "APROBACIÓN DE CREDITO";
//		String cuerpo = "COOPJE SISTEMA TRANSACCIONAL\n"
//				+ "------------------------------------------------------------------------------\n"
//				+ "              Estimado(a): " + cliente.getNombre().toUpperCase() + " "
//				+ cliente.getApellido().toUpperCase() + "\n"
//				+ "------------------------------------------------------------------------------\n"
//				+ "COOPJE le informa que su credito ha sido aprobado.                   \n"
//				+ "                                                                              \n"
//				+ "                         Fecha: " + obtenerFecha(credito.getFechaRegistro()) + "\n"
//				+ "                                                                              \n"
//				+ "La informacion de sus cuotas se encuentra en el archivo adjunto.              \n"
//				+ "------------------------------------------------------------------------------\n";
//
//		CompletableFuture.runAsync(() -> {
//			try {
//				enviarCorreo2(destinatario, asunto, cuerpo, credito);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
////			} 
//	}

	/**
	 * Método que permite enviar el correo electronico con los datos dados en el método aprobarCredito.
	 * 
	 * @param destinatario El correo electronico del cliente al que se envia el correo.
	 * @param asunto El asunto del correo electronico.
	 * @param cuerpo El cuerpo del correo electronico.
	 * @param credito Una clase Credito que se envia en un metodo para generar la tabla 
	 * 					de amortización en un documento pdf y guardar en un archivo.
	 * @throws AddressException La excepción que se lanza cuando se encuentra una dirección con formato incorrecto.
	 * @throws IOException Señala que se ha producido una excepción de E / S de algún tipo. Esta clase es la clase 
							general de excepciones producidas por operaciones de E / S fallidas o interrumpidas.
	 * @throws MessagingException La clase base para todas las excepciones lanzadas por las clases de mensajería.
	 */
//	public void enviarCorreo2(String destinatario, String asunto, String cuerpo, Credito credito) {
//		Properties propiedad = new Properties();
//		propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
//		propiedad.setProperty("mail.smtp.starttls.enable", "true");
//		propiedad.setProperty("mail.smtp.port", "587");
//
//		Session sesion = Session.getDefaultInstance(propiedad);
//		String correoEnvia = "cooperativajam@gmail.com";
//		String contrasena = "ZJRIcfjy1719";
//
//		MimeMessage mail = new MimeMessage(sesion);
//		Multipart multipart = new MimeMultipart();
//
//		MimeBodyPart attachmentPart = new MimeBodyPart();
//
//		MimeBodyPart textPart = new MimeBodyPart();
//
//		try {
//			mail.setFrom("COOPJE <" + correoEnvia + ">");
//			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
//			mail.setSubject(asunto);
//			File f = generarTablaAmor(credito);
//			attachmentPart.attachFile(f);
//			textPart.setText(cuerpo);
//			multipart.addBodyPart(attachmentPart);
//			multipart.addBodyPart(textPart);
//			mail.setContent(multipart);
//
//			Transport transportar = sesion.getTransport("smtp");
//			transportar.connect(correoEnvia, contrasena);
//			transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
//		} catch (AddressException | IOException ex) {
//			System.out.println(ex.getMessage());
//		} catch (MessagingException ex) {
//			System.out.println(ex.getMessage());
//		}
//	}

	/**
	 * Metodo que permite generar la tabla de amortizacion en un documento pdf en base a los datos del crédito.
	 * 
	 * @param credito Una clase Credito con los datos del credito.
	 * @return Un archivo pdf con los datos de la tabla de amortización.
	 */
//	public File generarTablaAmor(Credito credito) {
//		try {
//			Cliente cliente = credito.getSolicitud().getClienteCredito();
//			double monto = credito.getMonto();
//			double interes = credito.getInteres();
//			int meses = Integer.parseInt(credito.getSolicitud().getMesesCredito());
//			Document document = new Document();
//
//			File file = File.createTempFile("TablaAmortizacion", ".pdf");
//			FileOutputStream fos = new FileOutputStream(file);
//			PdfWriter.getInstance(document, fos);
//			document.open();
//			Paragraph par = new Paragraph();
//			par.add(new Phrase("COOP JAM"));
//			par.setAlignment(Element.ALIGN_CENTER);
//			document.add(par);
//			document.add(Chunk.NEWLINE);
//			Paragraph par1 = new Paragraph();
//			par1.add(new Phrase("TABLA DE AMORTIZACIÓN"));
//			par1.setAlignment(Element.ALIGN_CENTER);
//			document.add(par1);
//			document.add(Chunk.NEWLINE);
//			Paragraph par2 = new Paragraph();
//			par2.add(new Phrase("               Detalles de Crédito"));
//			par2.add(Chunk.NEWLINE);
//			par2.add(new Phrase("               Cliente: " + cliente.getNombre()+" "+cliente.getApellido()));
//			par2.add(Chunk.NEWLINE);
//			par2.add(new Phrase("               Fecha Registro: " + obtenerFecha2(credito.getFechaRegistro())));
//			par2.add(Chunk.NEWLINE);
//			par2.add(new Phrase("               Fecha Vencimiento: " + obtenerFecha2(credito.getFechaVencimiento())));
//			par2.add(Chunk.NEWLINE);
//			par2.add(new Phrase("               Monto: " + monto));
//			par2.add(Chunk.NEWLINE);
//			par2.add(new Phrase("               Interes: " + interes + "%"));
//			par2.add(Chunk.NEWLINE);
//			par2.add(new Phrase("               Plazo: " + meses + " meses"));
//			document.add(par2);
//			document.add(Chunk.NEWLINE);
//
//			PdfPTable table = new PdfPTable(6);
//			PdfPCell celdaInicial = new PdfPCell(new Paragraph("Detalles de las Cuotas"));
//			celdaInicial.setColspan(6);
//			celdaInicial.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(celdaInicial);
//			PdfPCell ct1 = new PdfPCell(new Phrase("#Cuota"));
//			ct1.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(ct1);
//			PdfPCell ct2 = new PdfPCell(new Phrase("Fecha"));
//			ct2.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(ct2);
//			PdfPCell ct3 = new PdfPCell(new Phrase("Cuota"));
//			ct3.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(ct3);
//			PdfPCell ct4 = new PdfPCell(new Phrase("Capital"));
//			ct4.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(ct4);
//			PdfPCell ct5 = new PdfPCell(new Phrase("Interes"));
//			ct5.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(ct5);
//			PdfPCell ct6 = new PdfPCell(new Phrase("Saldo"));
//			ct6.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(ct6);
//
//			for (DetalleCredito dcre : credito.getDetalles()) {
//				PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(dcre.getNumeroCuota())));
//				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//				table.addCell(cell1);
//				PdfPCell cell2 = new PdfPCell(new Phrase(obtenerFecha2(dcre.getFechaPago())));
//				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
//				table.addCell(cell2);
//				PdfPCell cell3 = new PdfPCell(new Phrase(String.valueOf(valorDecimalCr(dcre.getSaldo()))));
//				cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				table.addCell(cell3);
//				PdfPCell cell4 = new PdfPCell(new Phrase(String.valueOf(valorDecimalCr(dcre.getCuota()))));
//				cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				table.addCell(cell4);
//				PdfPCell cell5 = new PdfPCell(new Phrase(String.valueOf(valorDecimalCr(dcre.getInteres()))));
//				cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				table.addCell(cell5);
//				PdfPCell cell6 = new PdfPCell(new Phrase(String.valueOf(valorDecimalCr(dcre.getMonto()))));
//				cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				table.addCell(cell6);
//			}
//			document.add(table);
//
//			document.close();
//			return file;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	/**
	 * Metodo que permite indicar los datos para enviar mediante el correo el rechazo de la solicitud de crédito.
	 * 
	 * @param cliente Una clase Cliente con los datos del cliente.
	 * @param razon La descripción del rechazo de la solictud de credito.
	 * @throws Exception Excepción por si sucede algún error en el proceso de envio.
	 */
	public void rechazarCredito(Cliente cliente, String razon) {
		String destinatario = cliente.getCorreo();
		String asunto = "RECHAZO DE CREDITO";
		String cuerpo = "COOPJE\n"
				+ "------------------------------------------------------------------------------\n"
				+ "              Estimado(a): " + cliente.getNombre().toUpperCase() + " "
				+ cliente.getApellido().toUpperCase() + "\n"
				+ "------------------------------------------------------------------------------\n"
				+ "COOPJE le informa que su credito no ha sido aprobado.                \n"
				+ "Los detalles del rechazo se muestran a continuación.                          \n"
				+ "                                   DETALLES                                   \n" + razon
				+ "						             \n"
				+ "                                                                              \n"
				+ "                                                                              \n"
				+ "------------------------------------------------------------------------------\n";
		CompletableFuture.runAsync(() -> {
			try {
				enviarCorreo(destinatario, asunto, cuerpo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

//			} 
	}

	/**
	 * Metodo que permite indicar los datos para enviar mediante el correo el mensaje de cambio de contraseña.
	 * 
	 * @param cliente Una clase Cliente con los datos del cliente.
	 * @throws Exception Excepción por si sucede algún error en el proceso de envio.
	 */
	public void cambioContrasena(Cliente cliente) {
		String destinatario = cliente.getCorreo();
		String asunto = "CAMBIO DE CONTRASEÑA";
		String cuerpo = "COOPJE                                               SISTEMA TRANSACCIONAL\n"
				+ "------------------------------------------------------------------------------\n"
				+ "              Estimado(a): " + cliente.getNombre().toUpperCase() + "          "
				+ cliente.getApellido().toUpperCase() + "\n"
				+ "------------------------------------------------------------------------------\n"
				+ "COOPJE le informa que su contraseña ha sido cambiada exitosamente.   \n"
				+ "                                                                              \n"
				+ "                   Su nueva contraseña es:   " + cliente.getClave() + "       \n"
				+ "                       Fecha: " + fecha() + "                                 \n"
				+ "                                                                              \n"
				+ "------------------------------------------------------------------------------\n";
		CompletableFuture.runAsync(() -> {
			try {
				enviarCorreo(destinatario, asunto, cuerpo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

//			} 
	}

	/**
	 * Método que permite calcular el historial de credito del cliente.
	 * 
	 * @param solicitudCredito Una clase SolicitudDeCredito con los datos de la solicitud de credito.
	 * @return Un mensaje indicado el valor del historial de crédito.
	 */
//	public String historialCredito(SolicitudDeCredito solicitudCredito) {
//		List<Credito> lstCreditos = creditoDAO.getCreditos();
//		List<Credito> lstAprobados = new ArrayList<Credito>();
//		boolean confirmar = false;
//		boolean confirmar2 = false;
//
//		if (lstCreditos.size() == 0) {
//			confirmar = true;
//			return "A30";
//		} else {
//			for (Credito credito : lstCreditos) {
//				if (credito.getSolicitud().getClienteCredito().getCedula()
//						.equals(solicitudCredito.getClienteCredito().getCedula())) {
//					lstAprobados.add(credito);
//				}
//			}
//		}
//
//		for (Credito crd : lstAprobados) {
//			if (crd.getEstado().equalsIgnoreCase("Pagado")) {
//				for (DetalleCredito detalleCredito : crd.getDetalles()) {
//					if (detalleCredito.getEstado().equalsIgnoreCase("Retraso")) {
//						confirmar = true;
//						return "A33";
//					}
//				}
//			}
//		}
//
//		if (!confirmar) {
//			return "A31";
//		}
//
//		for (Credito crd : lstAprobados) {
//			if (crd.getEstado().equalsIgnoreCase("Pagado")) {
//				for (DetalleCredito detalleCredito : crd.getDetalles()) {
//					if (detalleCredito.getEstado().equalsIgnoreCase("Pagado")) {
//						confirmar2 = true;
//					}
//				}
//			}
//		}
//
//		if (confirmar2) {
//			return "A32";
//		}
//		return null;
//	}

	/**
	 * Método que permite calcular la edad del cliente.
	 * 
	 * @param fechaNacimiento La fecha de nacimiento del cliente.
	 * @return La edad del cliente en base a su fecha de nacimiento.
	 */
//	public int obtenerEdad(Date fechaNacimiento) {
//		Calendar a = Calendar.getInstance();
//		Calendar b = Calendar.getInstance();
//		a.setTime(fechaNacimiento);
//		b.setTime(new Date());
//		int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
//		if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
//				|| (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
//			diff--;
//		}
//		return diff;
//	}

	/**
	 * Método que permite determinar el rango de saldo de cuenta del cliente.
	 * 
	 * @param solicitudDeCredito Una clase SolicitudDeCredito con los datos de la solicitud de credito.
	 * @return Un mensaje indicado el valor del saldo de crédito en rangos.
	 */
//	public String saldoCuenta(SolicitudDeCredito solicitudDeCredito) {
//		CuentaDeAhorro cuentaDeAhorro = cuentaDeAhorroDAO
//				.getCuentaCedulaCliente(solicitudDeCredito.getClienteCredito().getCedula());
//		if (cuentaDeAhorro != null) {
//			double saldo = cuentaDeAhorro.getSaldoCuentaDeAhorro();
//			if (saldo < 500) {
//				return "A61";
//			} else if (saldo >= 500 && saldo < 1000) {
//				return "A62";
//			} else if (saldo >= 1000 && saldo < 1500) {
//				return "A63";
//			} else if (saldo >= 1500) {
//				return "A64";
//			}
//		}
//		return "A65";
//	}

	/**
	 * Método que permite determinar el estado del garante en la solicitud.
	 * 
	 * @param solicitudDeCredito Una clase SolicitudDeCredito con los datos de la solicitud de credito.
	 * @return Un mensaje indicado el valor del garante de crédito.
	 */
//	public String garanteCreditos(SolicitudDeCredito solicitudDeCredito) {
//		List<SolicitudDeCredito> lstSolicitudes = solicitudDeCreditoDAO.getSolicitudDeCreditos();
//		boolean confirmar = false;
//		for (SolicitudDeCredito solCredito : lstSolicitudes) {
//			if (solCredito.getGaranteCredito().getCedula()
//					.equalsIgnoreCase(solicitudDeCredito.getGaranteCredito().getCedula())
//					&& solCredito.getEstadoCredito().equalsIgnoreCase("Solicitando")) {
//				confirmar = true;
//				return "A102";
//			} else if (solCredito.getGaranteCredito().getCedula()
//					.equalsIgnoreCase(solicitudDeCredito.getGaranteCredito().getCedula())
//					&& solCredito.getEstadoCredito().equalsIgnoreCase("Aprobado")) {
//				confirmar = true;
//				return "A103";
//			}
//		}
//
//		if (!confirmar) {
//			return "A101";
//		}
//		return null;
//	}

	/**
	 * Método que permite determinar el numero de creditos aprobados del cliente que solicta el credito.
	 * 
	 * @param solicitudDeCredito Una clase SolicitudDeCredito con los datos de la solicitud de credito.
	 * @return Un mensaje indicando el numero de crédito del cliente.
	 */
//	public int numeroCreditos(SolicitudDeCredito solicitudDeCredito) {
//		List<Credito> lstCreditos = creditoDAO.getCreditos();
//		int contador = 0;
//		for (Credito credito : lstCreditos) {
//			if (credito.getSolicitud().getClienteCredito().getCedula()
//					.equalsIgnoreCase(solicitudDeCredito.getClienteCredito().getCedula())) {
//				contador++;
//			}
//		}
//		return contador;
//	}

	/**
	 * Método que permite codificar en codigo especificos los datos de la solicitud de credito.
	 * 
	 * @param palabra El texto que se encuentar en la interfaz de solicitud de credito que se validara.
	 * @return El texto del parametro codificado.
	 */
	
//	public String obtenerCodigo(String palabra) {
//		switch (palabra) {
//		case "inmuebles":
//			return "A40";
//		case "automovil":
//			return "A41";
//		case "muebles / equipamiento":
//			return "A42";
//		case "tecnología":
//			return "A43";
//		case "electrodomesticos":
//			return "A44";
//		case "reparaciones":
//			return "A45";
//		case "educacion":
//			return "A46";
//		case "vacaciones":
//			return "A47";
//		case "capacitacion":
//			return "A48";
//		case "negocios":
//			return "A49";
//		case "otros":
//			return "A410";
//		case "desempleado":
//			return "A71";
//		case "menos de 1 año":
//			return "A72";
//		case "entre 1 y 4 años":
//			return "A73";
//		case "entre 4 y 7 años":
//			return "A74";
//		case "mas de  7 años":
//			return "A75";
//		case "masculino: divorciado/separado":
//			return "A91";
//		case "femenino: dirvorciada/separada/casada":
//			return "A92";
//		case "masculino: soltero":
//			return "A93";
//		case "masculino: casado/viudo":
//			return "A94";
//		case " femenino: soltera":
//			return "A95";
//		case "Bienes inmuebles":
//			return "A121";
//		case "Seguro de vida y plan de construcción":
//			return "A122";
//		case "automovil u otro":
//			return "A123";
//		case "desconocido / sin propiedad":
//			return "A124";
//		case "gratis":
//			return "A151";
//		case "alquiler":
//			return "A152";
//		case "propio":
//			return "A153";
//		case "sin empleo":
//			return "A171";
//		case "jubilado":
//			return "A172";
//		case "empleado":
//			return "A173";
//		case "autonomo":
//			return "A174";
//		case "si":
//			return "A201";
//		case "no":
//			return "A202";
//		default:
//			break;
//		}
//		return null;
//
//	}

	/**
	 * Método que permite calcular el tipo de cliente de una solicitud de credito.
	 * 
	 * @param tr El número de cedula de la persona a obtener el tipo de cliente.
	 * @throws ForbiddenException Una excepción de tiempo de ejecución que indica que el servidor 
	 * 								ha prohibido el acceso a un recurso solicitado por un cliente.
	 * @throws InterruptedException Se lanza cuando un hilo está esperando, durmiendo u ocupado de otra manera, 
	 * 									y el hilo se interrumpe, ya sea antes o durante la actividad.
	 * @throws ExecutionException Se lanza una excepción al intentar recuperar el resultado de una 
	 * 								tarea que se canceló al lanzar una excepción
	 * @return El tipo de cliente para el crédito.
	 */
	
//	public int obtenerTipoCliente(String tr) throws ForbiddenException, InterruptedException, ExecutionException {
//
//		Form form = new Form();
//		form.param("Dni", tr);
//		Client client = ClientBuilder.newClient();
//		WebTarget target = client.target("http://35.238.98.31:8000/apiAnalisis/predecir/");
//		Future<String> response = target.request(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.TEXT_PLAIN)
//				.buildPost(Entity.form(form)).submit(String.class);
//		// client.close();
//		return Integer.parseInt(response.get());
//	}

	/**
	 * Método que permite enviar los datos de la solicitud de credito ene l archivo en servidor Django para el procesado del tipo de cliente.
	 * 
	 * @param credito Los datos de la solicitud de credito.
	 * @return Un mensaje indicando el resultado del proceso de guardado.
	 */
//	public String enviarEntidad(String credito) {
//		Client client = ClientBuilder.newClient();
//		WebTarget target = client.target("http://35.238.98.31:8000/apiAnalisis/enviarSolicitud/");
//		String res = target.request().post(Entity.json(credito), String.class);
//		client.close();
//		return res;
//	}

	/**
	 * Método que permite obtener los datos de los tipos de cliente para hacer una gráfica, del servicio web de Django.
	 * 
	 * @return Un mensaje indicando los resultados separados por ";" para su posterior gráfica.
	 */
//	public String getDatos() {
//		Client client = ClientBuilder.newClient();
//		WebTarget target = client.target("http://35.238.98.31:8000/apiAnalisis/verDiagrama/");
//		String res = target.request().get(String.class);
//		client.close();
//		return res;
//	}
	
	/**
	 * Método que permite obtener los creditos par un cliente específico en base a su número de cédula.
	 * 
	 * @param cedula El numero de cédula del cliente.
	 * @return Una lista con clases Credito con los datos de los créditos del cliente en cuestión.
	 */
	
//	public List<Credito> listarCreditosCedula(String cedula) {
//		List<Credito> cred = creditoDAO.getCreditos();
//		List<Credito> credLista = new ArrayList<Credito>();
//		for (Credito credito : cred) {
//			if (credito.getSolicitud().getClienteCredito().getCedula().equals(cedula)) {
//				credLista.add(credito);
//			}
//		}
//
//		return credLista;
//	}

	/**
	 * Método que permite obtener los datos de un crédito en especifico para ver sus detalles de cuotas.
	 * 
	 * @param codigo El codigo del credito en cuestión.
	 * @return Una clase Credito con los datos del credito del cliente.
	 */
	
//	public Credito verCredito(int codigo) {
//		Credito cred = creditoDAO.read(codigo);
//		return cred;
//	}

	/**
	 * Método que permite actualizar un detalle de un credito
	 * 
	 * @param credito Una clase DetalleCredito que tare los nuevos valores del detalle para actualizar.
	 */
//	public void actualizarDetalle(DetalleCredito credito) {
//		detalleCreditoDAO.update(credito);
//	}

	/**
	 * Método que permite cambiar el formato de los nuemros que se generen.
	 * 
	 * @param valor El valor del double para transformar.
	 */
//	public double valorDecimalCr(double valor) {
//		String num = String.format(Locale.ROOT, "%.2f", valor);
//		return Double.parseDouble(num);
//	}

	/**
	 * Método que permite actualizar un credito.
	 * 
	 * @param credito Una clase Credito con los nuevos datos para actualizar.
	 */
//	public void actualiza(Credito credito) {
//		creditoDAO.update(credito);
//
//	}

	/**
	 * Método que permite verificar una solicitud ce credito en base a la cedula del cliente.
	 * 
	 * @param cedulaCliente El numero de cédula del cliente.
	 * @return Un valor booleano que indica el estado de una solicitud.
	 */
//	public boolean verificarSolicitudSolicitando(String cedulaCliente) {
//		List<SolicitudDeCredito> solicitudes = solicitudDeCreditoDAO.getSolicitudDeCreditos();
//		for (SolicitudDeCredito solicitudDeCredito : solicitudes) {
//			if (solicitudDeCredito.getEstadoCredito().equalsIgnoreCase("Solicitando")
//					&& solicitudDeCredito.getClienteCredito().getCedula().equalsIgnoreCase(cedulaCliente)) {
//				return false;
//			}
//		}
//		return true;
//	}

	/**
	 * Método que permite obtener los creditos aprovados para un cliente específico en base a su número de cédula.
	 * 
	 * @param cedula El numero de cédula del cliente.
	 * @return Una lista con clases Credito con los datos de los créditos aprobados del cliente en cuestión.
	 */
	
//	public List<Credito> creditosAprovados(String cedulaCliente) {
//		List<Credito> listaCreditos = creditoDAO.getCreditos();
//		List<Credito> listCreditoTotales = new ArrayList<Credito>();
//		for (Credito credito : listaCreditos) {
//			if (credito.getSolicitud().getClienteCredito().getCedula().equalsIgnoreCase(cedulaCliente)) {
//				listCreditoTotales.add(credito);
//			}
//		}
//		return listCreditoTotales;
//	}

	/**
	 * Método que permite actualizar las cuotas vencidas en base a las fechas de las cuotas de los clientes.
	 * 
	 * @throws ParseException Una excepción que señala que se ha alcanzado un error inesperadamente durante el análisis.
	 */
//	public void registrarCuotaVencida() throws ParseException {
//		List<Credito> listacreditos = creditoDAO.getCreditos();
//
//		Date date = new Date();
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String fecha = dateFormat.format(date);
//		Date fechActual = dateFormat.parse(fecha);
//
//		List<Credito> lisComprabar = new ArrayList<Credito>();
//		for (Credito credito : listacreditos) {
//			if (credito.getEstado().equals("Pendiente")) {
//				lisComprabar.add(credito);
//			}
//		}
//
//		for (Credito cred : lisComprabar) {
//
//			for (DetalleCredito detale : cred.getDetalles()) {
//
//				Date fechaDetalle = detale.getFechaPago();
//				String fechat = dateFormat.format(fechaDetalle);
//				Date fechDet = dateFormat.parse(fechat);
//
//				if (fechDet.equals(fechActual)) {
//
//					CuentaDeAhorro cuenta = cuentaDeAhorroDAO
//							.getCuentaCedulaCliente(cred.getSolicitud().getClienteCredito().getCedula());
//					double saldo = cuenta.getSaldoCuentaDeAhorro();
//
//					if (detale.getEstado().equals("Pendiente")) {
//						if (saldo >= detale.getSaldo()) {
//
//							Double sss = cuenta.getSaldoCuentaDeAhorro() - detale.getSaldo();
//							detale.setEstado("Pagado");
//							detale.setSaldo(0.0);
//							cuenta.setSaldoCuentaDeAhorro(sss);
//
//							detalleCreditoDAO.update(detale);
//							cuentaDeAhorroDAO.update(cuenta);
//
//						} else if (saldo == 0.0) {
//
//							detale.setEstado("Vencido");
//							detalleCreditoDAO.update(detale);
//
//						} else if (saldo > 0 && saldo < detale.getSaldo()) {
//
//							double valorP = detale.getSaldo() - cuenta.getSaldoCuentaDeAhorro();
//							detale.setSaldo(valorP);
//							cuenta.setSaldoCuentaDeAhorro(0.0);
//							detale.setEstado("Vencido");
//
//							detalleCreditoDAO.update(detale);
//							cuentaDeAhorroDAO.update(cuenta);
//
//						}
//
//					} else if (detale.getEstado().equals("Vencido")) {
//						if (saldo >= detale.getSaldo()) {
//							Double sss = cuenta.getSaldoCuentaDeAhorro() - detale.getSaldo();
//							detale.setEstado("Pagado");
//							detale.setSaldo(0.00);
//							cuenta.setSaldoCuentaDeAhorro(sss);
//							detalleCreditoDAO.update(detale);
//							cuentaDeAhorroDAO.update(cuenta);
//
//						} else if (saldo > 0 && saldo < detale.getSaldo()) {
//
//							double valorP = detale.getSaldo() - cuenta.getSaldoCuentaDeAhorro();
//							detale.setSaldo(valorP);
//							cuenta.setSaldoCuentaDeAhorro(0.0);
//							// detale.setEstado("Vencido");
//
//							detalleCreditoDAO.update(detale);
//							cuentaDeAhorroDAO.update(cuenta);
//
//						}
//
//					}
//
//				}
//
//			}
//		}
//
//	}

	/**
	 * Metodo que permite dar acceso al cliente en la aplicación móvil mediante un servicio web.
	 * 
	 * @param username El nombre de usuario del cliente que se envio en el correo.
	 * @param password La contraseña del cliente que se envio en el correo de creación de la cuenta.
	 * @return Un clase Respuesta indicando los datos del desarrollo del proceso, con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */
//	public Respuesta loginServicio(String username, String password) {
//		Cliente cliente = new Cliente();
//		Respuesta respuesta = new Respuesta();
//		CuentaDeAhorro cuentaDeAhorro = new CuentaDeAhorro();
//		List<Credito> lstCreditos = new ArrayList<Credito>();
//		try {
//			cliente = clienteDAO.obtenerClienteUsuarioContraseña(username, password);
//			if (cliente != null) {
//				respuesta.setCodigo(1);
//				respuesta.setDescripcion("Ha ingresado exitosamente");
//				respuesta.setCliente(cliente);
//				cuentaDeAhorro = cuentaDeAhorroDAO.getCuentaCedulaCliente(cliente.getCedula());
//				respuesta.setCuentaDeAhorro(cuentaDeAhorro);
//				lstCreditos = creditosAprovados(cliente.getCedula());
//				List<CreditoRespuesta> lstNuevaCreditos = new ArrayList<CreditoRespuesta>();
//				for (Credito credito : lstCreditos) {
//					CreditoRespuesta creditoRespuesta = new CreditoRespuesta();
//					creditoRespuesta.setCodigoCredito(credito.getCodigoCredito());
//					creditoRespuesta.setEstado(credito.getEstado());
//					creditoRespuesta.setMonto(credito.getMonto());
//					creditoRespuesta.setInteres(credito.getInteres());
//					creditoRespuesta.setFechaRegistro(credito.getFechaRegistro());
//					creditoRespuesta.setFechaVencimiento(credito.getFechaVencimiento());
//					creditoRespuesta.setDetalles(credito.getDetalles());
//					lstNuevaCreditos.add(creditoRespuesta);
//				}
//				respuesta.setListaCreditos(lstNuevaCreditos);
//			}
//		} catch (Exception e) {
//			respuesta.setCodigo(2);
//			respuesta.setDescripcion("Error " + e.getMessage());
//		}
//		return respuesta;
//	}

	/**
	 * Metodo que permite cambiar la contraseña del cliente en la aplicación móvil mediante un servicio web.
	 * 
	 * @param correo El correo del cliente que describio cuando creo una cuenta de ahorros.
	 * @param contraAntigua La contraseña del cliente antigua.
	 * @param contraActual La contraseña del cliente nueva.
	 * @return Un clase Respuesta indicando los datos del desarrollo del proceso, con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */
	
//	public Respuesta cambioContraseña(String correo, String contraAntigua, String contraActual) {
//		System.out.println(correo + "" + contraAntigua);
//		Cliente cliente = new Cliente();
//		Respuesta respuesta = new Respuesta();
//		try {
//			cliente = clienteDAO.obtenerClienteCorreoContraseña(correo, contraAntigua);
//			System.out.println(cliente.toString());
//			cliente.setClave(contraActual);
//			clienteDAO.update(cliente);
//			respuesta.setCodigo(1);
//			respuesta.setDescripcion("Se ha actualizado su contraseña exitosamente"); 
//			cambioContrasena(cliente);
//		} catch (Exception e) {
//			respuesta.setCodigo(2);
//			respuesta.setDescripcion("Error " + e.getMessage());
//		}
//
//		return respuesta;
//	} 
	
	/**
	 * Método que permite realizar una transferencia externa en la aplicación móvil mediante un servicio web.
	 * 
	 * @param transferenciaExterna Una clase TransferenciaExterna que se envia en formato json  mediante el servicio web.
	 * @return Un clase RespuestaTransferenciaExterna indicando los datos del desarrollo del proceso, con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */
//	public RespuestaTransferenciaExterna realizarTransferenciaExterna(TransferenciaExterna transferenciaExterna) {  
//		RespuestaTransferenciaExterna respuestaTransferenciaExterna = new RespuestaTransferenciaExterna();
//		try {  
//			CuentaDeAhorro cuentaDeAhorro = cuentaDeAhorroDAO.read(transferenciaExterna.getCuentaPersonaLocal()); 
//			if(cuentaDeAhorro!=null) { 
//				if(cuentaDeAhorro.getSaldoCuentaDeAhorro()>=transferenciaExterna.getMontoTransferencia()) { 
//					transferenciaExterna.setFechaTransaccion(new Date());
//					transferenciaExternaDAO.insert(transferenciaExterna);  
//					cuentaDeAhorro.setSaldoCuentaDeAhorro(cuentaDeAhorro.getSaldoCuentaDeAhorro()-transferenciaExterna.getMontoTransferencia()); 
//					cuentaDeAhorroDAO.update(cuentaDeAhorro);
//					respuestaTransferenciaExterna.setCodigo(1); 
//					respuestaTransferenciaExterna.setDescripcion("Transferencia se ha realizado exitosamente"); 
//				}else { 
//					respuestaTransferenciaExterna.setCodigo(2);
//					respuestaTransferenciaExterna.setDescripcion("No tiene esa cantidad en su cuenta");
//				}
//			}else { 
//				respuestaTransferenciaExterna.setCodigo(3); 
//				respuestaTransferenciaExterna.setDescripcion("La cuenta no existe");
//			}
//		}catch (Exception e) {
//			respuestaTransferenciaExterna.setCodigo(4); 
//			respuestaTransferenciaExterna.setDescripcion("Error : " + e.getMessage());
//		}
//		return respuestaTransferenciaExterna;
//	}
//
}
