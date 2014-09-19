package br.com.login.Dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.login.model.Album;
import br.com.login.model.Ficha;
import br.com.login.util.HibernateUtil;

@ViewScoped
public class FichaDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean Gravar(Ficha ficha) throws Exception {

		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		sessao.saveOrUpdate(ficha);
		transacao.commit();
		sessao.close();
		return true;
	}

	public List<Ficha> ListarFichas() throws Exception {
		Session sessao = HibernateUtil.getSession();
		Criteria criteria = sessao.createCriteria(Ficha.class);
		List<Ficha> listaRetorno = criteria.list();
		sessao.close();
		return listaRetorno;
	}
}
