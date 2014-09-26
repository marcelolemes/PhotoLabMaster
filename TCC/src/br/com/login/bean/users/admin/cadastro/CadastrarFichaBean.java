package br.com.login.bean.users.admin.cadastro;

import br.com.login.Dao.ClienteDao;
import br.com.login.Dao.FichaDao;
import br.com.login.model.Ficha;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@javax.faces.bean.ManagedBean
public class CadastrarFichaBean {

    Ficha ficha = new Ficha();
    FichaDao fichaDao = new FichaDao();
    ClienteDao clienteDao = new ClienteDao();
    private int cod_cliente;

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
