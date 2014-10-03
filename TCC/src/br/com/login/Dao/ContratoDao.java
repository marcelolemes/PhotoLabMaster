package br.com.login.Dao;

import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.Ficha;
import br.com.login.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
public class ContratoDao implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public boolean Gravar(Contrato contrato) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        sessao.saveOrUpdate(contrato);
        transacao.commit();
        sessao.close();
        return true;

    }

    public boolean Update(Contrato contrato) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        try {
            sessao.update(contrato);
            transacao.commit();
            sessao.close();
            return true;
        } catch (Exception e) {
            sessao.close();
            return false;
        }

    }

    public List<Contrato> listarContratos() throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Contrato.class);
        List<Contrato> listaRetorno = criteria.list();
        sessao.close();
        return listaRetorno;

    }
    public List<Contrato> listarContratosPorFicha(Ficha ficha) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        int status;
        System.out.println("Chegou no hibernate");
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.eq("ficha", ficha));
        List<Contrato> listaRetorno = criteria.list();
        for(int x=0; x < listaRetorno.size();x++){
            Criteria criteria2 = sessao.createCriteria(Album.class).setProjection(Projections.min("status"));
            criteria2.add(Restrictions.ge("contrato",listaRetorno.get(x)));
            status = (Integer)criteria2.uniqueResult();
            listaRetorno.get(x).setStatus(status);

        }
        for(int x=0; x < listaRetorno.size();x++){
            sessao.update(listaRetorno.get(x));
        }
        transacao.commit();
        sessao.close();
        return listaRetorno;

    }

    public Contrato pesquisarContrato(String numeroContrato) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.like("numeroContrato", numeroContrato,
                MatchMode.ANYWHERE));
        Contrato Retorno = (Contrato) criteria.uniqueResult();
        sessao.close();
        return Retorno;

    }

}
