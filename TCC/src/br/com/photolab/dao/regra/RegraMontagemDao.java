package br.com.photolab.dao.regra;

import br.com.photolab.dao.relatorio.RelatorioDao;
import br.com.photolab.dao.modelo.AlbumDao;
import br.com.photolab.dao.modelo.ContratoDao;
import br.com.photolab.bean.usuarios.usuario.UsuarioBean;
import br.com.photolab.modelo.Album;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.modelo.Usuario;
import br.com.photolab.util.HibernateUtil;
import org.hibernate.Session;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by marcelo on 27/09/2014.
 */
@ManagedBean
public class RegraMontagemDao implements Serializable {
    private UsuarioBean usuarioBean;
    ContratoDao contDao = new ContratoDao();
    RelatorioDao relatorioDao = new RelatorioDao();
    AlbumDao albumDao = new AlbumDao();



    public Album NovoAlbum(Usuario usuario) throws Exception { //sobrecarga com "USER"
        Album retorno = null;
        Contrato contrato =  contDao.listarContratoStatus(10, 13, 1);

        if (contrato!= null) {
            retorno = albumDao.ProximoAlbum(contrato,10,12);

            if (retorno != null) {
                if (usuario != null){
                    retorno.setUsuarioMontagem(usuario);
                }

                retorno.setStatus(13); //Marca o album como "montagem"
                retorno.setOcupado(true);
                Session sessao = HibernateUtil.getSession();
                org.hibernate.Transaction transacao = sessao.beginTransaction();
                sessao.update(retorno);
                transacao.commit();
                sessao.close();

                contrato = contDao.atualizarContratoRetorna(contrato); //Verifica qual a condição geral do contrato, baseado no status dos albuns
                contrato.setUrgencia(0);
                contrato.setOcupado(true);

                sessao = HibernateUtil.getSession(); //Registra a condição atual do contrato
                transacao = sessao.beginTransaction();
                sessao.update(contrato);
                retorno.setContrato(contrato); //Atualiza contrato do album atual
                transacao.commit();
                sessao.close();
            }
            else
            {
                contrato = contDao.atualizarContratoRetorna(contrato);
                contrato.setUrgencia(4);
                contrato.setOcupado(false);
                Session sessao = HibernateUtil.getSession();
                org.hibernate.Transaction transacao = sessao.beginTransaction();
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
            contDao.atualizarContrato(album.getContrato());
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
            contDao.atualizarContrato(album.getContrato());
        }
        catch (Exception e) {
        }

    }


}
