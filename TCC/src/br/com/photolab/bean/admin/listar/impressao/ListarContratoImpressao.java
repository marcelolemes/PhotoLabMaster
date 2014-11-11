package br.com.photolab.bean.admin.listar.impressao;

import br.com.photolab.dao.modelo.ContratoDao;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.modelo.apoio.Metricas;
import com.lowagie.text.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListarContratoImpressao implements Serializable {

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

    public ListarContratoImpressao() throws Exception {
        contDao = new ContratoDao();
        listaContratoFazendo = contDao.listarContratosStatus(15);
        listaContratoEmEspera = contDao.listarContratosStatus(14);
        listaContratoPronto = contDao.listarContratosStatus(16);
    }


    public void atualizar() throws Exception {
        try {
            listaContratoEmEspera.clear();
            listaContratoPronto.clear();
            contDao = new ContratoDao();
            listaContratoFazendo = contDao.listarContratosStatus(15);
            listaContratoEmEspera = contDao.listarContratosStatus(14);
            listaContratoPronto = contDao.listarContratosImpressos(16);
        } catch (Exception ex) {

        }
    }


    public void preProcessPDF(Object document) throws IOException,
            BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.add(new Paragraph("Setor impressão:  "));
        pdf.add(new Paragraph(" "));
        pdf.setPageSize(PageSize.A4.rotate());


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


    public List<Contrato> getContratosFiltrados() {
        return contratosFiltrados;
    }

    public void setContratosFiltrados(List<Contrato> contratosFiltrados) {
        this.contratosFiltrados = contratosFiltrados;
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
