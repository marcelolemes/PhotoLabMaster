package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.AlbumDao;
import br.com.login.Dao.ContratoDao;
import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.Metricas;
import com.lowagie.text.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped //arrumar para outro tipo de escopo assim que possivel
public class ListarCursos implements Serializable {

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
    private Contrato contratoSelecionado = new Contrato();
    private static Contrato contratoSelecionado2 = new Contrato();

    public ListarCursos() throws Exception {

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
        pdf.add(new Paragraph("Contratos"));
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

    public static Contrato getContratoSelecionado2() {
        return contratoSelecionado2;
    }

    public static void setContratoSelecionado2(Contrato contratoSelecionado2) {
        ListarCursos.contratoSelecionado2 = contratoSelecionado2;
    }
}
