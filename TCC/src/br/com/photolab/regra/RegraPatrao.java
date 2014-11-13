package br.com.photolab.regra;

import br.com.photolab.bean.usuario.UsuarioBean;
import br.com.photolab.dao.modeloDao.AlbumDao;
import br.com.photolab.dao.modeloDao.ContratoDao;
import br.com.photolab.dao.modeloDao.UsuarioDao;
import br.com.photolab.dao.regraDao.RegraPatraoDao;
import br.com.photolab.dao.regraDao.RegraTratamentoDao;
import br.com.photolab.dao.relatorioDao.RelatorioDao;
import br.com.photolab.dao.relatorioDao.RelatorioDiarioDao;
import br.com.photolab.modelo.Album;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.relatorio.Relatorio;
import br.com.photolab.relatorio.RelatorioDiario;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 26/09/2014.
 */
@ManagedBean
@ViewScoped
public class RegraPatrao implements Serializable {

    private long totalFotosMes;
    private long totalAlbunsMes;
    List<Contrato> contratosMes = new ArrayList<Contrato>();
    RegraPatraoDao regraPatraoDao = new RegraPatraoDao();
    public RegraPatrao() throws Exception {

        totalAlbunsMes = regraPatraoDao.ContarAlbumMes();
        totalFotosMes = regraPatraoDao.ContarFotosMes();
        contratosMes = regraPatraoDao.ListarContratoMes();

        try {


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exceção albuns restantes");
        }

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
