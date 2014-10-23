package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.RelatorioDiarioDao;
import br.com.login.Dao.UserDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Metricas;
import br.com.login.model.RelatorioDiario;
import br.com.login.model.User;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import org.primefaces.model.chart.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class RelatorioMontagemUserBean
{
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    Metricas metricas = new Metricas();
    private RelatorioDiario relatorioDiaro = new RelatorioDiario();
    private RelatorioDiarioDao relatorioDiarioDao = new RelatorioDiarioDao();
    private List<RelatorioDiario> relatorioList;
    private List<RelatorioDiario> relatorioTodosMes;
    UserDao userDao = new UserDao();
    static int qtdMaximo =0;
    Date data;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");

    public RelatorioMontagemUserBean()  {
        try {
            data = new Date();
            //relatorioList = albunsMes(userBean.getUserLogado());
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

        lineModel1= initCategoryModel(userDao.ListarUsersMontagem());
        lineModel1.setTitle("Produção Montagem do mês: "+metricas.getMes()[Integer.parseInt(simpleDateFormat.format(data))-1]);
        lineModel1.setLegendPosition("w");
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

    private LineChartModel initCategoryModel(List<User> users) throws Exception {
        LineChartModel model = new LineChartModel();
        ChartSeries userChart;
        userChart = new ChartSeries();
        userChart.setLabel("Legenda funcionários");

        for (int x=1; x <= 31; x++){ //Estou adicionando os dias do mês "manualmente"
            if (x>=10){
            userChart.set(String.valueOf(x),null);
            }
            else {
                userChart.set("0"+String.valueOf(x),null);
            }


        }
        model.addSeries(userChart);

        for (int y = 0; y < users.size(); y++) {
            relatorioTodosMes = albunsMes(users.get(y));
            userChart = null;
            userChart = new ChartSeries();
            userChart.setLabel(users.get(y).getApelido());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");

            for (int x = 0; x < relatorioTodosMes.size(); x++) {
                if (relatorioTodosMes.get(x).getFotos() > qtdMaximo) {
                    qtdMaximo = relatorioTodosMes.get(x).getFotos();
                }
                userChart.set(simpleDateFormat.format(relatorioTodosMes.get(x).getDataRelatorio()), relatorioTodosMes.get(x).getFotos());
                System.out.println("teste aqui  " + simpleDateFormat.format(relatorioTodosMes.get(x).getDataRelatorio())+" " + relatorioTodosMes.get(x).getFotos() +" " + relatorioTodosMes.get(x).getFuncionario().getApelido());

            }
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



}
