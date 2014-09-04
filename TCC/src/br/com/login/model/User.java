package br.com.login.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@ManagedBean
@RequestScoped
@Table(name = "Usuario")
public class User {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cod;
	@Column
	private String apelido;
	@Column
	private String senha;
	@Column
	private String servicoatual;
	@Column
	private String servicoanterior;
	@Column
	private String ultimoacesso;
	

	public String getUltimoacesso() {
		return ultimoacesso;
	}

	public void setUltimoacesso(String ultimoacesso) {
		this.ultimoacesso = ultimoacesso;
	}

	public String getServicoanterior() {
		return servicoanterior;
	}

	public void setServicoanterior(String servicoanterior) {
		this.servicoanterior = servicoanterior;
	}

	public String getServicoatual() {
		return servicoatual;
	}

	public void setServicoatual(String servicoatual) {
		this.servicoatual = servicoatual;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
