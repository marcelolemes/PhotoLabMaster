package br.com.login.Dao;

import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.User;
import br.com.login.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by marcelo on 27/09/2014.
 */
public class RegraTratamentoDao implements Serializable {

    public Album NovoAlbum() throws Exception {

        Contrato contrato = contratoAtual();
        if (contrato!= null) {
            Session sessao = HibernateUtil.getSession();
            org.hibernate.Transaction transacao = sessao.beginTransaction();
            Criteria criteria = sessao.createCriteria(Album.class);
            criteria.add(Restrictions.eq("contrato", contrato));
            criteria.add(Restrictions.eq("ocupado", false));
            criteria.add(Restrictions.ge("status", 5));
            criteria.add(Restrictions.le("status", 10));
            criteria.addOrder(Order.asc("numero"));
            criteria.setMaxResults(1);
            Album retorno = (Album) criteria.uniqueResult();
            if (retorno == null) {
                contratoPronto(contrato);
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "TRATAMENTO!",
                                "Contrato "+contrato.getNumeroContrato()+" terminado, tente pegar album novamente para iniciar um novo contrato disponível"));
            }
            else {

                retorno.setStatus(11);
                retorno.setOcupado(true);
                sessao.update(retorno);
                transacao.commit();
            }
            try {
                sessao.close();

            }
            catch (Exception e){}
            return retorno;
        }
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Sem contratos para o seu setor",
                        "Sem contratos para o seu setor, informe o seu superior imediatamente!"));
        return null;
    }

    public Album NovoAlbum(User user) throws Exception { //sobrecarga com "USER"

        Contrato contrato = contratoAtual();
        if (contrato!= null) {
            Session sessao = HibernateUtil.getSession();
            org.hibernate.Transaction transacao = sessao.beginTransaction();
            Criteria criteria = sessao.createCriteria(Album.class);
            criteria.add(Restrictions.eq("contrato", contrato));
            criteria.add(Restrictions.eq("ocupado", false));
            criteria.add(Restrictions.ge("status", 5));
            criteria.add(Restrictions.le("status", 10));
            criteria.addOrder(Order.asc("numero"));
            criteria.setMaxResults(1);
            Album retorno = (Album) criteria.uniqueResult();
            if (retorno == null) {
                contratoPronto(contrato);
                //   FacesContext.getCurrentInstance().addMessage(
                //         null,
                //       new FacesMessage(FacesMessage.SEVERITY_INFO, "Contrato terminado",
                //             "Contrato "+contrato.getNumeroContrato()+" terminado, tente pegar album novamente para iniciar um novo contrato disponível"));
            }
            else {
                if (user != null){
                    retorno.setUserTratamento(user);
                }

                retorno.setStatus(8);
                retorno.setOcupado(true);
                sessao.update(retorno);
                transacao.commit();
            }
            try {
                sessao.close();

            }
            catch (Exception e){}
            return retorno;
        }
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Sem contratos para o seu setor",
                        "Sem contratos para o seu setor, informe o seu superior imediatamente!"));
        return null;
    }

    public void albumTerminado(Album album) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        int status =0;
        int statusMax =0;
        Contrato contrato = album.getContrato();

        if (album!=null){
            album.setStatus(11);
            album.setOcupado(false);
            sessao.update(album);
            transacao.commit();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Album "+album.getNumero()+" encerrado" ,
                            "Pegue outro album para continuar trabalhando"));


            Criteria criteria2 = sessao.createCriteria(Album.class).setProjection(Projections.min("status"));
            Criteria criteria3 = sessao.createCriteria(Album.class).setProjection(Projections.max("status"));
            criteria2.add(Restrictions.eq("contrato",contrato));
            criteria3.add(Restrictions.eq("contrato",contrato));

            try {
                status = (Integer)criteria2.uniqueResult();
                statusMax = (Integer)criteria3.uniqueResult();
                if(status>=0){
                    if(status<=18){
                        System.out.println("Alterar status ao fechar");
                        switch (statusMax){

                            case 8:
                                contrato.setStatus(statusMax);
                                break;
                            case 11:
                                if(status != statusMax) {  // caso o st
                                    contrato.setStatus(statusMax - 3);
                                }
                                else {
                                    contrato.setStatus(statusMax);
                                }
                                break;
                            case 13:
                                if(status != statusMax) {
                                    if(status>=6 & status <=10) {
                                        contrato.setStatus(10);
                                    }

                                }
                                else {
                                    contrato.setStatus(statusMax);
                                }
                                break;
                            case 14:
                                if(status != statusMax) {
                                    if(status>=6 & status <=10) {
                                        contrato.setStatus(10);
                                    }
                                    else {
                                        contrato.setStatus(statusMax - 1);
                                    }
                                }
                                else {
                                    contrato.setStatus(statusMax);
                                }
                                break;
                            case 15:
                                contrato.setStatus(statusMax);
                                break;
                            default:
                                contrato.setStatus(status);
                                break;

                        }

                    }
                }


            }
            catch (Exception e){
                //TODO
            }
        }
        try {
            sessao.update(contrato);
            transacao.commit();
            sessao.close();
        }
        catch (Exception e) {
        }

    }

    public void albumCancelado(Album album) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        if (album!=null){
            if(album.getContrato().getStatus() == 11 ){
                album.getContrato().setStatus(7);
            }
            album.setStatus(7);
            album.setOcupado(false);
        }
        sessao.update(album.getContrato());
        sessao.update(album);

        transacao.commit();
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Album "+album.getNumero()+" cancelado" ,
                        "Informe ao seu superior"));
        try {
            sessao.close();
        }
        catch (Exception e) {
        }

    }


    public void albumDeletado(Album album) throws Exception {
              Session sessao = HibernateUtil.getSession();
              org.hibernate.Transaction transacao = sessao.beginTransaction();
              if (album!=null){
                  sessao.delete(album);
              }

              transacao.commit();
              FacesContext.getCurrentInstance().addMessage(
                      null,
                      new FacesMessage(FacesMessage.SEVERITY_INFO, "Album "+album.getNumero()+" Removido" ,
                              "por favor, mova-o para a pasta 'Menos de vinte'"));
              try {
                  sessao.close();
              }
              catch (Exception e) {
              }

          }


    public Contrato contratoAtual() throws Exception {

        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        int status;
        int statusMax;
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.ge("status",5));
        criteria.add(Restrictions.le("status", 10));
        criteria.addOrder(Order.asc("urgencia")).addOrder(Order.asc("status")).addOrder(Order.asc("cod"));
        criteria.setMaxResults(1);
        Contrato retorno = (Contrato) criteria.uniqueResult();
        if (retorno != null ) {
            if (retorno.getUrgencia() > 0) {
                retorno.setUrgencia(0);
            }
            if (retorno.getStatus()>5 ||retorno.getStatus() <10){
                //                retorno.setStatus(10);


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
                                    }
                                    break;
                                case 13:
                                    if(status != statusMax) {
                                        if(status>=6 & status <=10) {
                                            retorno.setStatus(10);
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
                                    }
                                    break;
                                case 15:
                                    retorno.setStatus(statusMax);
                                    break;
                                default:
                                    retorno.setStatus(status);
                                    break;

                            }
                        }
                    }


                }
                catch (Exception e){
                    //TODO
                }

            }
            sessao.update(retorno);
            transacao.commit();
        }
        else {

        }
        sessao.close();
        return retorno;
    }
    public void contratoPronto(Contrato contrato) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Album.class).setProjection(Projections.rowCount());
        criteria.add(Restrictions.eq("ocupado", true));

        long cont = (Long) criteria.uniqueResult();
        if(cont == 0) {
            try {
                contrato.setUrgencia(4);
                contrato.setStatus(11);

                sessao.update(contrato);
                transacao.commit();
                sessao.close();

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Contrato encerrado" ,
                                "Informe ao seu superior"));
            }
            catch (Exception e){

            }


        }
        else {
            System.out.println(""+cont);
            try{

                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Contrato encerrado" ,
                                "Informe ao seu superior"));

                contrato.setStatus(11);
                contrato.setUrgencia(4);
                sessao.update(contrato);
                transacao.commit();
                sessao.close();
            }
            catch (Exception e){
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Algo deu errado" ,
                                "Tente outra vez"));
            }
        }

    }
}
