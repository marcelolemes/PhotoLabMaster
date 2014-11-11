package br.com.photolab.bean.admin.listar.tratamento;

import br.com.photolab.dao.modeloDao.ContratoDao;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.modelo.apoio.Metricas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListarContratoTratamento implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    ContratoDao contDao = new ContratoDao();
    private Metricas metricas = new Metricas();
    private List<Contrato> listaContratoEmEspera;
    private List<Contrato> listaContratoPronto;
    private List<Contrato> listaContratoFazendo;

    public ListarContratoTratamento() throws Exception {
        contDao = new ContratoDao();
        listaContratoEmEspera = contDao.listarContratosStatus(5, 7,1);
        listaContratoFazendo  = contDao.listarContratosStatus(8);
        listaContratoPronto = contDao.listarContratosStatus(11);
    }


    public void atualizar() throws Exception {
        try {
            listaContratoEmEspera.clear();
            listaContratoPronto.clear();
            contDao = new ContratoDao();
            listaContratoFazendo  = contDao.listarContratosStatus(8);
            listaContratoEmEspera = contDao.listarContratosStatus(5, 7,1);
            listaContratoPronto = contDao.listarContratosStatus(11);
        } catch (Exception ex) {

        }
    }

    public ContratoDao getContDao() {
        return contDao;
    }

    public void setContDao(ContratoDao contDao) {
        this.contDao = contDao;
    }

    public Metricas getMetricas() {
        return metricas;
    }

    public void setMetricas(Metricas metricas) {
        this.metricas = metricas;
    }

    public List<Contrato> getListaContratoEmEspera() {
        return listaContratoEmEspera;
    }

    public void setListaContratoEmEspera(List<Contrato> listaContratoEmEspera) {
        this.listaContratoEmEspera = listaContratoEmEspera;
    }

    public List<Contrato> getListaContratoPronto() {
        return listaContratoPronto;
    }

    public void setListaContratoPronto(List<Contrato> listaContratoPronto) {
        this.listaContratoPronto = listaContratoPronto;
    }

    public List<Contrato> getListaContratoFazendo() {
        return listaContratoFazendo;
    }

    public void setListaContratoFazendo(List<Contrato> listaContratoFazendo) {
        this.listaContratoFazendo = listaContratoFazendo;
    }
}
