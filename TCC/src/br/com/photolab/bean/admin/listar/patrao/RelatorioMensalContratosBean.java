package br.com.photolab.bean.admin.listar.patrao;

import br.com.photolab.bean.usuario.UsuarioBean;
import br.com.photolab.dao.modeloDao.UsuarioDao;
import br.com.photolab.dao.regraDao.RegraPatraoDao;
import br.com.photolab.dao.relatorioDao.RelatorioDiarioDao;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.modelo.Usuario;
import br.com.photolab.modelo.apoio.Mes;
import br.com.photolab.modelo.apoio.Metricas;
import br.com.photolab.relatorio.RelatorioDiario;
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
 */
@ManagedBean
@ViewScoped
public class RelatorioMensalContratosBean
{
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;
    RegraPatraoDao regraPatraoDao = new RegraPatraoDao();
    Calendar calendar;
    Calendar calendar2;
    private long totalFotosMes=0;
    private long totalAlbunsMes=0;
    private List<Contrato> contratosMes = new ArrayList<Contrato>();

    public RelatorioMensalContratosBean() throws Exception {

        iniciar();

    }
    public void iniciar() throws Exception {


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
                calendar.set(Calendar.MONTH,usuarioBean.getMesSelecionado().getNumero());
                calendar.set(Calendar.YEAR,usuarioBean.getAnoSelecionado());

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
                calendar2.set(Calendar.MONTH,usuarioBean.getMesSelecionado().getNumero()+1);
                calendar2.set(Calendar.YEAR,usuarioBean.getAnoSelecionado());


        totalAlbunsMes = regraPatraoDao.ContarAlbumMes(calendar, calendar2);
        totalFotosMes = regraPatraoDao.ContarFotosMes(calendar, calendar2);
        contratosMes = regraPatraoDao.ListarContratoMes(calendar,calendar2);
    }



    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Calendar getCalendar2() {
        return calendar2;
    }

    public void setCalendar2(Calendar calendar2) {
        this.calendar2 = calendar2;
    }

    public long getTotalFotosMes() {
        return totalFotosMes;
    }

    public void setTotalFotosMes(long totalFotosMes) {
        this.totalFotosMes = totalFotosMes;
    }

    public long getTotalAlbunsMes() {
        return totalAlbunsMes;
    }

    public void setTotalAlbunsMes(long totalAlbunsMes) {
        this.totalAlbunsMes = totalAlbunsMes;
    }

    public List<Contrato> getContratosMes() {
        return contratosMes;
    }

    public void setContratosMes(List<Contrato> contratosMes) {
        this.contratosMes = contratosMes;
    }
}
