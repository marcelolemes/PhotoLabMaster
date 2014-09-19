package br.com.login.Dao;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.login.model.Cliente;
import br.com.login.util.HibernateUtil;

@ViewScoped
public class ClienteDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean Gravar(Cliente cliente) throws Exception {
		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		sessao.saveOrUpdate(cliente);
		transacao.commit();
		sessao.close();
		return true;

	}
	public Cliente pesquisarCliente(int cod) throws Exception {
		Session sessao = HibernateUtil.getSession();
		Criteria criteria = sessao.createCriteria(Cliente.class);
		criteria.add(Restrictions.eq("cod", cod));
		Cliente Retorno = (Cliente) criteria.uniqueResult();
		sessao.close();
		return Retorno;

	}
}
