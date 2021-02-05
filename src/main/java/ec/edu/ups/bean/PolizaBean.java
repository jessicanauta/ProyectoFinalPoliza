package ec.edu.ups.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ec.edu.ups.modelo.Poliza;
import ec.edu.ups.negocio.GestionUsuarioLocal;


@ManagedBean
@SessionScoped
public class PolizaBean {

	@Inject
	private GestionUsuarioLocal gestionON;
	private Poliza newPoliza;
	private int tiempo;
	private int valor;
	
	
	
	@PostConstruct
	public void init() {
		newPoliza = new Poliza();
		
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public GestionUsuarioLocal getGestionON() {
		return gestionON;
	}
	public void setGestionON(GestionUsuarioLocal gestionON) {
		this.gestionON = gestionON;
	}
	public Poliza getNewPoliza() {
		return newPoliza;
	}
	public void setNewPoliza(Poliza newPoliza) {
		this.newPoliza = newPoliza;
	}
	
	public String cotizapoliza() {
		try {
				
		newPoliza.setInteres(getGestionON().validar_piliz(newPoliza.getPlazo(), newPoliza.getMonto()));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return null;
		
	}
	
	
}
