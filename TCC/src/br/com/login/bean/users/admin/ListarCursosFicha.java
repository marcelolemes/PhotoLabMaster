package br.com.login.bean.users.admin;

import br.com.login.Dao.ContratoDao;
import br.com.login.model.Contrato;
import br.com.login.model.Ficha;
import br.com.login.model.Metricas;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class ListarCursosFicha implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    ContratoDao contDao = new ContratoDao();
    private Metricas metricas = new Metricas();
    private List<Contrato> listaContrato;
    private List<Contrato> contratosFiltrados;
    private List<String> urgencias;
    private Ficha fichaSelecionada = new Ficha();


    public Ficha getFichaSelecionada() {
        return fichaSelecionada;
    }

    public void setFichaSelecionada(Ficha fichaSelecionada) {
        this.fichaSelecionada = fichaSelecionada;
    }


    private Contrato contratoSelecionado;

    public ListarCursosFicha() throws Exception {

        listaContrato = contDao.listarContratos();

    }



    public String btUpdateCursos() {
        return "/pages/conteudo/editarcursos_index.xhtml";
    }


    public String atualizar() throws Exception {
        try {
            listaContrato.clear();
            contDao.listarContratos().clear();
        } catch (Exception ex) {
        }
        listaContrato = contDao.listarContratos();

        return "/pages/conteudo/visualizarcursos_index.xhtml";
    }


    public String parserStatus(Contrato contrato) {
        return metricas.getStatusContratoLista().get(contrato.getStatus())
                .getLabel();
    }

    public String parserUrgencia(Contrato contrato) {
        return metricas.getUrgenciaLista().get(contrato.getUrgencia())
                .getLabel();
    }

    public String updateContrato(Contrato contrato) throws Exception {
        if (contDao.Update(contrato)) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Update",
                            "Gravado com sucesso"));
            return "/pages/visualizarcursos_index.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Update",
                            "Falha ao gravar!!!"));
            return "/pages/visualizarcursos_index.xhtml";
        }

    }

    public void preProcessPDF(Object document) throws IOException,
            BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

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

    public List<Contrato> getListaContrato() {
        return listaContrato;
    }

    public void setListaContrato(List<Contrato> listaContrato) {
        this.listaContrato = listaContrato;
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

}
