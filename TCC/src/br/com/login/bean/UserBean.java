package br.com.login.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.login.Dao.UserDao;
import br.com.login.model.User;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1652836096542327706L;

	public UserBean() {

		user = new User();
		nomeSessao = new String();
		
	}

	private User user;

	private String nomeSessao;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void logar() throws Exception {
		UserDao userDao = new UserDao();

		if (userDao.testarLogin(user)) {
			System.out.print("Encontrado");
			setNomeSessao(user.getApelido());
			nomeSessao = user.getApelido();
			messageSucessoLogin();

		} else {
			System.out.print("Não encontrado");
			messageErroLogin();
		}
	}

	public void gravar() {
		UserDao userDao = new UserDao();
		try {
			userDao.Gravar(user);
			messageSucessoGravar();
		} catch (Exception ex) {
			messageErroLogin();
		}

	}

	public void messageSucessoLogin() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Login",
						"Realizado com sucesso, Seja bem vindo " + nomeSessao));

	}

	public void messageSucessoGravar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravar",
						"Cadastro realizado com sucesso, Seja bem vindo "
								+ nomeSessao));

	}

	public void messageErroLogin() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login",
								"Usuário/Senha incorretos, por favor, tente novamente"));
	}

	public void messageErroCadastro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro",
						"O Cadastro falhou, por favor, tente novamente"));
	}

	public String getNomeSessao() {
		return nomeSessao;
	}

	public void setNomeSessao(String nomeSessao) {
		this.nomeSessao = nomeSessao;
	}

}
