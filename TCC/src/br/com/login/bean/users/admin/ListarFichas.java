package br.com.login.bean.users.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.login.Dao.FichaDao;
import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.Ficha;
import br.com.login.model.Metricas;

@ManagedBean
@ViewScoped
public class ListarFichas implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	FichaDao fichaDao = new FichaDao();
	private List<Ficha> listaFichas;
	Metricas metricas = new Metricas();
	private Ficha fichaSelecionada = new Ficha();
	

	@PostConstruct
	public void inicializarLista() {
		try {

			listaFichas = fichaDao.ListarFichas();
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}

	}

	public String parserUrgencia(int urgencia) {
		return metricas.getUrgenciaLista().get(urgencia).getLabel();
	}

	public String parserStatus(int status) {
		return metricas.getStatusContratoLista().get(status).getLabel();
	}

	

	

	public List<Ficha> getListaFichas() {
		return listaFichas;
	}

	public void setListaFichas(List<Ficha> listaFichas) {
		this.listaFichas = listaFichas;
	}

}
