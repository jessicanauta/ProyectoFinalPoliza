package ec.edu.ups.bean;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.modelo.Credito;
import ec.edu.ups.modelo.DetalleCredito;
import ec.edu.ups.modelo.Empleado;
import ec.edu.ups.modelo.SolicitudDeCredito;
import ec.edu.ups.negocio.GestionUsuarioLocalPoliza;

/**
 * Clase de tipo Bean para el manejo de JSF y archivos xhtml
 */
@ManagedBean
@SessionScoped
public class LoginBean {
	@Inject
	private GestionUsuarioLocalPoliza empleadoON;

	private String usuario;

	private String contrasena;

	private List<SolicitudDeCredito> solicitudes;

	private SolicitudDeCredito solicitudDeCredito;

	private SolicitudDeCredito solicitudDeCreditoAux;

	private boolean editable = false;

	private boolean editabledos = false;

	private String motivo;

	private Empleado empleado;

	private String tipoC;

	@PostConstruct
	public void init() {
		solicitudes = new ArrayList<SolicitudDeCredito>();
		loadDataSol();
		empleado = new Empleado();
		
	}

	public GestionUsuarioLocalPoliza getEmpleadoON() {
		return empleadoON;
	}

	public void setEmpleadoON(GestionUsuarioLocalPoliza empleadoON) {
		this.empleadoON = empleadoON;
	}

	/**
	 * Metodo para obtener el usuario
	 * 
	 * @return El usuario o valor digitado en la pagina xhtml
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Metodo para asignar un usuario
	 * 
	 * @param usuario El parametro usuario me permite asignar valor a mi variable
	 *                usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Metodo para obtener la contraseña
	 * 
	 * @return La contraseña que se ingresa en la pagina xhtml
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Metodo para asignar una contraseña
	 * 
	 * @param contrasena El parametro contraseña me permite asignar un valor a mi
	 *                   variable contrasena
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	/**
	 * Metodo para obtener solicitudes
	 * @return Una lista de solicitudes de Credito
	 */
	public List<SolicitudDeCredito> getSolicitudes() {
		return solicitudes;
	}
	
	/**
	 * Asignar solicitudes de Credito
	 * @param solicitudes El parametro solicitudes me permite asignar las
	 * solicitudes de credito de la aplicacion
	 */
	public void setSolicitudes(List<SolicitudDeCredito> solicitudes) {
		this.solicitudes = solicitudes;
	}
	
	/**
	 * Metodo para obter unasolicitud de credito
	 * @return Una solicitud de credito
	 */
	public SolicitudDeCredito getSolicitudDeCredito() {
		return solicitudDeCredito;
	}
	
	/**
	 * Asignar una solicitud de credito
	 * @param solicitudDeCredito El parametro solicitudDeCredito me permite asignar los
	 * valores del credito
	 */
	public void setSolicitudDeCredito(SolicitudDeCredito solicitudDeCredito) {
		this.solicitudDeCredito = solicitudDeCredito;
	}
	
	/**
	 * Metodo para asiganr un booleano 
	 * @return Un boleano con TRUE O FALSE
	 */
	public boolean isEditable() {
		return editable;
	}
	
	/**
	 * Asignar el valor a editable
	 * @param editable El parametro editable me permite asignar los valores de true o false
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	/**
	 * Metodo para asiganr un booleano 
	 * @return Un boleano con TRUE O FALSE
	 */
	public boolean isEditabledos() {
		return editabledos;
	}
	
	/**
	 * Asignar el valor a editable
	 * @param editabledos El parametro editabledos me permite asignar los valores de true o false
	 */
	public void setEditabledos(boolean editabledos) {
		this.editabledos = editabledos;
	}
	
	/**
	 * Metodo para obtener el motivo de credito
	 * @return El motivo de rechazo del credito
	 */
	public String getMotivo() {
		return motivo;
	}
	
	/**
	 * Asignar el motivo del credito
	 * @param motivo El parametro motivo me permite asignar
	 * el motivo del credito
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	/**
	 * Metodo para obtener el empleado
	 * @return
	 */
	public Empleado getEmpleado() {
		return empleado;
	}
	
	/**
	 * Asignar un empleado
	 * @param empleado El parametro empleado me permite asignar
	 * el empleado que se encuentra conectado
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	/**
	 * Metodo para obtener el tipo de cliente
	 * @return El tipo de cliente
	 */
	public String getTipoC() {
		return tipoC;
	}
	
	/**
	 * Asignar el tipo de cliente
	 * @param tipoC El parametro tipoC me permite asignar
	 * el tipo de cliente
	 */
	public void setTipoC(String tipoC) {
		this.tipoC = tipoC;
	}
	
	/**
	 * Metodo para obtener una solicitud de credito
	 * @return Una solicitud de credito
	 */
	public SolicitudDeCredito getSolicitudDeCreditoAux() {
		return solicitudDeCreditoAux;
	}
	
	/**
	 * Asignar una solicitud de credito
	 * @param solicitudDeCreditoAux El parametro solicitudDeCreditoAux me permite
	 * adignar los valores de una solicitud de Credito
	 */
	public void setSolicitudDeCreditoAux(SolicitudDeCredito solicitudDeCreditoAux) {
		this.solicitudDeCreditoAux = solicitudDeCreditoAux;
	}

	/**
	 * Metodo para validar rol de Empleado
	 * 
	 * @return La pagina de acuerdo al rol del empleado
	 */
	public String validarUsuario() {
		Empleado emp;
		try {
			emp = empleadoON.usuario(usuario, contrasena);
			System.out.println("***************" + emp.getNombre());
			empleado = emp;
			if (emp != null && emp.getRol().equalsIgnoreCase("Cajero")) {
				try {
					addMessage("OK", "Ingreso");
					
					
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
					//FacesContext contex2 = FacesContext.getCurrentInstance();
					FacesContext contex = FacesContext.getCurrentInstance();
					
					contex.getExternalContext().redirect("PaginaCajero.xhtml");
				} catch (Exception e) {
				}
			} else if (emp != null && emp.getRol().equalsIgnoreCase("Asistente")) {
				try {
					loadDataSol();
					FacesContext contex = FacesContext.getCurrentInstance();
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
					contex.getExternalContext().redirect("PaginaJefeCredito.xhtml");
				} catch (Exception e) {
				}
			} else if (emp != null && emp.getRol().equalsIgnoreCase("Admin")) {
				try {
					FacesContext contex = FacesContext.getCurrentInstance();
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
					contex.getExternalContext().redirect("Admin.xhtml");
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			addMessage("ERROR", "NO SE PUEDO INGRESAR, REVISE USUARIO CONTRASEÑA");
			return "InicioUsuarios";
		}
		
		return null;
	}
	
	/**
	 * Metodo para cargar las solicitudes de credito
	 */
	public void loadDataSol() {
		solicitudes = new ArrayList<SolicitudDeCredito>();
		System.out.println("ENTRAAAAAAAA EN LOADDATASOL");
		// solicitudes = empleadoON.listadoSolicitudDeCreditos();
		List<SolicitudDeCredito> soli = empleadoON.listadoSolicitudDeCreditos();
		System.out.println(soli.size());
		List<SolicitudDeCredito> actual = new ArrayList<SolicitudDeCredito>();
		for (SolicitudDeCredito sol : soli) {
			if (sol.getEstadoCredito().equals("Solicitando")) {
				actual.add(sol);
			}
		}
		solicitudes = actual;
	}
	
	/**
	 * Metodo para obtener solicitudes en estado Solicitando
	 * @return
	 */
	public List<SolicitudDeCredito> loTTT() {
		System.out.println("ENTRAAAAAAAA EN LOADDATASOL");
		// solicitudes = empleadoON.listadoSolicitudDeCreditos();
		List<SolicitudDeCredito> soli = empleadoON.listadoSolicitudDeCreditos();
		System.out.println(soli.size());
		List<SolicitudDeCredito> actual = new ArrayList<SolicitudDeCredito>();
		for (SolicitudDeCredito sol : soli) {
			if (sol.getEstadoCredito().equals("Solicitando")) {
				actual.add(sol);
			}
		}
		return actual;
	}
	
	/**
	 * Metodo para carfar las solicitudes aprobadas segun el tipo de cliente
	 * @param apr El parametro me permite devolver las listas de los
	 * creditos segun el tipo de cliente
	 * @return Lista de solicitudes de creditos
	 */
	public List<SolicitudDeCredito> loadDataSolAR(String apr) {
		System.out.println("ENTRAAAAAAAA APROBADOS RECHAZADOS");
		// solicitudes = empleadoON.listadoSolicitudDeCreditos();
		List<SolicitudDeCredito> soli = empleadoON.listadoSolicitudDeCreditos();
		System.out.println(soli.size());
		List<SolicitudDeCredito> actual = new ArrayList<SolicitudDeCredito>();
		List<SolicitudDeCredito> actual2 = new ArrayList<SolicitudDeCredito>();
		/*for (SolicitudDeCredito sol : soli) {
			if (sol.getTipoCliente().equals("1")) {
				actual.add(sol);
			} else if (sol.getTipoCliente().equals("2")) {
				actual2.add(sol);
			}
		}*/

		if (apr.equals("Ap")) {
			return actual;
		} else if (apr.equals("Rch")) {
			return actual2;
		}
		return null;
	}
	
	/**
	 * Metodo para obtenet la solicitud de credito que se desea visualizar
	 * @param cod El parametro cod me permite devolver la solicitud de credito
	 * con el codigo igual al parametro cod
	 * @return Una solicitud de credito
	 */
	public String cargarSol(int cod) {
		editable = true;
		System.out.println("**********/****/--" + cod + editable);

		for (SolicitudDeCredito sol : solicitudes) {
			if (sol.getCodigoCredito() == cod) {
				solicitudDeCredito = sol;
			//	tipoC = tipoCliente(sol);
			}
		}
		return null;
	}
	
	/**
	 * Metodo para cargar las solicitudes
	 * @param cod El parametro codigo me permite obtener la solicitud
	 * con el codigo igual al parametro cod
	 */
	public String cargarSolAR(int cod) {
		editable = true;
		System.out.println("**********/****/--" + cod + editable);
		List<SolicitudDeCredito> solii = empleadoON.listadoSolicitudDeCreditos();
		for (SolicitudDeCredito sol : solii) {
			if (sol.getCodigoCredito() == cod) {
				solicitudDeCreditoAux = sol;
				//tipoC = tipoCliente(sol);
			}
		}
		return null;
	}
	
	/**
	 * Metodo para mostrar el mensaje del tipo de cliente
	 * @param credito
	 * @return
	 */
/*	public String tipoCliente(SolicitudDeCredito credito) {
		String tipo = credito.getTipoCliente();
		if (tipo.equals("1")) {
			String mensaje = "Este un Buen cliente para el credito, Se recomienda Aprobar";
			return mensaje;
		} else if (tipo.equalsIgnoreCase("2")) {
			String mensaje2 = "Es un MAL CLIENTE  para el credito, Se recomienda Rechazar";
			return mensaje2;
		}

		return " ";
	}
	*/
	/**
	 * Metodo para actualizar el estado de una salicitud de credito
	 * @param cod El parametro cod me permite actualizar la solicitud con el codigo igual al parametro cod
	 * @return El nombre de la pagina del Jefe de credito
	 */
	public String aprobar(int cod) {
		System.out.println("//////-/////////-/////" + empleado.getNombre());
		for (SolicitudDeCredito sol : solicitudes) {
			if (sol.getCodigoCredito() == cod && sol.getEstadoCredito().equalsIgnoreCase("Solicitando")) {

				Credito credito = new Credito();
				credito.setFechaRegistro(new Date());
				credito.setInteres(12);
				credito.setMonto(sol.getMontoCredito());
				credito.setJefeC(empleado);
				credito.setEstado("Pendiente");
				credito.setSolicitud(sol);
				List<DetalleCredito> li = empleadoON.crearTablaAmortizacion(Integer.parseInt(sol.getMesesCredito()),
						sol.getMontoCredito(), 12.00);
				System.out.println(li.toString());
				credito.setFechaVencimiento(li.get(li.size()-1).getFechaPago());
				credito.setDetalles(li);
				empleadoON.guardarCredito(credito);
				//empleadoON.aprobarCredito(credito, sol.getClienteCredito());
				System.out.println(credito);
				solicitudDeCredito.setEstadoCredito("Aprobado");
				empleadoON.actualizarSolicitudCredito(solicitudDeCredito);
				//CuentaDeAhorro ccv = empleadoON.buscarCuentaDeAhorroCliente(sol.getClienteCredito().getCedula());
			//	ccv.setSaldoCuentaDeAhorro(ccv.getSaldoCuentaDeAhorro() + sol.getMontoCredito());
			//	empleadoON.actualizarCuentaDeAhorro(ccv);
				solicitudDeCredito = new SolicitudDeCredito();
				editable = false;
				editabledos = false;
				loadDataSol();
			}
		}

		return "PaginaJefeCredito";
	}
	
	/**
	 * Metodo para rechazar una solicitud de Credito
	 * @return El nombre de la pagina del Jefe de credito
	 */
	public String rechazar() {
		solicitudDeCredito.setEstadoCredito("Rechazado");

		empleadoON.actualizarSolicitudCredito(solicitudDeCredito);
		System.out.println(motivo);
		// System.out.println(solicitudDeCredito.getCodigoCredito());
		//empleadoON.rechazarCredito(solicitudDeCredito.getClienteCredito(), motivo);
		solicitudDeCredito = new SolicitudDeCredito();
		editable = false;
		editabledos = false;
		loadDataSol();
		return "PaginaJefeCredito";
	}
	
	public void cambio() {
		editable = false;
		editabledos = true;
	}
	
	/**
	 * Metodo para visualizar los documentos de una solicitud
	 * @param tipo El parametro tipo nos permite asignar el nombre del documento que se desea visualizar
	 * @throws IOException Excepcion para errores de visualizacion
	 */
	public void ver(String tipo) throws IOException {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		File file = File.createTempFile("archivoTemp", ".pdf");
		try (FileOutputStream fos = new FileOutputStream(file)) {
			if (tipo.equalsIgnoreCase("cedula")) {
				fos.write(solicitudDeCredito.getArCedula());
			} else if (tipo.equalsIgnoreCase("planilla")) {
				fos.write(solicitudDeCredito.getArPlanillaServicios());
			
			}

		}
		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			// Open file.
			input = new BufferedInputStream(new FileInputStream(file), 10240);

			// Init servlet response.
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
			output = new BufferedOutputStream(response.getOutputStream(), 10240);

			// Write file contents to response.
			byte[] buffer = new byte[10240];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			// Finalize task.
			output.flush();
		} finally {

		}

		// Inform JSF that it doesn't need to handle response.
		// This is very important, otherwise you will get the following exception in the
		// logs:
		// java.lang.IllegalStateException: Cannot forward after response has been
		// committed.
		facesContext.responseComplete();
	}

	public void ver2(String tipo) throws IOException {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		File file = File.createTempFile("archivoTemp", ".pdf");
		try (FileOutputStream fos = new FileOutputStream(file)) {
			if (tipo.equalsIgnoreCase("cedula")) {
				fos.write(solicitudDeCreditoAux.getArCedula());
			} else if (tipo.equalsIgnoreCase("planilla")) {
				fos.write(solicitudDeCreditoAux.getArPlanillaServicios());
			}

		}
		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			// Open file.
			input = new BufferedInputStream(new FileInputStream(file), 10240);

			// Init servlet response.
			response.reset();
			response.setHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Length", String.valueOf(file.length()));
			response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
			output = new BufferedOutputStream(response.getOutputStream(), 10240);

			// Write file contents to response.
			byte[] buffer = new byte[10240];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			// Finalize task.
			output.flush();
		} finally {

		}

		// Inform JSF that it doesn't need to handle response.
		// This is very important, otherwise you will get the following exception in the
		// logs:
		// java.lang.IllegalStateException: Cannot forward after response has been
		// committed.
		facesContext.responseComplete();
	}
	
	/*public String datos() {
		String res = empleadoON.getDatos();
		System.out.println(res);
		return "";
	}
	
	*/
	public void addMessage(String summary, String detail) {
		 System.out.println(summary+"mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmkkk"+detail);
	        /*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	        FacesContext.getCurrentInstance().addMessage(null, message);*/
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	    }
	
	
	   public String logout() {
	        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	        return "InicioUsuarios?faces-redirect=true";
		  // return null;
	        
	    }
	
}
