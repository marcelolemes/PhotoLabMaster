package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.RelatorioDao;
import br.com.login.Dao.RelatorioDiarioDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Relatorio;
import br.com.login.model.RelatorioDiario;
import br.com.login.model.User;
import com.lowagie.text.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class RelatorioUserBean
{
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private RelatorioDiario relatorioDiaro = new RelatorioDiario();
    private RelatorioDiarioDao relatorioDiarioDao = new RelatorioDiarioDao();
    private List<RelatorioDiario> relatorioList;
    private Date dataInicial;
    private Date dataFinal;


    public RelatorioUserBean() {
        relatorioList = new ArrayList<RelatorioDiario>();
    }

    public void inicializarLista() throws Exception {

        if (!dataInicial.before(new Date())){
            FacesContext.getCurrentInstance().addMessage(
                          null,
                          new FacesMessage(FacesMessage.SEVERITY_WARN, "Relatório",
                                  "a data inicial é inválida, a data inicial deve ser anterior à hoje por exemplo"));
        }
        if (!dataFinal.after(new Date())){
            FacesContext.getCurrentInstance().addMessage(
                                      null,
                                      new FacesMessage(FacesMessage.SEVERITY_WARN, "Relatório",
                                              "a data final é inválida, a data final deve ser posterior à hoje por exemplo"));
                }

        albunsEspaçoTempo(userBean.getUserLogado(),dataInicial,dataFinal);
    }

    public void albunsEspaçoTempo(User user,Date dataInicial,Date dataFinal) throws Exception {

        relatorioList =relatorioDiarioDao.ListarIntervalo(user,dataInicial,dataFinal);
    }
    public void preProcessPDF(Object document) throws IOException,
            BadElementException, DocumentException {
         Document pdf = (Document) document;
         pdf.open();
         pdf.add(new Paragraph(userBean.getUserLogado().getApelido()));



     }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }


    public RelatorioDiario getRelatorioDiaro() {
        return relatorioDiaro;
    }

    public void setRelatorioDiaro(RelatorioDiario relatorioDiaro) {
        this.relatorioDiaro = relatorioDiaro;
    }

    public List<RelatorioDiario> getRelatorioList() {
        return relatorioList;
    }

    public void setRelatorioList(List<RelatorioDiario> relatorioList) {
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
