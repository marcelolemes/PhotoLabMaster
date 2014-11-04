package br.com.login.bean.users.admin.listar.tratamento.mensal;

import br.com.login.Dao.RelatorioDiarioDao;
import br.com.login.Dao.UserDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Mes;
import br.com.login.model.Metricas;
import br.com.login.model.RelatorioDiario;
import br.com.login.model.User;
import org.primefaces.model.chart.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 06/10/14.
 * Com algumas pequenas alterações, serve para outros setores tbm, não será feito o reuso, pois não se faz necessário
 */
@ManagedBean
@ViewScoped
public class RelatorioMensalTratamentoBean
{
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    Metricas metricas = new Metricas();
    private RelatorioDiario relatorioDiario = new RelatorioDiario();
    private RelatorioDiarioDao relatorioDiarioDao = new RelatorioDiarioDao();
    private List<RelatorioDiario> relatorioList;
    private List<RelatorioDiario> relatorioTodosMes;
    private List<Calendar> mesesApos= new ArrayList<Calendar>();
    private List<Mes> meses;
    UserDao userDao = new UserDao();
    List<User> usersTratamento = new ArrayList<User>();
    static int qtdMaximo =0;
    //private static int mesSelecionado =0;
    Date data;
    Calendar calendar;
    Calendar calendar2;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");

    public RelatorioMensalTratamentoBean(){
        try {
            usersTratamento = userDao.ListarUsersTratamento();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<RelatorioDiario> albunsMes(User user,int mes,int ano) throws Exception { // fazer classe com nome  e numero do mEs

        calendar = Calendar.getInstance();
        calendar.clear(Calendar.YEAR);
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
        calendar.set(Calendar.MONTH,mes);
        calendar.set(Calendar.YEAR,ano);

        calendar2 = Calendar.getInstance();
        calendar2.clear(Calendar.YEAR);
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
        calendar2.set(Calendar.MONTH,mes+1);
        calendar2.set(Calendar.YEAR,ano);

        return relatorioDiarioDao.ListarMes(user,calendar,calendar2);
    }


    public LineChartModel createLineModels() throws Exception {

        LineChartModel lineModel1;

        lineModel1= initCategoryModel(usersTratamento);
        lineModel1.setTitle("Produção Tratamento do mês: " +userBean.getMesSelecionado().getNome()+" de "+ userBean.getAnoSelecionado());
        lineModel1.setLegendPosition("sw");
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
            relatorioTodosMes = albunsMes(users.get(y),userBean.getMesSelecionado().getNumero(),userBean.getAnoSelecionado());
            userChart = new ChartSeries();
            userChart.setLabel(users.get(y).getApelido());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");

            for (int x = 0; x < relatorioTodosMes.size(); x++) {
                if (relatorioTodosMes.get(x).getFotos() > qtdMaximo) {
                    qtdMaximo = relatorioTodosMes.get(x).getFotos();
                }
                userChart.set(simpleDateFormat.format(relatorioTodosMes.get(x).getDataRelatorio()), relatorioTodosMes.get(x).getFotos());


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

    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }

    public List<Calendar> getMesesApos() {
        return mesesApos;
    }

    public void setMesesApos(List<Calendar> mesesApos) {
        this.mesesApos = mesesApos;
    }


}
