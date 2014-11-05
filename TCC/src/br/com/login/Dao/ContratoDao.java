package br.com.login.Dao;

import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.Ficha;
import br.com.login.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
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

    public List<Contrato> listarContratosStatus(int min, int max,int order) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.ge("status", min)).add(Restrictions.le("status", max));
        criteria.addOrder(Order.desc("ocupado")); // ordenar os ocupados primeiro
        criteria.addOrder(Order.asc("urgencia")); // ordenar por urgencia
        if (order==0){
            criteria.addOrder(Order.desc("status"));
        }
        else {
            criteria.addOrder(Order.asc("status"));
        }

        List<Contrato> listaRetorno = criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public Contrato listarContratoStatus(int min, int max,int order) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.ge("status", min)).add(Restrictions.le("status", max));
        criteria.addOrder(Order.desc("ocupado")); // ordenar os ocupados primeiro
        criteria.addOrder(Order.asc("urgencia")); // ordenar por urgencia
        if (order==0){
            criteria.addOrder(Order.desc("status"));
        }
        else {
            criteria.addOrder(Order.asc("status"));
        }
        criteria.setMaxResults(1);
        Contrato retorno = (Contrato) criteria.uniqueResult();
        sessao.close();
        System.out.println("Retorno contrato "+retorno.getNumeroContrato());
        return retorno;
    }

    public List<Contrato> listarContratosStatus(int unique) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.eq("status", unique));
        criteria.addOrder(Order.asc("urgencia"));
        List<Contrato> listaRetorno = criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public Contrato atualizarContratoRetorna(Contrato retorno) throws Exception {
        int status;
        int statusMax;
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        Criteria criteria2 = sessao.createCriteria(Album.class).setProjection(Projections.min("status"));
        Criteria criteria3 = sessao.createCriteria(Album.class).setProjection(Projections.max("status"));
        criteria2.add(Restrictions.eq("contrato",retorno));
        criteria3.add(Restrictions.eq("contrato",retorno));
        try {
            status = (Integer)criteria2.uniqueResult();
            statusMax = (Integer)criteria3.uniqueResult();
            if(status>=0){
                if(status<=18){

                    switch (statusMax){
                        case 8:
                            retorno.setStatus(statusMax);
                            break;
                        case 11:
                            if(status != statusMax) {  // caso o st
                                retorno.setStatus(statusMax - 3);
                            }
                            else {
                                retorno.setStatus(statusMax);
                                retorno.setUrgencia(4);
                                retorno.setOcupado(false);

                            }
                            break;
                        case 13:
                            if(status != statusMax) {
                                if(status>=6 & status <=10) {
                                    retorno.setStatus(10);
                                }
                                else { //alteração 31-10
                                    retorno.setStatus(statusMax);
                                }
                            }
                            else {
                                retorno.setStatus(statusMax);
                            }
                            break;
                        case 14:
                            if(status != statusMax) {
                                if(status>=6 & status <=10) {
                                    retorno.setStatus(10);
                                }
                                else {
                                    retorno.setStatus(statusMax - 1);
                                }
                            }
                            else {
                                retorno.setStatus(statusMax);
                                retorno.setUrgencia(4);
                            }
                            break;
                        case 15:
                            retorno.setStatus(statusMax);
                            break;
                        default:
                            retorno.setStatus(statusMax);
                            break;

                    }
                }
            }


        }
        catch (Exception e){
            //TODO
        }

        // sessao.update(retorno);
        //transacao.commit();
        sessao.close();
        return retorno;
    }
    public void atualizarContrato(Contrato retorno) throws Exception {
        int status;
        int statusMax;
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        Criteria criteria2 = sessao.createCriteria(Album.class).setProjection(Projections.min("status"));
        Criteria criteria3 = sessao.createCriteria(Album.class).setProjection(Projections.max("status"));
        criteria2.add(Restrictions.eq("contrato",retorno));
        criteria3.add(Restrictions.eq("contrato",retorno));
        try {
            status = (Integer)criteria2.uniqueResult();
            statusMax = (Integer)criteria3.uniqueResult();
            if(status>=0){
                if(status<=18){

                    switch (statusMax){
                        case 8:
                            retorno.setStatus(statusMax);
                            break;
                        case 11:
                            if(status != statusMax) {  // caso o st
                                if(status == 8){
                                    retorno.setUrgencia(4);
                                    retorno.setOcupado(false);
                                }
                                else if(status == 7){
                                    retorno.setStatus(7);
                                    retorno.setUrgencia(4);
                                    retorno.setOcupado(false);
                                }
                                else {
                                    retorno.setStatus(statusMax - 8);
                                    retorno.setUrgencia(0);
                                    retorno.setOcupado(true);
                                }
                            }
                            else {
                                retorno.setStatus(statusMax);
                                retorno.setUrgencia(4);
                                retorno.setOcupado(false);
                            }
                            break;
                        case 13:
                            if(status != statusMax) {
                                if(status>=6 & status <=10) {
                                    retorno.setStatus(10);
                                }
                                else {

                                    retorno.setStatus(statusMax);
                                }
                            }
                            else {
                                retorno.setStatus(statusMax);
                            }
                            break;

                        case 14:
                            if(status != statusMax) {
                                if(status>=6 & status <=10) {
                                    retorno.setStatus(10);
                                }
                                else {
                                    if (status==13){
                                        retorno.setUrgencia(4);
                                        retorno.setOcupado(false);
                                    }
                                    else {
                                        retorno.setStatus(statusMax - 1);
                                    }
                                }
                            }
                            else {
                                retorno.setStatus(statusMax);
                                retorno.setUrgencia(4);
                                retorno.setOcupado(false);
                            }
                            break;
                        case 15:
                            retorno.setStatus(statusMax);
                            break;
                        default:
                            retorno.setStatus(statusMax);
                            break;

                    }
                }
            }


        }
        catch (Exception e){
            //TODO
        }

        sessao.update(retorno);
        transacao.commit();
        sessao.close();

    }



    public List<Contrato> listarContratosPorFicha(Ficha ficha) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        int status;
        int statusMax;
        System.out.println("Chegou no hibernate");
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.eq("ficha", ficha));
        List<Contrato> listaRetorno = criteria.list();

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
