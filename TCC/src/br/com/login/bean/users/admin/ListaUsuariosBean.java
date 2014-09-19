package br.com.login.bean.users.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.login.Dao.UserDao;
import br.com.login.model.Metricas;
import br.com.login.model.User;

@ManagedBean
@ViewScoped
public class ListaUsuariosBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6208235532882903687L;
	UserDao userDao = new UserDao();
	private List<User> listaUsers;
	Metricas metricas = new Metricas();

	@PostConstruct
	public void inicializarLista() {
		try {

			listaUsers = userDao.ListarUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String parserNivel(User user) {
		return metricas.getNivelAcesso().get(user.getNivelAcesso())
				.getLabel();
	}
	public String parserSetor(User user) {
		try {
			return metricas.getSetores().get(user.getSetor()).getLabel();
		} catch (Exception e) {
			System.out.println("Não deu");
			return null;
		}

	}


	public List<User> getListaUsers() {
		return listaUsers;
	}

	public void setListaUsers(List<User> listaUsers) {
		this.listaUsers = listaUsers;
	}

}
