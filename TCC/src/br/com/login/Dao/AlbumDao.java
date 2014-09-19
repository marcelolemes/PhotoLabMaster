package br.com.login.Dao;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.util.HibernateUtil;

@ViewScoped
public class AlbumDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean Gravar(Album album) throws Exception {
		Session sessao = HibernateUtil.getSession();
		org.hibernate.Transaction transacao = sessao.beginTransaction();
		album.setStatus(album.getContrato().getStatus());
		sessao.saveOrUpdate(album);
		transacao.commit();
		sessao.close();
		return true;

	}

	public boolean GravarLista(List<Album> albuns) throws Exception {
		Session sessao = HibernateUtil.getSession();
		sessao.beginTransaction();
		System.out.println("Transação iniciada");
		for (Album album : albuns) {
			sessao.save(album);
			System.out.println("Album persistido");
		}
		sessao.getTransaction().commit();
		sessao.close();
		return true;

	}

	public List<Album> ListarAlbuns() throws Exception {
		Session sessao = HibernateUtil.getSession();
		Criteria criteria = sessao.createCriteria(Album.class);
		List<Album> listaRetorno = criteria.list();
		sessao.close();
		return listaRetorno;
	}

	public List<Album> ListarAlbunsContrato(Contrato contrato) throws Exception {
		Session sessao = HibernateUtil.getSession();
		Criteria criteria = sessao.createCriteria(Album.class);
		criteria.add(Restrictions.eq("contrato", contrato.getCod()));
		List<Album> listaRetorno = criteria.list();
		sessao.close();
		return listaRetorno;
	}
}
