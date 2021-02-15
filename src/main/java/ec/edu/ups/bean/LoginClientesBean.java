package ec.edu.ups.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.SesionCliente;
import ec.edu.ups.negocio.GestionUsuarioLocal;

/** 
 * Esta clase implementa la logica que se utilizara en las diferentes interfaces para poder utilizar las entidades o clases
 */
@ManagedBean 
@SessionScoped
public class LoginClientesBean {
	//Atributos de la clase
	@Inject 
	private GestionUsuarioLocal gestionUsuarios;  
	private Cliente cliente;  
	private String usuario; 
	private String contraseña; 
	
	/** 
	 * Metodo que permite inicializar atributos y metodos al momento que se llama a esta clase
	 */
	@PostConstruct 
	public void init() { 
		cliente = new Cliente();
	}
	
	/** 
	 * Metodo que permite obtener el atributo gestionUsuarios
	 * @return Atributo gestionUsuarios de la clase
	 */
	public GestionUsuarioLocal getGestionUsuarios() {
		return gestionUsuarios;
	}
	
	/** 
	 * Metodo que permite asignar un valor al atributo gestionUsuarios
	 * @param gestionUsuarios Variable asignada al atributo gestionUsuarios de la clase
	 */
	public void setGestionUsuarios(GestionUsuarioLocal gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
	}
	
	/**
	 * Metodo que permite obtener el atributo cliente
	 * @return Atributo cliente de la clase
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/** 
	 * Metodo que permite asignar un valor al atributo cliente
	 * @param cliente Variable asignada al atributo cliente de la clase
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	} 
	
	/** 
	 * Metodo que permite obtener el atributo usuario
	 * @return Atributo usuario de la clase
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/** 
	 * Metodo que permite asignar un valor al atributo usuario
	 * @param usuario Variable asignada al atributo usuario de la clase
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Metodo que permite obtener el atributo contraseña
	 * @return  Atributo contraseña de la clase
	 */
	public String getContraseña() {
		return contraseña;
	}
	
	/** 
	 * Metodo que permite asignar un valor al atributo contraseña
	 * @param contraseña Variable asignada al atributo contraseña de la clase
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	} 
	
	/** 
	 * Metodo que permite guardar una sesion 
	 * @return Nombre de Pagina a donde se va a redirigir la pagina
	 */
	public 	String validarCliente() {  
		List<Cliente> lstClis = gestionUsuarios.listaClientes();  
		System.out.println("PASO LA LISTAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		for(Cliente c : lstClis) {  
			System.out.println("ENTROOOOOOOOOOOO EN EL FORRRRRR");
			if(c.getUsuario().equalsIgnoreCase(usuario) && c.getClave().equalsIgnoreCase(contraseña)) { 
				System.out.println("ENTROOOOOOOOOOOO EN EL IFFFFFFFFFFFFFF CORRECTO");
				SesionCliente sesionCliente = new SesionCliente();
				sesionCliente.setCliente(c);  
				sesionCliente.setFechaSesion(new Date());
				sesionCliente.setEstado("Correcto");  
				gestionUsuarios.guardarSesion(sesionCliente);
				try { 
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", c);

					
					
					FacesContext contex = FacesContext.getCurrentInstance();
					contex.getExternalContext().redirect("PaginaPrincipalCliente.xhtml?faces-redirect=true&cedula="+c.getCedula());
				} catch (Exception e) {
				}
			}else if(c.getUsuario().equalsIgnoreCase(usuario)) {  
				System.out.println("ENTROOOOOOOOOOOO EN EL IFFFFFFFFFFFFFF MAL"); 
				SesionCliente sesionCliente2 = new SesionCliente();
				sesionCliente2.setCliente(c);  
				sesionCliente2.setFechaSesion(new Date());
				sesionCliente2.setEstado("Incorrecto");  
				gestionUsuarios.guardarSesion(sesionCliente2);
				return "InicioClientes";
			}
		} 
		return "InicioClientes";
	} 
	
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "InicioClientes?faces-redirect=true";
	  // return null;
        
    }
	
	
	
	
}
