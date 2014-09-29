package br.com.login.Dao;

import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.User;
import br.com.login.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

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

    public boolean GravarLista(List<Album> albuns,Contrato contrato) throws Exception {
        Session sessao = HibernateUtil.getSession();
        sessao.beginTransaction();
        System.out.println("Transaçãoo iniciada");
        for (Album album : albuns) {
            sessao.save(album);
            System.out.println("Album persistido");
        }
        contrato.setQtdAlbum(albuns.size());
        try {
            sessao.update(contrato);
        }
        catch (Exception ex)
        {

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

    public List<Album> ListarAlbunsFuncTratamento(User user) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Album.class);
        criteria.add(Restrictions.eq("userTratamento", user));
        List<Album> listaRetorno = criteria.list();
        sessao.close();
        return listaRetorno;
    }
    public List<Album> ListarAlbunsFuncMontagem(User user) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Album.class);
        criteria.add(Restrictions.eq("userMontagem", user));
        List<Album> listaRetorno = criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public List<Album> ListarAlbunsContrato(Contrato contrato) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Album.class);
        criteria.add(Restrictions.eq("contrato", contrato));
        System.out.println("Contrato aqui: "+contrato.getNumeroContrato());
        List<Album> listaRetorno = criteria.list();
        sessao.close();
        return listaRetorno;
    }
}
