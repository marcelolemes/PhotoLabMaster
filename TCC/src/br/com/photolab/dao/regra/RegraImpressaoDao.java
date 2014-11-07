package br.com.photolab.dao.regra;

import br.com.photolab.bean.usuarios.usuario.UsuarioBean;
import br.com.photolab.dao.modelo.AlbumDao;
import br.com.photolab.dao.modelo.ContratoDao;
import br.com.photolab.dao.relatorio.RelatorioDao;
import br.com.photolab.modelo.Album;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.modelo.Usuario;
import br.com.photolab.util.HibernateUtil;
import org.hibernate.Session;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcelo on 27/09/2014.
 */
@ManagedBean
public class RegraImpressaoDao implements Serializable {
    private UsuarioBean usuarioBean;
    ContratoDao contDao = new ContratoDao();
    AlbumDao albumDao = new AlbumDao();


    public RegraImpressaoDao() {

        try {



        }
        catch (Exception ex){

        }
    }

    public void albumImpresso(Album album) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();


        if (album!=null){
            album.setStatus(16);
            album.setOcupado(false);
            sessao.update(album);
            transacao.commit();
        }

        try {
            sessao.close();
            contDao.atualizarContrato(album.getContrato());
        }
        catch (Exception e) {
        }

    }


    public void contratoImpressao(Contrato contrato) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();


        if (contrato!=null){
            contrato.setStatus(15);
            sessao.update(contrato);
            transacao.commit();
        }

        try {
            sessao.close();
            //contDao.atualizarContrato(contrato);
        }
        catch (Exception e) {
        }

    }

}
