package br.com.login.bean.users.admin;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.login.Dao.ContratoDao;
import br.com.login.model.Contrato;
import br.com.login.model.Metricas;

@ManagedBean
@SessionScoped
public class EditarCursos implements Serializable {
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
	private Contrato contratoSelecionado;

	public EditarCursos() throws Exception {

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