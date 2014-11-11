package br.com.photolab.bean.admin.cadastro;

import br.com.photolab.dao.modeloDao.ClienteDao;
import br.com.photolab.dao.modeloDao.FichaDao;
import br.com.photolab.modelo.Cliente;
import br.com.photolab.modelo.Ficha;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@javax.faces.bean.ManagedBean
public class CadastrarFichaBean {

    Ficha ficha = new Ficha();
    FichaDao fichaDao = new FichaDao();
    ClienteDao clienteDao = new ClienteDao();
    private int cod_cliente;

    public CadastrarFichaBean() throws Exception {
        inicializarClientes();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    private List<Cliente> clientes = new ArrayList<Cliente>();
    private List<SelectItem> clientesItem = new ArrayList<SelectItem>();

    public List<SelectItem> getClientesItem() {
        return clientesItem;
    }

    public void setClientesItem(List<SelectItem> clientesItem) {
        this.clientesItem = clientesItem;
    }


    public void inicializarClientes() throws Exception {
        System.out.println(" ITEM carregado ");
        clientes = clienteDao.ListarCliente();

        for (int i = 0; i < clientes.size(); i++) {
            clientesItem.add(new SelectItem(clientes.get(i).getCod(),clientes.get(i).getNome()));
            System.out.println("add "+i);
        }

    }

    public String gravarFicha() throws Exception {

        ficha.setCliente(clienteDao.pesquisarCliente(cod_cliente));
        fichaDao.Gravar(ficha);
        FacesContext.getCurrentInstance().addMessage(
        				null,
        				new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso",
        						"Ficha cadastrada!"));
        return "/pages/admin/visualizafichas_index.xhtml";
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

}
