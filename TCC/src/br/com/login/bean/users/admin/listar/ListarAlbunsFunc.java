package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.AlbumDao;
import br.com.login.Dao.RegraMontagemDao;
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

    private long qtdDia;
    private long qtdDiaFotos;

    Relatorio relatorio = new Relatorio();

    RelatorioDao relatorioDao = new RelatorioDao();
    private List<Relatorio> relatorioList;

    private Contrato contratoSelecionado = new Contrato();

    @PostConstruct
    public void listarAlbumFeito(){
        try {

            // relatorioList = relatorioDao.ListarAlbunsFunc(userBean.getUserLogado());
            relatorioList = relatorioDao.ListarAlbunsHoje(userBean.getUserLogado());
            qtdDia = relatorioDao.contarAlbunsHoje(userBean.getUserLogado());
            qtdDiaFotos = relatorioDao.contarFotosHoje(userBean.getUserLogado());
        }
        catch (Exception e)
        {
            System.out.println("Exceção Relatório");
            e.printStackTrace();
            e.getMessage();

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

    public long getQtdDia() {
        return qtdDia;
    }

    public void setQtdDia(long qtdDia) {
        this.qtdDia = qtdDia;
    }

    public long getQtdDiaFotos() {
        return qtdDiaFotos;
    }

    public void setQtdDiaFotos(long qtdDiaFotos) {
        this.qtdDiaFotos = qtdDiaFotos;
    }

    public RelatorioDao getRelatorioDao() {
        return relatorioDao;
    }

    public void setRelatorioDao(RelatorioDao relatorioDao) {
        this.relatorioDao = relatorioDao;
    }
}
