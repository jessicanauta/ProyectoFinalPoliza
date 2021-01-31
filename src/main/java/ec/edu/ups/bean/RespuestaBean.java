package ec.edu.ups.bean;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//import org.primefaces.event.CloseEvent;
//import org.primefaces.event.MoveEvent;

@ManagedBean
@RequestScoped
public class RespuestaBean {
	
//	public void handleClose(CloseEvent event) {
//        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
//    }
//     
//    public void handleMove(MoveEvent event) {
//        addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
//    }
     
    public void respuestaSolicitudCredito() {
        addMessage("Confirmacion", "Solicitud Guardada");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
