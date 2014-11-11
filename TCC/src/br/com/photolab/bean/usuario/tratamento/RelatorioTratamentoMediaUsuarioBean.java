package br.com.photolab.bean.usuario.tratamento;

import br.com.photolab.dao.relatorioDao.RelatorioDiarioDao;
import br.com.photolab.dao.modeloDao.UsuarioDao;
import br.com.photolab.bean.usuario.UsuarioBean;
import br.com.photolab.modelo.apoio.Metricas;
import br.com.photolab.relatorio.RelatorioDiario;
import br.com.photolab.modelo.Usuario;
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
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class RelatorioTratamentoMediaUsuarioBean
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

    PieChartModel model;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");

    public RelatorioTratamentoMediaUsuarioBean()  {
        try {
            data = new Date();
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

    public double mediaAlbunsMes(Usuario usuario) throws Exception {

        return relatorioDiarioDao.ListarMediaMes(usuario);
    }
    public double totalAlbunsMes(Usuario usuario) throws Exception {

        return relatorioDiarioDao.ListarTotalMes(usuario);
    }


    public void preProcessPDF(Object document) throws IOException,
            BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.add(new Paragraph(usuarioBean.getUsuarioLogado().getApelido()));
    }


    public BarChartModel createBarModels1() throws Exception {

        BarChartModel barChartModel1;
        barChartModel1 = initBarModel1(usersTratamento);
        barChartModel1.setTitle("Participação Media Tratamento do mês de "+metricas.getMes()[Integer.parseInt(simpleDateFormat.format(data))-1]);
        barChartModel1.setLegendPosition("w");


        return barChartModel1;
    }
    public PieChartModel createPieModels1() throws Exception {
        PieChartModel pieChartModel1 = new PieChartModel();
        pieChartModel1 = initPieModel1(usersTratamento);
        pieChartModel1.setTitle("Participação Total Tratamento do mês de "+metricas.getMes()[Integer.parseInt(simpleDateFormat.format(data))-1]);
        pieChartModel1.setLegendPosition("w");


        return pieChartModel1;
    }


    public void itemSelect(ItemSelectEvent event) {

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Usuário", usersTratamento.get(event.getItemIndex()).getApelido());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    private BarChartModel initBarModel1(List<Usuario> usuarios) throws Exception {

        BarChartModel model = new BarChartModel();
        ChartSeries userChart;

        for (int y = 0; y < usuarios.size(); y++) {
            userChart = new ChartSeries();
            userChart.setLabel(usuarios.get(y).getApelido());


            if(qtdMaximo< mediaAlbunsMes(usuarios.get(y)))
            {
                qtdMaximo = (int)mediaAlbunsMes(usuarios.get(y));
            }

            userChart.set("Mês: "+metricas.getMes()[Integer.parseInt(simpleDateFormat.format(data))-1], mediaAlbunsMes(usuarios.get(y)));
            model.addSeries(userChart);
        }


        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Fotos");
        yAxis.setMin(0);
        yAxis.setMax(qtdMaximo+50);

        return model;
    }

    private PieChartModel initPieModel1(List<Usuario> usuarios) throws Exception {
        model = new PieChartModel();

        for (int y = 0; y < usuarios.size(); y++) {

            model.set(usuarios.get(y).getApelido(), totalAlbunsMes(usuarios.get(y)));

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
