package br.com.login.bean.users.admin.cadastro;

import br.com.login.Dao.ClienteDao;
import br.com.login.model.Cliente;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ViewScoped
@javax.faces.bean.ManagedBean
public class CadastrarClienteBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1428777646489674738L;
    ClienteDao clienteDao = new ClienteDao();
    private Cliente cliente = new Cliente();

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
