package br.com.login.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class Metricas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6233474203192271864L;
	private static List<SelectItem> nivelAcesso = new ArrayList<SelectItem>();
	private static List<SelectItem> urgenciaLista = new ArrayList<SelectItem>();
	private static List<SelectItem> statusContratoLista = new ArrayList<SelectItem>();

	public Metricas() {

		inicializarNivelAcesso();
		inicializarUrgenciaLista();
		iniciarStatusLista();
	}

	public void inicializarNivelAcesso() {
		nivelAcesso.add(new SelectItem(0, "Patrão"));
		nivelAcesso.add(new SelectItem(1, "Usuário Júnior"));
		nivelAcesso.add(new SelectItem(2, "Usuário Comum"));
		nivelAcesso.add(new SelectItem(3, "Usuário Avançado"));
		nivelAcesso.add(new SelectItem(4, "Gestor"));
		nivelAcesso.add(new SelectItem(5, "SysAdmin"));
	}

	public void inicializarUrgenciaLista() {
		urgenciaLista.add(new SelectItem(0, "Urgentíssimo"));
		urgenciaLista.add(new SelectItem(1, "Muito Urgente"));
		urgenciaLista.add(new SelectItem(2, "Urgente"));
		urgenciaLista.add(new SelectItem(3, "Normal"));
		urgenciaLista.add(new SelectItem(4, "Baixa"));
		urgenciaLista.add(new SelectItem(5, "Manter Parado"));

	}

	public void iniciarStatusLista() {

		statusContratoLista.add(new SelectItem(0, "Cadastrado"));
		statusContratoLista.add(new SelectItem(1, "Renomeado"));
		statusContratoLista.add(new SelectItem(2, "Separando"));
		statusContratoLista.add(new SelectItem(3, "Parado na Cor"));
		statusContratoLista.add(new SelectItem(4, "Em Correção de Cor"));
		statusContratoLista.add(new SelectItem(5, "Pronto Cor"));
		statusContratoLista.add(new SelectItem(6, "Cor/Tratamento"));
		statusContratoLista.add(new SelectItem(7, "Parado tratamento de pele"));
		statusContratoLista.add(new SelectItem(8, "Em tratamento de pele"));
		statusContratoLista.add(new SelectItem(9, "Em tratamento Terceirizado"));
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
		Metricas.nivelAcesso = nivelAcesso;
	}

	public List<SelectItem> getUrgenciaLista() {
		return urgenciaLista;
	}

	public void setUrgenciaLista(List<SelectItem> urgenciaLista) {
		Metricas.urgenciaLista = urgenciaLista;
	}

	public List<SelectItem> getStatusContratoLista() {
		return statusContratoLista;
	}

	public void setStatusContratoLista(List<SelectItem> statusContratoLista) {
		Metricas.statusContratoLista = statusContratoLista;
	}

}
