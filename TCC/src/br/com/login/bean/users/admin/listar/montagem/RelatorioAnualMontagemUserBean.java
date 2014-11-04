package br.com.login.bean.users.admin.listar.montagem;

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
import java.util.*;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class RelatorioAnualMontagemUserBean
{
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    Metricas metricas = new Metricas();
    private RelatorioDiario relatorioDiario = new RelatorioDiario();
    private RelatorioDiarioDao relatorioDiarioDao = new RelatorioDiarioDao();
    private List<RelatorioDiario> relatorioList;
    private List<RelatorioDiario> relatorioTodosMes;
    private int[] mesesAno= {0,1,2,3,4,5,6,7,8,9,10,11};
    private List<String> nomeMes;
    private List<Calendar> meses= new ArrayList<Calendar>();
    private List<Calendar> mesesApos= new ArrayList<Calendar>();
    UserDao userDao = new UserDao();
    static int qtdMaximo =0;
    Date data;
    Calendar calendar;
    Calendar calendar2;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");

    public RelatorioAnualMontagemUserBean()  {



        try {
            data = new Date();
            nomeMes.add("Janeiro");
            nomeMes.add("Fevereiro");
            nomeMes.add("Março");






            /*for (int x =0;x<12;x++){


                calendar = Calendar.getInstance();
                calendar.clear(Calendar.DAY_OF_YEAR);
                calendar.clear(Calendar.DAY_OF_WEEK);
                calendar.clear(Calendar.DAY_OF_MONTH);
                calendar.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
                calendar.clear(Calendar.HOUR_OF_DAY);
                calendar.clear(Calendar.WEEK_OF_YEAR);
                calendar.clear(Calendar.WEEK_OF_MONTH);
                calendar.clear(Calendar.HOUR);
                calendar.clear(Calendar.AM_PM);
                calendar.clear(Calendar.MINUTE);
                calendar.clear(Calendar.SECOND);
                calendar.clear(Calendar.MILLISECOND);
                calendar.set(Calendar.MONTH,x);

                calendar2 = Calendar.getInstance();
                calendar2.clear(Calendar.DAY_OF_YEAR);
                calendar2.clear(Calendar.DAY_OF_WEEK);
                calendar2.clear(Calendar.DAY_OF_MONTH);
                calendar2.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
                calendar2.clear(Calendar.HOUR_OF_DAY);
                calendar2.clear(Calendar.WEEK_OF_YEAR);
                calendar2.clear(Calendar.WEEK_OF_MONTH);
                calendar2.clear(Calendar.HOUR);
                calendar2.clear(Calendar.AM_PM);
                calendar2.clear(Calendar.MINUTE);
                calendar2.clear(Calendar.SECOND);
                calendar2.clear(Calendar.MILLISECOND);
                calendar2.set(Calendar.MONTH,x+1);

                meses.add(calendar);
                mesesApos.add(calendar2);

}
*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<RelatorioDiario> albunsMes(User user,Calendar calendar1,Calendar calendar2) throws Exception { // fazer classe com nome  e numero do mEs

        return relatorioDiarioDao.ListarMes(user,calendar1,calendar2);
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

    public String parseMes(int mes){

        return nomeMes.get(mes);
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
                System.out.println("teste aqui montagem " + simpleDateFormat.format(relatorioTodosMes.get(x).getDataRelatorio())+" " + relatorioTodosMes.get(x).getFotos() +" " + relatorioTodosMes.get(x).getFuncionario().getApelido());

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


    public RelatorioDiario getRelatorioDiario() {
        return relatorioDiario;
    }

    public void setRelatorioDiario(RelatorioDiario relatorioDiario) {
        this.relatorioDiario = relatorioDiario;
    }

    public List<RelatorioDiario> getRelatorioList() {
        return relatorioList;
    }

    public void setRelatorioList(List<RelatorioDiario> relatorioList) {
        this.relatorioList = relatorioList;
    }

    public List<Calendar> getMeses() {
        return meses;
    }

    public void setMeses(List<Calendar> meses) {
        this.meses = meses;
    }

    public List<Calendar> getMesesApos() {
        return mesesApos;
    }

    public void setMesesApos(List<Calendar> mesesApos) {
        this.mesesApos = mesesApos;
    }

    public int[] getMesesAno() {
        return mesesAno;
    }

    public void setMesesAno(int[] mesesAno) {
        this.mesesAno = mesesAno;
    }
}
