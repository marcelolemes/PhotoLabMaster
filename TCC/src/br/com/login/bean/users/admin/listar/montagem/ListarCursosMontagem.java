package br.com.login.bean.users.admin.listar.montagem;

import br.com.login.Dao.ContratoDao;
import br.com.login.model.Contrato;
import br.com.login.model.Metricas;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListarCursosMontagem implements Serializable {

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
    private List<Contrato> contratosFiltrados;
    private List<String> urgencias;
    private Contrato contratoSelecionado = new Contrato();
    private static Contrato contratoSelecionado2 = new Contrato();

    public ListarCursosMontagem() throws Exception {
        contDao = new ContratoDao();
        listaContratoFazendo = contDao.listarContratosStatus(13);
        listaContratoEmEspera = contDao.listarContratosStatus(11, 12,0);
        listaContratoPronto = contDao.listarContratosStatus(14);
    }


    public void atualizar() throws Exception {
        try {
            listaContratoEmEspera.clear();
            listaContratoPronto.clear();
            contDao = new ContratoDao();
            listaContratoFazendo = contDao.listarContratosStatus(13);
            listaContratoEmEspera = contDao.listarContratosStatus(11, 12,0);
            listaContratoPronto = contDao.listarContratosStatus(14);
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

    public List<String> getUrgencias() {
        return urgencias;
    }

    public void setUrgencias(List<String> urgencias) {
        this.urgencias = urgencias;
    }

    public Contrato getContratoSelecionado() {
        return contratoSelecionado;
    }

    public void setContratoSelecionado(Contrato contratoSelecionado) {
        this.contratoSelecionado = contratoSelecionado;
    }

    public List<Contrato> getContratosFiltrados() {
        return contratosFiltrados;
    }

    public void setContratosFiltrados(List<Contrato> contratosFiltrados) {
        this.contratosFiltrados = contratosFiltrados;
    }

    public static Contrato getContratoSelecionado2() {
        return contratoSelecionado2;
    }

    public static void setContratoSelecionado2(Contrato contratoSelecionado2) {
        ListarCursosMontagem.contratoSelecionado2 = contratoSelecionado2;
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
