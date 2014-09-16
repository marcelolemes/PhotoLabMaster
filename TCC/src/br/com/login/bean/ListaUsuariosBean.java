package br.com.login.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.login.Dao.UserDao;
import br.com.login.model.User;

@ManagedBean
@ViewScoped
public class ListaUsuariosBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6208235532882903687L;
	UserDao userDao = new UserDao();
	private List<User> listaUsers;

	@PostConstruct
	public void inicializarLista() {
		try {
			
			listaUsers = userDao.ListarUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<User> getListaUsers() {
		return listaUsers;
	}

	public void setListaUsers(List<User> listaUsers) {
		this.listaUsers = listaUsers;
	}

}
