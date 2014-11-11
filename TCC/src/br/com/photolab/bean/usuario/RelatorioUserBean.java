package br.com.photolab.bean.usuario;

import br.com.photolab.dao.relatorioDao.RelatorioDiarioDao;
import br.com.photolab.relatorio.RelatorioDiario;
import br.com.photolab.modelo.Usuario;
import com.lowagie.text.*;
import org.primefaces.model.chart.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class RelatorioUserBean
{
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;
    private RelatorioDiario relatorioDiaro = new RelatorioDiario();
    private RelatorioDiarioDao relatorioDiarioDao = new RelatorioDiarioDao();
    private List<RelatorioDiario> relatorioList;
    private List<RelatorioDiario> relatorioTodosMes;
    static int qtdMaximo =0;


    public RelatorioUserBean()  {
        try {
            relatorioList = albunsMes(usuarioBean.getUsuarioLogado());
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

    public void preProcessPDF(Object document) throws IOException,
            BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.add(new Paragraph(usuarioBean.getUsuarioLogado().getApelido()));
    }


    public LineChartModel createLineModels() throws Exception {

        LineChartModel lineModel1;

        lineModel1= initCategoryModel(usuarioBean.getUsuarioLogado());
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

    private LineChartModel initCategoryModel(Usuario usuario) throws Exception {
        LineChartModel model = new LineChartModel();
        relatorioTodosMes = albunsMes(usuario);
        ChartSeries userChart = new ChartSeries();
         userChart.setLabel(usuario.getApelido());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");

        for (int x =0; x < relatorioTodosMes.size();x++){
            if (relatorioTodosMes.get(x).getFotos()>qtdMaximo){
                qtdMaximo = relatorioTodosMes.get(x).getFotos();
            }
            userChart.set(simpleDateFormat.format(relatorioTodosMes.get(x).getDataRelatorio()), relatorioTodosMes.get(x).getFotos());
            System.out.println("teste aqui  "+simpleDateFormat.format(relatorioTodosMes.get(x).getDataRelatorio()) + relatorioTodosMes.get(x).getFotos());

        }
        
        model.addSeries(userChart);


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
