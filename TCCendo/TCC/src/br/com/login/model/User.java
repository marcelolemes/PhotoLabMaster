package br.com.login.model;

<<<<<<< HEAD
=======
import java.io.Serializable;

>>>>>>> origin/conserto
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
<<<<<<< HEAD
public class User {
=======
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5680566631347397096L;
>>>>>>> origin/conserto
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cod;
	@Column
	private String apelido;
	@Column
	private String senha;

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
