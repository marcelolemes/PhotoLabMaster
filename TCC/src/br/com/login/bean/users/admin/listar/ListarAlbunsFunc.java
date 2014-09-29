package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.AlbumDao;
import br.com.login.Dao.RelatorioDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Contrato;
import br.com.login.model.Relatorio;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListarAlbunsFunc implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    AlbumDao albumDao = new AlbumDao();
    Relatorio relatorio = new Relatorio();
    RelatorioDao relatorioDao = new RelatorioDao();
   private List<Relatorio> relatorioList;
    //    Metricas metricas = new Metricas();
    //  private List<Album> listaAlbuns;
    private Contrato contratoSelecionado = new Contrato();

    @PostConstruct
    public void listarAlbumFeito(){
        try {

           relatorioList = relatorioDao.ListarAlbunsFunc(userBean.getUserLogado());
        }
        catch (Exception e)
        {
            System.out.println("Exceção Relatório");

        }
    }



    public List<Relatorio> getRelatorioList() {
        return relatorioList;
    }

    public void setRelatorioList(List<Relatorio> relatorioList) {
        this.relatorioList = relatorioList;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
