package br.com.login.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.login.Dao.UserDao;
import br.com.login.model.User;

@ManagedBean
@SessionScoped
public class UserBean {

	public UserBean() {

		user = new User();
	}

	private User user;
	@ManagedProperty(value = "#{sessao}")
	private String sessao = new String();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String logar() throws Exception {
		UserDao userDao = new UserDao();

		if (userDao.testarLogin(user)) {
			System.out.print("Encontrado");

			setSessao(user.getApelido());
			messageSucessoLogin();
			return "result.xhtml";
		} else {
			System.out.print("Não encontrado");
			messageErroLogin();
		}
		return null;
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
						"Realizado com sucesso, Seja bem vindo " + sessao));

	}

	public void messageSucessoGravar() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravar",
						"Cadastro realizado com sucesso, Seja bem vindo "
								+ sessao));
		// remover sessão do manage bean selecionado
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("userBean");

	}

	public void messageErroLogin() {
		FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login",
								"Usuário/Senha incorretos, por favor, tente novamente"));
		// remover sessão do manage bean selecionado
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("userBean");
	}

	public void messageErroCadastro() {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro",
						"O Cadastro falhou, por favor, tente novamente"));
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = sessao;
	}

}
