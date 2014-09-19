package br.com.login.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@ManagedBean
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8060330139043854512L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cod;
	@Column
	private String nome;
	@Column
	private String obs;
	@Column
	@OneToMany(mappedBy = "cliente")
	private List<Ficha> listaFichas;

	public int getCod() {
		return cod;
	}

	public String getNome() {
		return nome;
	}

	public String getObs() {
		return obs;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

}
