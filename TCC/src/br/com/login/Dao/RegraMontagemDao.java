package br.com.login.Dao;

import br.com.login.bean.users.UserBean;
import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.Relatorio;
import br.com.login.model.User;
import br.com.login.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by marcelo on 27/09/2014.
 */
@ManagedBean
public class RegraMontagemDao implements Serializable {
    private UserBean userBean;
    ContratoDao contDao = new ContratoDao();
    RelatorioDao relatorioDao = new RelatorioDao();
    AlbumDao albumDao = new AlbumDao();



    public Album NovoAlbum(User user) throws Exception { //sobrecarga com "USER"
        Album retorno = null;
        Contrato contrato =  contDao.listarContratoStatus(11, 13, 0);

        if (contrato!= null) {
            contrato = contDao.atualizarContratoRetorna(contrato);
            contrato.setUrgencia(0);
            contrato.setOcupado(true);

            System.out.println("Contrato atual teste atualizado "+contrato.isOcupado());
            retorno = albumDao.ProximoAlbum(contrato);

            System.out.println("Retorno album "+retorno.getNumero());

            if (retorno != null) {
                if (user != null){
                    retorno.setUserMontagem(user);
                }

                retorno.setStatus(13);
                retorno.setOcupado(true);

                Session sessao = HibernateUtil.getSession();
                org.hibernate.Transaction transacao = sessao.beginTransaction();
                sessao.update(retorno);
                sessao.update(contrato);
                transacao.commit();
                sessao.close();
            }

            return retorno;
        }
        else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Sem albuns para o seu setor",
                            "Sem albuns para o seu setor, informe o seu superior imediatamente!"));

            return retorno;
        }
    }

    public void albumTerminado(Album album) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();


        if (album!=null){
            album.setStatus(14);
            album.setOcupado(false);
            sessao.update(album);
            transacao.commit();



            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Album "+album.getNumero()+" encerrado" ,
                            "Pegue outro album para continuar trabalhando"));
        }



        try {
            sessao.close();
            contDao.atualizarContrato(album.getContrato());
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


    public void  albumCancelado(Album album) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        if (album!=null){
            album.setStatus(12);
            album.setOcupado(false);
        }
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



    public Contrato contratoAtual() throws Exception {

        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();

        Contrato retorno = contDao.listarContratoStatus(11, 13, 0);

        if (retorno != null ) {
            retorno =contDao.atualizarContratoRetorna(retorno);
            System.out.println(retorno.getCurso() + "Contrato atual teste");
            retorno.setUrgencia(0);
            retorno.setOcupado(true);

            sessao.update(retorno);
            transacao.commit();
            sessao.close();

        }
        return retorno;
    }
    public void contratoPronto(Contrato contrato) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Album.class).setProjection(Projections.rowCount());
        criteria.add(Restrictions.le("status", 13));
        contrato.setOcupado(false);
        long cont = (Long) criteria.uniqueResult();
        if(cont == 0) {
            try {
                contrato.setStatus(14);
                contrato.setUrgencia(4);
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
                Criteria criteria2 = sessao.createCriteria(Album.class).setProjection(Projections.rowCount());
                criteria2.add(Restrictions.le("status", 13));
                criteria2.add(Restrictions.le("ocupado", false));
                long cont2 = (Long) criteria2.uniqueResult();
                if(cont2 == 0) {
                    contrato.setUrgencia(4);
                    sessao.clear();
                    sessao.update(contrato);
                    transacao.commit();
                    sessao.close();
                }
                contrato.setUrgencia(4);
            }
            catch (Exception e){
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Algo deu errado" ,
                                "Tente outra vez"));
                System.out.println("Deu ruim");
            }
        }

    }
}
