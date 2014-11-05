package br.com.photolab.bean.usuarios.admin.listar.tratamento.mensal;

import br.com.photolab.dao.relatorio.RelatorioDiarioDao;
import br.com.photolab.dao.modelo.UsuarioDao;
import br.com.photolab.bean.usuarios.usuario.UsuarioBean;
import br.com.photolab.modelo.apoio.Metricas;
import br.com.photolab.relatorio.RelatorioDiario;
import br.com.photolab.modelo.Usuario;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
public class RelatorioMensalTratamentoMediaBean
{
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;
    Metricas metricas = new Metricas();
    private RelatorioDiario relatorioDiaro = new RelatorioDiario();
    private RelatorioDiarioDao relatorioDiarioDao = new RelatorioDiarioDao();
    private List<RelatorioDiario> relatorioList;
    List<Usuario> usersTratamento = new ArrayList<Usuario>();
    UsuarioDao usuarioDao = new UsuarioDao();
    static int qtdMaximo =0;
    Date data;
    Calendar calendar;
    Calendar calendar2;
    PieChartModel model;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");

    public RelatorioMensalTratamentoMediaBean()  {
        try {
            usersTratamento = usuarioDao.ListarUsersTratamento();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<RelatorioDiario> albunsMes() throws Exception {

        return relatorioDiarioDao.ListarMes(usuarioBean.getUsuarioLogado());
    }

    public List<RelatorioDiario> albunsMes(Usuario usuario) throws Exception {

        return relatorioDiarioDao.ListarMes(usuario);
    }

    public List<RelatorioDiario> albunsMes(Usuario usuario,int mes,int ano) throws Exception { // fazer classe com nome  e numero do mEs

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

        return relatorioDiarioDao.ListarMes(usuario,calendar,calendar2);
    }



    public double mediaAlbunsMes(Usuario usuario) throws Exception {

        return relatorioDiarioDao.ListarMediaMes(usuario);
    }


    public double mediaAlbunsMes(Usuario usuario,int mes,int ano) throws Exception {

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

            return relatorioDiarioDao.ListarMediaMes(usuario,calendar,calendar2);
        }



    public double totalAlbunsMes(Usuario usuario) throws Exception {

        return relatorioDiarioDao.ListarTotalMes(usuario);
    }


    public double totalAlbunsMes(Usuario usuario,int mes, int ano) throws Exception {

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

           return relatorioDiarioDao.ListarTotalMes(usuario,calendar,calendar2);
       }



    public BarChartModel createBarModels1() throws Exception {

        BarChartModel barChartModel1;
        barChartModel1 = initBarModel1(usersTratamento);
        barChartModel1.setTitle("Participação Media Montagem do mês de "+ usuarioBean.getMesSelecionado().getNome()+" de "+ usuarioBean.getAnoSelecionado());
        barChartModel1.setLegendPosition("ws");


        return barChartModel1;
    }
    public PieChartModel createPieModels1() throws Exception {
        PieChartModel pieChartModel1 = new PieChartModel();
        pieChartModel1 = initPieModel1(usersTratamento);
        pieChartModel1.setTitle("Participação Total Tratamento do mês de "+ usuarioBean.getMesSelecionado().getNome()+" de "+ usuarioBean.getAnoSelecionado());
        pieChartModel1.setLegendPosition("ws");


        return pieChartModel1;
    }


    public void itemSelect(ItemSelectEvent event) {

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Usuário", usersTratamento.get(event.getItemIndex()).getApelido());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void itemSelect2(ItemSelectEvent event) {

         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                 "Usuário", usersTratamento.get(event.getSeriesIndex()).getApelido());
         FacesContext.getCurrentInstance().addMessage(null, msg);
     }

    private BarChartModel initBarModel1(List<Usuario> usuarios) throws Exception {
        double aux=0;
        BarChartModel model = new BarChartModel();
        ChartSeries userChart;

        for (int y = 0; y < usuarios.size(); y++) {
            userChart = new ChartSeries();
            userChart.setLabel(usuarios.get(y).getApelido());

            try {
                aux = mediaAlbunsMes(usuarios.get(y), usuarioBean.getMesSelecionado().getNumero(), usuarioBean.getAnoSelecionado());
            }
            catch (Exception ex)
            {
                aux=0;
            }
            if(qtdMaximo< aux)
            {
                qtdMaximo = (int)aux;
            }

            userChart.set("Mês: " + usuarioBean.getMesSelecionado().getNome(), aux);
            model.addSeries(userChart);
        }


        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Fotos");
        yAxis.setMin(0);
        yAxis.setMax(qtdMaximo + 50);

        return model;
    }

    private PieChartModel initPieModel1(List<Usuario> usuarios) throws Exception {
        model = new PieChartModel();

        for (int y = 0; y < usuarios.size(); y++) {

            model.set(usuarios.get(y).getApelido(), totalAlbunsMes(usuarios.get(y), usuarioBean.getMesSelecionado().getNumero(), usuarioBean.getAnoSelecionado()));

        }

        return model;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
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
