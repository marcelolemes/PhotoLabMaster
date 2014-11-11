package br.com.photolab.bean.admin.cadastro;

import br.com.photolab.dao.modelo.ClienteDao;
import br.com.photolab.modelo.Cliente;

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

    public String gravarCliente() throws Exception {
        clienteDao.Gravar(cliente);
        return "/pages/admin/pagina_principal_admin.xhtml";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
