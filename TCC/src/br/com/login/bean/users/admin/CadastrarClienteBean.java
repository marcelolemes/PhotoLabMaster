package br.com.login.bean.users.admin;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;

import br.com.login.Dao.ClienteDao;
import br.com.login.model.Cliente;

@ViewScoped
@javax.faces.bean.ManagedBean
public class CadastrarClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1428777646489674738L;
	private Cliente cliente = new Cliente();
	ClienteDao clienteDao = new ClienteDao(); 

	public void gravarCliente() throws Exception {

		clienteDao.Gravar(cliente);

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
