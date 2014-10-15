package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.RelatorioDao;
import br.com.login.Dao.RelatorioDiarioDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Relatorio;
import br.com.login.model.RelatorioDiario;
import br.com.login.model.User;
import com.lowagie.text.*;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
    public List<RelatorioDiario> albunsdoGrafico(User user,Date dataInicial,Date dataFinal) throws Exception {

        return relatorioDiarioDao.ListarIntervalo(user,dataInicial,dataFinal);
    }

    public void preProcessPDF(Object document) throws IOException,
            BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.add(new Paragraph(userBean.getUserLogado().getApelido()));
    }


    public LineChartModel createLineModels() throws Exception {

        LineChartModel lineModel1;

        lineModel1= initCategoryModel(userBean.getUserLogado());
        lineModel1.setTitle("Produção");
        lineModel1.setLegendPosition("e");
        lineModel1.setShowPointLabels(true);
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(1500);

        lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Dias"));
        yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Fotos");
        yAxis.setMin(0);
        yAxis.setMax(1500);
        return lineModel1;
    }

    private LineChartModel initCategoryModel(User user) throws Exception {
        LineChartModel model = new LineChartModel();

        ChartSeries userChart = new ChartSeries();
        userChart.setLabel(user.getApelido());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (int x =0; x>= relatorioList.size();x++){

            userChart.set(simpleDateFormat.format(relatorioList.get(x).getDataRelatorio()), relatorioList.get(x).getFotos());
            System.out.println("teste aqui "+simpleDateFormat.format(relatorioList.get(x).getDataRelatorio()) + relatorioList.get(x).getFotos());
            model.addSeries(userChart);
        }



        return model;
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
