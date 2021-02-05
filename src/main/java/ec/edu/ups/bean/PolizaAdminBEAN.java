package ec.edu.ups.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.ups.modelo.PolizaAdmin;
import ec.edu.ups.negocio.GestionUsuarioLocal;

@Named
@RequestScoped
public class PolizaAdminBEAN implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PolizaAdmin polAdmin;
	
	private List <PolizaAdmin> ListpolAdm;
	
	@Inject
	private GestionUsuarioLocal polizaON;

	

	@PostConstruct
	public void init() {
		
		polAdmin=new PolizaAdmin();
		listaradmins();
		//crear();
	}
	
	
	public String crear(){

		polizaON.crearPolizaParam(polAdmin);
		listaradmins();
		return null;
	}
	 
	public void actualizar(PolizaAdmin polAdmin){
		System.out.println("hola aqui");
		polizaON.actualizarparam(polAdmin);
		listaradmins();
	}
	
	
	public String listaradmins(){
			ListpolAdm=polizaON.listaAdministrador();
			return "lista";
		}
	

	public PolizaAdmin getPolAdmin() {
		return polAdmin;
	}


	public void setPolAdmin(PolizaAdmin polAdmin) {
		this.polAdmin = polAdmin;
	}


	public List<PolizaAdmin> getPolAdm() {
		return ListpolAdm;
	}


	public void setPolAdm(List<PolizaAdmin> polAdm) {
		this.ListpolAdm = polAdm;
	}


 
	
	
		
}
