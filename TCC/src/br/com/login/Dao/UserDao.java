package br.com.login.Dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.login.model.User;
import br.com.login.util.HibernateUtil;

public class UserDao {

	public boolean testarLogin(User user) throws Exception {
		User resultado;
		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		Criteria criteria = sessao.createCriteria(User.class);
		criteria.add(Restrictions.eq("apelido", user.getApelido()));
		resultado = (User) criteria.uniqueResult();
		if (resultado != null) {
			if (resultado.getSenha() != null) {
				if (resultado.getSenha().equals(user.getSenha())) {
					sessao.close();
					return true;
				} else
					sessao.close();
					return false;

			} else
				sessao.close();
				return false;
		} else
			sessao.close();
			return false;
		

	}

	public void Gravar(User user) throws Exception {
		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		sessao.saveOrUpdate(user);
		transacao.commit();
		sessao.close();
	}
}
