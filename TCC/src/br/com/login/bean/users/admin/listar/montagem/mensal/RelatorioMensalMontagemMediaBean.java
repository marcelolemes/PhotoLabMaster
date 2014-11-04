package br.com.login.bean.users.admin.listar.montagem.mensal;

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
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class RelatorioMensalMontagemMediaBean
{
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    Metricas metricas = new Metricas();
    private RelatorioDiario relatorioDiaro = new RelatorioDiario();
    private RelatorioDiarioDao relatorioDiarioDao = new RelatorioDiarioDao();
    private List<RelatorioDiario> relatorioList;
    List<User> usersMontagem = new ArrayList<User>();
    UserDao userDao = new UserDao();
    static int qtdMaximo =0;
    Date data;
    Calendar calendar;
    Calendar calendar2;
    PieChartModel model;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");

    public RelatorioMensalMontagemMediaBean()  {
        try {
            data = new Date();
            usersMontagem = userDao.ListarUsersMontagem();
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



    public double mediaAlbunsMes(User user) throws Exception {

        return relatorioDiarioDao.ListarMediaMes(user);
    }


    public double mediaAlbunsMes(User user,int mes,int ano) throws Exception {

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

            return relatorioDiarioDao.ListarMediaMes(user,calendar,calendar2);
        }



    public double totalAlbunsMes(User user) throws Exception {

        return relatorioDiarioDao.ListarTotalMes(user);
    }


    public double totalAlbunsMes(User user,int mes, int ano) throws Exception {

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

           return relatorioDiarioDao.ListarTotalMes(user,calendar,calendar2);
       }



    public BarChartModel createBarModels1() throws Exception {

        BarChartModel barChartModel1;
        barChartModel1 = initBarModel1(usersMontagem);
        barChartModel1.setTitle("Participação Media Montagem do mês de "+userBean.getMesSelecionado().getNome()+" de "+ userBean.getAnoSelecionado());
        barChartModel1.setLegendPosition("ws");


        return barChartModel1;
    }
    public PieChartModel createPieModels1() throws Exception {
        PieChartModel pieChartModel1 = new PieChartModel();
        pieChartModel1 = initPieModel1(usersMontagem);
        pieChartModel1.setTitle("Participação Total Montagem do mês de "+userBean.getMesSelecionado().getNome()+" de "+ userBean.getAnoSelecionado());
        pieChartModel1.setLegendPosition("ws");


        return pieChartModel1;
    }




    public void itemSelect(ItemSelectEvent event) {

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Usuário", usersMontagem.get(event.getItemIndex()).getApelido());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void itemSelect2(ItemSelectEvent event) {

         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                 "Usuário", usersMontagem.get(event.getSeriesIndex()).getApelido());
         FacesContext.getCurrentInstance().addMessage(null, msg);
     }


    private BarChartModel initBarModel1(List<User> users) throws Exception {
        double aux=0;
        BarChartModel model = new BarChartModel();
        ChartSeries userChart;

        for (int y = 0; y < users.size(); y++) {
            userChart = new ChartSeries();
            userChart.setLabel(users.get(y).getApelido());

            try {
                aux = mediaAlbunsMes(users.get(y),userBean.getMesSelecionado().getNumero(),userBean.getAnoSelecionado());
            }
            catch (Exception ex)
            {
                aux=0;
            }
            if(qtdMaximo< aux)
            {
                qtdMaximo = (int)aux;
            }

            userChart.set("Mês: " + userBean.getMesSelecionado().getNome(), aux);
            model.addSeries(userChart);
        }


        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Fotos");
        yAxis.setMin(0);
        yAxis.setMax(qtdMaximo + 50);

        return model;
    }

    private PieChartModel initPieModel1(List<User> users) throws Exception {
        model = new PieChartModel();

        for (int y = 0; y < users.size(); y++) {

            model.set(users.get(y).getApelido(), totalAlbunsMes(users.get(y), userBean.getMesSelecionado().getNumero(), userBean.getAnoSelecionado()));

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
