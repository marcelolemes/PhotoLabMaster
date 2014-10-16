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
    private List<RelatorioDiario> relatorioTodosMes;
    static int qtdMaximo =0;


    public RelatorioUserBean()  {
        try {
            relatorioList = albunsMes(userBean.getUserLogado());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<RelatorioDiario> albunsMes() throws Exception {

        return relatorioDiarioDao.ListarMes(userBean.getUserLogado());
    }

    public List<RelatorioDiario> albunsMes(User user) throws Exception {

        return relatorioDiarioDao.ListarMes(user);
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
        lineModel1.setAnimate(true);
        lineModel1.setZoom(true);
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(qtdMaximo+100);

        lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Dias"));
        yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Fotos");
        yAxis.setMin(0);
        yAxis.setMax(qtdMaximo+100);
        return lineModel1;
    }

    private LineChartModel initCategoryModel(User user) throws Exception {
        LineChartModel model = new LineChartModel();
        relatorioTodosMes = albunsMes(user);
        ChartSeries userChart = new ChartSeries();
         userChart.setLabel(user.getApelido());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");

        for (int x =1; x < relatorioTodosMes.size();x++){
            if (relatorioTodosMes.get(x).getFotos()>qtdMaximo){
                qtdMaximo = relatorioTodosMes.get(x).getFotos();
            }
            userChart.set(simpleDateFormat.format(relatorioTodosMes.get(x).getDataRelatorio()), relatorioTodosMes.get(x).getFotos());
            System.out.println("teste aqui  "+simpleDateFormat.format(relatorioTodosMes.get(x).getDataRelatorio()) + relatorioTodosMes.get(x).getFotos());

        }
        model.addSeries(userChart);


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



}
