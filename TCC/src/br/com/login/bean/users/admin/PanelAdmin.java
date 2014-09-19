package br.com.login.bean.users.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.login.Dao.UserDao;
import br.com.login.bean.users.UserBean;

@ManagedBean
@ViewScoped
public class PanelAdmin implements Serializable {

	/**
	 * 
	 */

	UserDao userDao = new UserDao();
	private static final long serialVersionUID = -8922198411434111521L;

	@ManagedProperty("#{userBean}")
	private UserBean userBean;

	@PostConstruct
	public void init() {

	}

	

	public String btVisualizarCursos() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 2) {

				userBean.autoridadeInsuficiente();

				return "/pages/admin/result_index.xhtml";
			} else {

				return "/pages/admin/visualizarcursos_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}

	public String btCadastro() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 4) {

				userBean.autoridadeInsuficiente();
				// return "result.xhtml";
				return "/pages/admin/result_index.xhtml";
			} else {

				return "/pages/admin/cadastro_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}

	public String btCadastrarCursos() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 0) {

				userBean.autoridadeInsuficiente();
				return "/pages/admin/result_index.xhtml";
			} else {

				return "/pages/admin/cadastrarcursos_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}
	
	public String btCadastrarAlbum() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 0) {

				userBean.autoridadeInsuficiente();
				return "/pages/admin/result_index.xhtml";
			} else {

				return "/pages/admin/cadastro_album_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}
	public String btListarAlbuns() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 0) {

				userBean.autoridadeInsuficiente();
				return "/pages/admin/result_index.xhtml";
			} else {

				return "/pages/admin/visualizaralbuns_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}

	public String btListarUsers() {

		if (userBean.getUser().isLogado()) {
			if (userBean.getUserLogado().getNivelAcesso() < 3) {

				userBean.autoridadeInsuficiente();
				// return "result.xhtml";
				return "/pages/admin/result_index.xhtml";
			} else {

				return "/pages/admin/usuarios_cadastrados_index.xhtml";
			}

		} else {
			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";

		}

	}

	public void fecharSessao() {
		// remover sessão do manage bean selecionado
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userBean");
		/*
		 * userBean.setUserLogado(null); userBean.setLogado(false);
		 */
		userBean.setLogado(false);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
	}
	
	
	
	
	

	public String sairSessao() throws Exception {

		if (userBean.getUser().isLogado()) {
			try {
				userBean.setLogado(false);
				
				userDao.gravarTimestamp(userBean.getUserLogado());

				/*
				 * userBean.setUserLogado(null);
				 */
				

				// Sair
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().invalidateSession();

				// remover sessão do manage bean selecionado
				/*
				 * FacesContext.getCurrentInstance().getExternalContext()
				 * .getSessionMap().remove("userBean");
				 */
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Logoff",
								"Sessão encerrada"));

			} catch (Exception ex) {
				// TODO: handle exception
			}

			return "/pages/login_index.xhtml";
		} else {

			userBean.nenhumUsuario();
			return "/pages/login_index.xhtml";
		}

	}
	

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
