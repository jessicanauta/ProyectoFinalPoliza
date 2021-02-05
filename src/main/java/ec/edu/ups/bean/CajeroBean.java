package ec.edu.ups.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Credito;
import ec.edu.ups.modelo.CuentaDeAhorro;
import ec.edu.ups.modelo.DetalleCredito;
import ec.edu.ups.modelo.Transaccion;
import ec.edu.ups.negocio.GestionUsuarioLocal;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;


/**
 * Clase de tipo Bean para el manejo de JSF y archivos xhtml

 */
@ManagedBean
@ViewScoped
public class CajeroBean {
	@Inject
	private GestionUsuarioLocal clienteON;
	private Double monto;
	private Cliente cliente;
	private List<Transaccion> listaTra;
	private boolean editable;	
	private boolean editable2;
	private String tipoTransaccion;	
	private List<Credito> credito;	
	private String cedulaAux;	
	private int codigoAux;
	private int codigoAux2;
	private int codigoAux3;
	private Transaccion transaccionAux;
	
	

	@PostConstruct
	public void init() {
		transaccionAux = new Transaccion();
		cliente = new Cliente();
	}

	public GestionUsuarioLocal getClienteON() {
		return clienteON;
	}

	public void setClienteON(GestionUsuarioLocal clienteON) {
		this.clienteON = clienteON;
	}

	/**
	 * Metodo para obtener un Monto
	 * 
	 * @return Me devuelve un valor de una transaccion
	 */
	public Double getMonto() {
		return monto;
	}

	/**
	 * Metodo para asignar un valor al monto
	 * 
	 * @param monto El parametro moto me permite asignar un valor al monto
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	/**
	 * Metodo para obtener un Cliente
	 * 
	 * @return Un cliente para se utilizado en la paguina
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Metodo para asignar un cliente
	 * 
	 * @param cliente el parametro cliente me permite asignar un valor a mi variable
	 *                cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Metodo para obtener una lista de tipo transacciones
	 * 
	 * @return Una lista de Transacciones
	 */
	public List<Transaccion> getListaTra() {
		return listaTra;
	}

	/**
	 * Metodo para asignar valores a la lista
	 * 
	 * @param listaTra el parametro listaTra me permite asignar una lista de
	 *                 transacciones a mi variable local de Tipo Lista de
	 *                 Transacciones
	 */
	public void setListaTra(List<Transaccion> listaTra) {
		this.listaTra = listaTra;
	}

	/**
	 * Metodo para asignar un valor booleano
	 * 
	 * @return Si es TRUE o FALSE
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Metodo para asignar un valor booleano
	 * 
	 * @param editable El parametro editable me permite cambiar a TRUE o FALSE el
	 *                 booleano
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * Metdo para obtener el tipo de transaccion que se va a realizar
	 * 
	 * @return El valor del tipo de transaccion si es Retiro o Deposito
	 */
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	/**
	 * Asignar el Tipo de Transaccion
	 * 
	 * @param tipoTransaccion El parametro tipoTransaccion me permite ver que tipo
	 *                        de Transaccion se esta realizando.
	 */
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	
	/**
	 * Metodo para obtener los creditos
	 * @return Una lista de creditos
	 */
	public List<Credito> getCredito() {
		return credito;
	}
	
	/**
	 * Asignar las transacciones
	 * @param credito El parametro credito asigna los creditos de un cliente
	 */
	public void setCredito(List<Credito> credito) {
		this.credito = credito;
	}
	
	/**
	 * Metodo para obtener la cedulaAux
	 * @return La cedula de la persona que va a realizar el pago de la transaccion
	 */
	public String getCedulaAux() {
		return cedulaAux;
	}
	
	/**
	 * Asignar el valor de la cedula del cliente que va a realizar un pago de Credito
	 * @param cedulaAux El parametro cedulaAux me permite asignar la cedula del cliente que va
	 * a realizar el pago de credito.
	 */
	public void setCedulaAux(String cedulaAux) {
		this.cedulaAux = cedulaAux;
	}
	
	/**
	 * Metodo para obtener el valor de visualizacion
	 * @return Devuelve true o false para poder visualizar los paneles en la
	 * pagina del cajero
	 */
	public boolean isEditable2() {
		return editable2;
	}
	
	/**
	 * Metoo para asignar el valor a editable2
	 * @param editable2 El parametro editable2 me permite visualizar o no formularios
	 * el la pagina del cajero
	 */
	public void setEditable2(boolean editable2) {
		this.editable2 = editable2;
	}
	
	/**
	 * Metodo para obtener el codigo de un Credito
	 * @return El codigo del credito que se va a realizar el pago
	 */
	public int getCodigoAux() {
		return codigoAux;
	}
	
	/**
	 * Meoto para asignar el codifo de un credito
	 * @param codigoAux El parametro codigoAux me permite asignar el codigo 
	 * de una transaccion que se desea
	 */
	public void setCodigoAux(int codigoAux) {
		this.codigoAux = codigoAux;
	}
	
	/**
	 * Metodo para obtener una transaacion
	 * @return Una transaccion para poder realizar su respetivo registro
	 */
	public Transaccion getTransaccionAux() {
		return transaccionAux;
	}
	
	/**
	 * Asignar una Transaccion
	 * @param transaccionAux El parametro transaccionAux mepermite asignar
	 * una transaccion y sus valores
	 */
	public void setTransaccionAux(Transaccion transaccionAux) {
		this.transaccionAux = transaccionAux;
	}
		
	

	public int getCodigoAux2() {
		return codigoAux2;
	}

	public void setCodigoAux2(int codigoAux2) {
		this.codigoAux2 = codigoAux2;
	}

	public int getCodigoAux3() {
		return codigoAux3;
	}

	public void setCodigoAux3(int codigoAux3) {
		this.codigoAux3 = codigoAux3;
	}
	
	

	/**
	 * Metodo para validar la cedula de Un Cliente
	 * 
	 * @return Me devuelve un Mensaje si la ceudla es correcta, incorreta o si el
	 *         cliente no esta registrado
	 */
	public String valCedula() {
		if (cliente.getCedula() != null) {
			try {
				boolean c = clienteON.validadorDeCedula(cliente.getCedula());
				if (c) {
					Cliente usuarioRegistrado = clienteON.buscarCliente(cliente.getCedula());
					if (usuarioRegistrado != null) {
						CuentaDeAhorro cuen = clienteON.buscarCuentaDeAhorroCliente(usuarioRegistrado.getCedula());
						System.out.println("Registrado");
						String l = (String) (usuarioRegistrado.getNombre() + "    " + usuarioRegistrado.getApellido());
						editable = false;
						cargarTransacciones();
						return l;
					} else {
						String kl = "Cliente No registrado en el sistema";
						editable = false;
						cargarTransacciones();
						return kl;
					}
				} else {
					String ml = "Cedula Incorrecta";
					editable = false;
					cargarTransacciones();
					return ml;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

		}
		return " ";
	}
	
	/**
	 * Metodo para obtener el numero de cueta de un cliente
	 * @return El numero de cuenta de un cliente
	 */
	public String numCuenta() {
		
		try {
			boolean c;
			c = clienteON.validadorDeCedula(cliente.getCedula());
			if (c) {
				Cliente usuarioRegistrado = clienteON.buscarCliente(cliente.getCedula());
				if (usuarioRegistrado != null) {
					CuentaDeAhorro cuen = clienteON.buscarCuentaDeAhorroCliente(usuarioRegistrado.getCedula());
					return cuen.getNumeroCuentaDeAhorro();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;

	}

	/**
	 * Metodo para validar el Saldo de la Cuenta de Ahorro
	 * 
	 * @return El saldo disponible en la cuenta de ahorro de acuerdo a la
	 *         transaccion que se esta realizando
	 */
	public String valMonto() {
		try {
			if (cliente.getCedula() != null) {
				Cliente usuarioRegistrado = clienteON.buscarCliente(cliente.getCedula());
				if (usuarioRegistrado != null) {
					CuentaDeAhorro cl = clienteON.buscarCuentaDeAhorroCliente(cliente.getCedula());
					String l = String.valueOf(cl.getSaldoCuentaDeAhorro());
					if (tipoTransaccion.equalsIgnoreCase("retiro") && monto == null) {
						return l;
					} else if (tipoTransaccion.equalsIgnoreCase("retiro") && cl.getSaldoCuentaDeAhorro() < monto) {
						String ms = "La cuenta no cuenta con el saldo suficiente, Su saldo es: "
								+ cl.getSaldoCuentaDeAhorro();
						return ms;

					} else {
						return l;
					}

				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return " ";
	}

	/**
	 * Metodo para regitrar la transaccion
	 * 
	 * @return Me devuelve a la Pagina del cajero para realizar una nueva
	 *         transaccion
	 */
	public String registrar() {
		CuentaDeAhorro clp = clienteON.buscarCuentaDeAhorroCliente(cliente.getCedula());
		if (tipoTransaccion.equalsIgnoreCase("deposito")) {
			Double nvmonto = clp.getSaldoCuentaDeAhorro() + monto;
			clp.setSaldoCuentaDeAhorro(nvmonto);
			clienteON.actualizarCuentaDeAhorro(clp);
			Transaccion t = new Transaccion();
			t.setCliente(clp.getCliente());
			t.setMonto(monto);
			t.setFecha(new Date());
			t.setTipo("deposito");
			t.setSaldoCuenta(nvmonto);
			try {
				clienteON.guardarTransaccion(t);
				addMessage("Confirmacion", "Transaccion Guardada");
				editable = false;
				listaTra = new ArrayList<Transaccion>();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			try {
				FacesContext contex = FacesContext.getCurrentInstance();
				contex.getExternalContext().redirect("PaginaCajero.xhtml");
			} catch (Exception e) {
			}
		} else if (tipoTransaccion.equalsIgnoreCase("retiro") && monto <= clp.getSaldoCuentaDeAhorro()) {
			Double nvmonto2 = clp.getSaldoCuentaDeAhorro() - monto;
			clp.setSaldoCuentaDeAhorro(nvmonto2);
			clienteON.actualizarCuentaDeAhorro(clp);
			Transaccion t2 = new Transaccion();
			t2.setCliente(clp.getCliente());
			t2.setMonto(monto);
			t2.setFecha(new Date());
			t2.setTipo("retiro");
			t2.setSaldoCuenta(nvmonto2);
			try {
				
				clienteON.guardarTransaccion(t2);
				addMessage("Confirmacion", "Transaccion Guardada");
				editable = false;
				listaTra = new ArrayList<Transaccion>();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			try {
				
				FacesContext contex = FacesContext.getCurrentInstance();
				contex.getExternalContext().redirect("PaginaCajero.xhtml");
			} catch (Exception e) {
			}
		}
		return "PaginaCajero";
	}

	/**
	 * Metodo para Cargar las Transacciones
	 * 
	 * @return Una lista de transacciones del cliente que va a realizar un Deposito
	 *         o Retiro
	 */
	public String cargarTransacciones() {
		List<Transaccion> lis = clienteON.listadeTransacciones(cliente.getCedula());
		if (lis != null) {
			listaTra = lis;
			editable = true;
			editable2 = false;
		}
		return null;
	}
	
	
	/**
	 * Metodo para cargar los Credito del Cliente
	 * 
	 * @return Una lista de Creditos y sus detalles correspondientes al cliente.
	 */
	public List<Credito> cargarCreditos() {
		List<Credito> lisAux = new ArrayList<Credito>();
		try {
			List<Credito> lis = clienteON.listarCreditosCedula(cedulaAux);
			
			System.out.println(lis.size());
			for(Credito credito: lis) {
				if (credito.getEstado().equals("Pendiente")) {
					lisAux.add(credito);
				}
			}
			if (lisAux  != null) {
				return lisAux;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			//return lisAux;
		}
		return lisAux;
		
	}
	
	/**
	 * Metodo para cambiar el estado de la visualizacion de la ventana del pago de credito
	 * 
	 */
	 public void activar() {
		 try {
			 editable = true;
			 editable2 = false;
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	 }
	 
	 /**
	  * Metodo para cambiar el estado de pago de credito
	  *  
	  * @param cod El parametro cod me permite asignar el codigo del credito que se va a actualizar
	  */
	 public void cambioVar(int cod) {
		 codigoAux = cod;
		 editable = false;
		 editable2 = true;
		 
	 }
	 
	 /**
	  * Metodo para obtener los Credito Pagados del cliente
	  * 
	  * @return
	  */
	 public List<DetalleCredito> verDealles(){
		 List<DetalleCredito> list = clienteON.verCredito(codigoAux).getDetalles();
		 List<DetalleCredito> list2 = new ArrayList<DetalleCredito>();
		 for(DetalleCredito credito:list) {
			 if (!credito.getEstado().equals("Pagado")) {
				 list2.add(credito);
			}
		 }
		 
		 return list2;
	 }
	 
	 /**
	  * Metodo para guradar la transaccion del pago de un credito
	  */
	 public void guardar() {
		 try {
			 
			 List<DetalleCredito> listt = clienteON.verCredito(codigoAux).getDetalles();
			 if (transaccionAux.getTipo().equals("pagoC")) {
				for(DetalleCredito credito: listt) {
					if (transaccionAux.getMonto() >= credito.getSaldo()  && credito.getCodigoDetalle() == codigoAux2) {
						transaccionAux.setTipo("PagoCredito");
						transaccionAux.setFecha(new Date());
						transaccionAux.setSaldoCuenta(credito.getMonto());;
						credito.setEstado("Pagado");
						credito.setSaldo(0.00);
						clienteON.actualizarDetalle(credito);

					}
				}
				
			}else if (transaccionAux.getTipo().equals("pagoAb")) {
				for(DetalleCredito credito: listt) {
					if (transaccionAux.getMonto() <= credito.getSaldo() && credito.getCodigoDetalle() == codigoAux2) {
						transaccionAux.setTipo("PagoCredito");
						transaccionAux.setFecha(new Date());
						transaccionAux.setSaldoCuenta(credito.getMonto());
						//credito.setEstado("PagoAbono");
						double valor = credito.getSaldo() - transaccionAux.getMonto();
						String num = String.format(Locale.ROOT, "%.2f", valor);
						credito.setSaldo(Double.parseDouble(num));
						if (valor > 0) {
							credito.setEstado("PagoAbono");
							credito.setSaldo(Double.parseDouble(num));
							clienteON.actualizarDetalle(credito);
						}else if(valor <= 0) {
							credito.setEstado("Pagado");
							credito.setSaldo(0);
							clienteON.actualizarDetalle(credito);

						}
						
						
					}
				}
				
			}else if(transaccionAux.getTipo().equals("pagoA")) {
				for(DetalleCredito credito: listt) {
					if (transaccionAux.getMonto() <= credito.getSaldo() && credito.getCodigoDetalle() == codigoAux2) {
						transaccionAux.setTipo("PagoCredito");
						transaccionAux.setFecha(new Date());
						transaccionAux.setSaldoCuenta(credito.getMonto());
						credito.setEstado("PagoAbono");
						double valor = credito.getSaldo() - transaccionAux.getMonto();
						String num = String.format(Locale.ROOT, "%.2f", valor);
						credito.setSaldo(Double.parseDouble(num));
						clienteON.actualizarDetalle(credito);

						
						
					}else if (transaccionAux.getMonto() >= credito.getSaldo() && credito.getCodigoDetalle() == codigoAux2) {
						transaccionAux.setTipo("PagoCredito");
						transaccionAux.setFecha(new Date());
						transaccionAux.setSaldoCuenta(credito.getMonto());
						credito.setEstado("Pagado");
						credito.setSaldo(0.00);
						clienteON.actualizarDetalle(credito);

						
						
					}
				}
			}
			 
			 
			
			 Cliente cliente = clienteON.buscarCliente(cedulaAux);
			 transaccionAux.setCliente(cliente);
			 try {
			//	addMessage("Confirmacion", "Transaccion Guardada");
				clienteON.guardarTransaccion(transaccionAux);
				editable = false;
			    transaccionAux = new Transaccion();
			    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 List<DetalleCredito> lisComprobar = clienteON.verCredito(codigoAux).getDetalles();
			 int cont = 0;
			 for(DetalleCredito credito: listt) {
				 if (credito.getEstado().equals("Pagado")) {
					cont += 1;
				}
			 }

			 if (cont == listt.size()) {
				 Credito nv = clienteON.verCredito(codigoAux);
				 nv.setEstado("Pagado");
				 clienteON.actualizarCredito(nv);
			}
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	 }
	 

	
	 /**
	  * Metodo para mostras el mensaje en la pagina del cajero
	  * @param summary El parametro sumary me permite asignar un valor a la pagina
	  * @param detail El parametro detail permite establecer el mensaje de la pagina
	  */
	 public void addMessage(String summary, String detail) {   
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.getExternalContext().getFlash().setKeepMessages(true);
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	    }
	 
	 public void handleClose(CloseEvent event) {
	        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
	    }
	     
	    public void handleMove(MoveEvent event) {
	        event.setTop(500);
	    	addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
	    }

}
