package br.com.photolab.bean.usuarios.usuario.montagem;

import br.com.photolab.dao.relatorio.RelatorioDiarioDao;
import br.com.photolab.dao.modelo.UsuarioDao;
import br.com.photolab.bean.usuarios.usuario.UsuarioBean;
import br.com.photolab.modelo.apoio.Metricas;
import br.com.photolab.relatorio.RelatorioDiario;
import br.com.photolab.modelo.Usuario;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class RelatorioMontagemUserBean
{
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;
    Metricas metricas = new Metricas();
    private RelatorioDiario relatorioDiaro = new RelatorioDiario();
    private RelatorioDiarioDao relatorioDiarioDao = new RelatorioDiarioDao();
    private List<RelatorioDiario> relatorioList;
    private List<RelatorioDiario> relatorioTodosMes;
    UsuarioDao usuarioDao = new UsuarioDao();
    static int qtdMaximo =0;
    Date data;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");

    public RelatorioMontagemUserBean()  {
        try {
            data = new Date();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<RelatorioDiario> albunsMes(Usuario usuario,Calendar calendar1, Calendar calendar2) throws Exception {

        return relatorioDiarioDao.ListarMes(usuario,calendar1, calendar2);
    }

    public List<RelatorioDiario> albunsMes(Usuario usuario) throws Exception {

        return relatorioDiarioDao.ListarMes(usuario);
    }

    public void preProcessPDF(Object document) throws IOException,
            BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.add(new Paragraph(usuarioBean.getUsuarioLogado().getApelido()));
    }


    public LineChartModel createLineModels() throws Exception {

        LineChartModel lineModel1;

        lineModel1= initCategoryModel(usuarioDao.ListarUsersMontagem());
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

    private LineChartModel initCategoryModel(List<Usuario> usuarios) throws Exception {
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

        for (int y = 0; y < usuarios.size(); y++) {
            relatorioTodosMes = albunsMes(usuarios.get(y));
            userChart = null;
            userChart = new ChartSeries();
            userChart.setLabel(usuarios.get(y).getApelido());
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
