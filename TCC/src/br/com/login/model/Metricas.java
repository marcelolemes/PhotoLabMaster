package br.com.login.model;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Metricas implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6233474203192271864L;
	private List<SelectItem> nivelAcesso = new ArrayList<SelectItem>();
	private List<SelectItem> urgenciaLista = new ArrayList<SelectItem>();
	private List<SelectItem> statusContratoLista = new ArrayList<SelectItem>();
	private List<SelectItem> setores = new ArrayList<SelectItem>();
    String[] mes = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro" };

	public Metricas() {

		inicializarNivelAcesso();
		inicializarUrgenciaLista();
		iniciarStatusLista();
		inicializarSetores();
	}

	public void inicializarNivelAcesso() {
        nivelAcesso.add(new SelectItem(0, "Patrão"));
        nivelAcesso.add(new SelectItem(1, "Usuário"));
		nivelAcesso.add(new SelectItem(2, "Gestor"));
		nivelAcesso.add(new SelectItem(3, "Admin"));

	}

	public void inicializarSetores() {
		setores.add(new SelectItem(0, "Cadastro"));
		setores.add(new SelectItem(1, "Correção de cor"));
		setores.add(new SelectItem(2, "Tratamento de pele"));
		setores.add(new SelectItem(3, "Montagem"));
		setores.add(new SelectItem(4, "Impressão"));
		setores.add(new SelectItem(5, "Backup"));

	}

	public void inicializarUrgenciaLista() {
		urgenciaLista.add(new SelectItem(0, "Fazendo"));
		urgenciaLista.add(new SelectItem(1, "Urgentíssimo"));
		urgenciaLista.add(new SelectItem(2, "Muito Urgente"));
		urgenciaLista.add(new SelectItem(3, "Urgente"));
		urgenciaLista.add(new SelectItem(4, "Normal"));
		urgenciaLista.add(new SelectItem(5, "Baixa"));
		urgenciaLista.add(new SelectItem(6, "Manter Parado"));

	}

	public void iniciarStatusLista() {

		statusContratoLista.add(new SelectItem(0, "Cadastrado"));
		statusContratoLista.add(new SelectItem(1, "Renomeado"));
		statusContratoLista.add(new SelectItem(2, "Separando"));
		statusContratoLista.add(new SelectItem(3, "Parado na Cor"));
		statusContratoLista.add(new SelectItem(4, "Em Correção de Cor"));
        statusContratoLista.add(new SelectItem(5, "Cor/Tratamento"));
		statusContratoLista.add(new SelectItem(6, "Pronto Cor"));
		statusContratoLista.add(new SelectItem(7, "Parado tratamento de pele"));
		statusContratoLista.add(new SelectItem(8, "Em tratamento de pele"));
		statusContratoLista
				.add(new SelectItem(9, "Em tratamento Terceirizado"));
		statusContratoLista.add(new SelectItem(10, "Tratamento/Montagem"));
		statusContratoLista.add(new SelectItem(11, "Tratamento Pronto"));
        statusContratoLista.add(new SelectItem(12, "Parado Montagem"));
        statusContratoLista.add(new SelectItem(13, "Em Montagem"));
        statusContratoLista.add(new SelectItem(14, "Pronto Montagem"));
		statusContratoLista.add(new SelectItem(15, "Em impressão"));
		statusContratoLista.add(new SelectItem(16, "Aguardando Backup"));
		statusContratoLista.add(new SelectItem(17, "Em backup"));
		statusContratoLista.add(new SelectItem(18, "Backup Pronto"));
	}

	public List<SelectItem> getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(List<SelectItem> nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public List<SelectItem> getUrgenciaLista() {
		return urgenciaLista;
	}

	public void setUrgenciaLista(List<SelectItem> urgenciaLista) {
		this.urgenciaLista = urgenciaLista;
	}

	public List<SelectItem> getStatusContratoLista() {
		return statusContratoLista;
	}

	public void setStatusContratoLista(List<SelectItem> statusContratoLista) {
		this.statusContratoLista = statusContratoLista;
	}

	public List<SelectItem> getSetores() {
		return setores;
	}

	public void setSetores(List<SelectItem> setores) {
		this.setores = setores;
	}

    public String[] getMes() {
        return mes;
    }

    public void setMes(String[] mes) {
        this.mes = mes;
    }
}
