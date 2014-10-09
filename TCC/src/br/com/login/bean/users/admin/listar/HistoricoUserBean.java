package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.AlbumDao;
import br.com.login.Dao.RelatorioDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Relatorio;
import br.com.login.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class HistoricoUserBean
{
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private Relatorio relatorio = new Relatorio();
    private RelatorioDao relatorioDao = new RelatorioDao();
    private List<Relatorio> relatorioList;
    private Date dataInicial;
    private Date dataFinal;


    public HistoricoUserBean() {
        relatorioList = new ArrayList<Relatorio>();
    }

    public void inicializarLista() throws Exception {

        albunsEspaçoTempo(userBean.getUserLogado(),dataInicial,dataFinal);
    }

    public void albunsEspaçoTempo(User user,Date dataInicial,Date dataFinal) throws Exception {

        relatorioList =relatorioDao.ListarIntervalo(user,dataInicial,dataFinal);
    }


    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    public RelatorioDao getRelatorioDao() {
        return relatorioDao;
    }

    public void setRelatorioDao(RelatorioDao relatorioDao) {
        this.relatorioDao = relatorioDao;
    }

    public List<Relatorio> getRelatorioList() {
        return relatorioList;
    }

    public void setRelatorioList(List<Relatorio> relatorioList) {
        this.relatorioList = relatorioList;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }
}
