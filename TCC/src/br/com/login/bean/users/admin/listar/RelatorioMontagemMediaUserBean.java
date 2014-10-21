package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.RelatorioDiarioDao;
import br.com.login.Dao.UserDao;
import br.com.login.bean.users.UserBean;
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
public class RelatorioMontagemMediaUserBean
{
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    private RelatorioDiario relatorioDiaro = new RelatorioDiario();
    private RelatorioDiarioDao relatorioDiarioDao = new RelatorioDiarioDao();
    private List<RelatorioDiario> relatorioList;
    UserDao userDao = new UserDao();
    static int qtdMaximo =0;


    public RelatorioMontagemMediaUserBean()  {
        try {
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

    public double mediaAlbunsMes(User user) throws Exception {

        return relatorioDiarioDao.ListarMediaMes(user);
    }
    public double totalAlbunsMes(User user) throws Exception {

        return relatorioDiarioDao.ListarTotalMes(user);
    }


    public void preProcessPDF(Object document) throws IOException,
            BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.add(new Paragraph(userBean.getUserLogado().getApelido()));
    }


    public BarChartModel createBarModels1() throws Exception {

        BarChartModel barChartModel1;
        barChartModel1 = initBarModel1(userDao.ListarUsersMontagem());
        barChartModel1.setTitle("Participação Media Montagem");
        barChartModel1.setLegendPosition("w");


        return barChartModel1;
    }
    public PieChartModel createPieModels1() throws Exception {

        PieChartModel pieChartModel1;
        pieChartModel1 = initPieModel1(userDao.ListarUsersMontagem());
        pieChartModel1.setTitle("Participação Total Montagem");
        pieChartModel1.setLegendPosition("w");


        return pieChartModel1;
    }

    private BarChartModel initBarModel1(List<User> users) throws Exception {
        Date data = new Date();
        BarChartModel model = new BarChartModel();
        ChartSeries userChart;

        for (int y = 0; y < users.size(); y++) {
            userChart = new ChartSeries();
            userChart.setLabel(users.get(y).getApelido());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");

            if(qtdMaximo< mediaAlbunsMes(users.get(y)))
            {
                qtdMaximo = (int)mediaAlbunsMes(users.get(y));
            }
            userChart.set("Mês "+simpleDateFormat.format(data), mediaAlbunsMes(users.get(y)));
            model.addSeries(userChart);
        }


        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Fotos");
        yAxis.setMin(0);
        yAxis.setMax(qtdMaximo+50);

        return model;
    }

    private PieChartModel initPieModel1(List<User> users) throws Exception {
        PieChartModel model = new PieChartModel();

        for (int y = 0; y < users.size(); y++) {

            model.set(users.get(y).getApelido(), totalAlbunsMes(users.get(y)));

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
