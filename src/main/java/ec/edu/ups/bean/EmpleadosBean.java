package ec.edu.ups.bean;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

//import org.primefaces.event.CloseEvent;
//import org.primefaces.event.MoveEvent;

import ec.edu.ups.modelo.Credito;
import ec.edu.ups.modelo.DetalleCredito;
import ec.edu.ups.modelo.Empleado;
import ec.edu.ups.modelo.SolicitudDeCredito;
import ec.edu.ups.negocio.GestionUsuarioLocal;
import ec.edu.ups.negocio.GestionUsuarioLocalPoliza;

/**
 * Clase de tipo Bean para el manejo de JSF y archivos xhtml

 */
@ManagedBean
@ViewScoped
public class EmpleadosBean {

	@Inject
	private GestionUsuarioLocal empleadoON;

	private Empleado empleado;

	private boolean ced;

	private List<Empleado> listaEmpleados;

	private String tipoEmpleado;

	private List<SolicitudDeCredito> solicitudes;

	private SolicitudDeCredito solicitudDeCredito;
	
	private boolean editable = false;
	
	private boolean editabledos = false;
	
	private String motivo;

	@PostConstruct
	public void init() {
		empleado = new Empleado();
		//solicitudDeCredito = new SolicitudDeCredito();
		loadData();
		loadDataSol();
	}

	public GestionUsuarioLocal getEmpleadoON() {
		return empleadoON;
	}

	public void setEmpleadoON(GestionUsuarioLocal empleadoON) {
		this.empleadoON = empleadoON;
	}

	/**
	 * Metodo para obtener un Empleado
	 * 
	 * @return Un empleado para un registro en la Base de Datos
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * Metodo para asignar un Empleado
	 * 
	 * @param empleado El parametro empleado me Permite asignar datos a un Empleado
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	/**
	 * Metodo para Obtener un Mensaje
	 * 
	 * @return Si es TRUE o False
	 */
	public boolean isCed() {
		return ced;
	}

	/**
	 * Metodo para asignar un valor
	 * 
	 * @param ced El parametro ced me permite asignar el volor booleano de TRUE o
	 *            FALSE
	 */
	public void setCed(boolean ced) {
		this.ced = ced;
	}

	/**
	 * Metodo para obtener lista de Empleados
	 * 
	 * @return Una lista de tipo Empleados
	 */
	public List<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	/**
	 * Metodo para asignar una lista de Empleado
	 * 
	 * @param listaEmpleados El parametro listaEmpleados me asigna los datos de los
	 *                       Empleados a mi lista
	 */
	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	/**
	 * Metodo para obtener un tipo de Empleado
	 * 
	 * @return El Tipod de Empleado que se esta asignando en la pagina xhtml
	 */
	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	/**
	 * Metodo para asignar el tipo de Empleado
	 * 
	 * @param tipoEmpleado El parametro tipoEmpleado me permite asignar el tipo de
	 *                     empleado seleccionado en la pagina xhtml
	 */
	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}
	
	/**
	 * Metodo para obtneter Solicitudes de Credito
	 * @return Una lista de solicitues de credito
	 */
	public List<SolicitudDeCredito> getSolicitudes() {
		return solicitudes;
	}
	
	/**
	 * Asignar solicitudes
	 * @param solicitudes El parametro solicitudes me permite asignar aquellas
	 * solicitudes de credito deseadas
	 */
	public void setSolicitudes(List<SolicitudDeCredito> solicitudes) {
		this.solicitudes = solicitudes;
	}
	
	/**
	 * Metodo para obtener una Solicitud de credito
	 * @return Una solicitud de credito
	 */
	public SolicitudDeCredito getSolicitudDeCredito() {
		return solicitudDeCredito;
	}
	
	/**
	 * Asignar los valor de solicitud de credito
	 * @param solicitudDeCredito El parametro solicitudDeCredito me permite 
	 * asignar los valores de una solicutud de credito
	 */
	public void setSolicitudDeCredito(SolicitudDeCredito solicitudDeCredito) {
		this.solicitudDeCredito = solicitudDeCredito;
	}
	
	
	/**
	 * Metodo para asiganr un booleano 
	 * @return Un boleano con TRUE O FALSE
	 */
	public boolean getEditable() {
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
	 * Metodo para obtner el valor de la variable motivo
	 * @return El valor de la variable motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	
	/**
	 * Asignar el valor de la variable motivo
	 * @param motivo El parametro motivo me permite asignar el valor
	 * del motivo
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	/**
	 * Metofo para obtner el valor de la variable editabledos
	 * @return El valor de la variable editabledos que puede ser true o false
	 */
	public boolean getEditabledos() {
		return editabledos;
	}
	
	/**
	 * Asignar el valor a la variable editabledos
	 * @param editabledos El parametro editabledos me permite asiganar el
	 * valor de true o false
	 */
	public void setEditabledos(boolean editabledos) {
		this.editabledos = editabledos;
	}

	/**
	 * Metodo para guardar datos del Empleado
	 * 
	 * @return La paguina con la lista de los Empleados registrados
	 */
	public String guardarDatos() {

		System.out.println(this.empleado.getCedula() + "   " + this.empleado.getNombre() + tipoEmpleado);

		try {
			if (tipoEmpleado.equalsIgnoreCase("cajero")) {
				empleado.setRol("Cajero");
				empleadoON.guardarEmpleado(empleado);
				addMessage("Confirmacion", "Empleado Guardado");
				
			} else if (tipoEmpleado.equalsIgnoreCase("asistente")) {
				empleado.setRol("Asistente");
				empleadoON.guardarEmpleado(empleado);
				addMessage("Confirmacion", "Empleado Guardado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			FacesContext contex = FacesContext.getCurrentInstance();
			contex.getExternalContext().redirect("Lista-Empleados.xhtml");
		} catch (Exception e) {
		}
		return null;

	}


	/**
	 * Metodo para validar un Empleado
	 * 
	 * @return Mensaje si el Empleado esta registrado en la Base de Datos
	 */
	public String valCedula() {
		System.out.println("*-------*" + empleado.getCedula());
		if (empleado.getCedula() != null) {
			Empleado usuarioRegistrado = empleadoON.usuarioRegistrado(empleado.getCedula());
			if (usuarioRegistrado != null) {
				System.out.println("Registrado");
				return "Empleado REGISTRADO";
			}
			try {
				ced = empleadoON.validadorDeCedula(empleado.getCedula());
				System.out.println(ced);
				if (ced) {
					return "Cedula Valida";
				} else if (ced == false) {
					return "Cedula Incorrecta";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return " ";
	}

	/**
	 * Metodo para asignar la lista de Empleados registrados en la Base de Datos
	 */
	public void loadData() {
		listaEmpleados = empleadoON.listadoEmpleados();
	}
	
	/**
	 * Metodo para cargar las solicitudes de Credito
	 */
	public void loadDataSol() {
		solicitudes = empleadoON.listadoSolicitudDeCreditos();
	}
	
	/**
	 * Metodo para cargar las solicitudes de Credito
	 * @param cod el parametro codigho me permite mostrar la solicitud con el codigo
	 * igual al parametro cod
	 */
	public String cargarSol(int cod) {
		editable = true;
		System.out.println("**********/****/--"+cod+editable);
		
		for (SolicitudDeCredito sol : solicitudes) {
			if (sol.getCodigoCredito() == cod) {
				solicitudDeCredito = sol;
			}
		}
		return null;
	}
	
	/**
	 * Metodo para actualizar el estado de una salicitud de credito
	 * @param cod El parametro cod me permite actualizar la solicitud con el codigo igual al parametro cod
	 * @return El nombre de la pagina del Jefe de credito
	 */
	public String aprobar(int cod) {	
		for (SolicitudDeCredito sol : solicitudes) {
			if (sol.getCodigoCredito() == cod && sol.getEstadoCredito().equalsIgnoreCase("Solicitando") ) {
				solicitudDeCredito.setEstadoCredito("Aprobado");
				empleadoON.actualizarSolicitudCredito(solicitudDeCredito);
				Credito credito = new Credito();
				credito.setFechaRegistro(new Date());
				credito.setInteres(credito.getInteres());
				credito.setMonto(sol.getMontoCredito());
				//credito.setJefeC();
				credito.setEstado("Pendiente");
				credito.setSolicitud(sol);
				List<DetalleCredito> li = empleadoON.crearTablaAmortizacion(Integer.parseInt(sol.getMesesCredito()), sol.getMontoCredito(), credito.getInteres());
				System.out.println(li.toString());
				credito.setDetalles(li);
				empleadoON.guardarCredito(credito);
			//	empleadoON.aprobarCredito(credito, sol.getClienteCredito());
				
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
		//System.out.println(solicitudDeCredito.getCodigoCredito());
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
			}else if (tipo.equalsIgnoreCase("planilla")) {
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
			byte[] buffer = new byte [10240];
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
	
	public void addMessage(String summary, String detail) { 
		FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
 
 public void handleClose(CloseEvent event) {
        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
    }
     
    public void handleMove(MoveEvent event) {
        event.setTop(500);
    	addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
    }
	

}
