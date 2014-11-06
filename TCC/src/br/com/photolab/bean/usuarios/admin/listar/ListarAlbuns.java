package br.com.photolab.bean.usuarios.admin.listar;

import br.com.photolab.dao.modelo.AlbumDao;
import br.com.photolab.bean.usuarios.usuario.UsuarioBean;
import br.com.photolab.modelo.Album;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.modelo.apoio.Metricas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListarAlbuns implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    @ManagedProperty("#{listarCursos}")
    ListarCursos listarCursos;
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;
    AlbumDao albumDao = new AlbumDao();
    Metricas metricas = new Metricas();
    private List<Album> listaAlbuns;
    private long qtdAlbuns;
    private long qtdFotosTotal;

    @PostConstruct
    public void inicializarLista() {
        try {

            albumDao = new AlbumDao();

            qtdAlbuns = albumDao.ContarAlbunsContrato(usuarioBean.getContratoSelecionado());
            qtdFotosTotal = albumDao.ContarFotosContrato(usuarioBean.getContratoSelecionado());
            listaAlbuns = albumDao.ListarAlbunsContrato(usuarioBean.getContratoSelecionado());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void btAtualizar() throws Exception{
        inicializarLista();
    }






    public String parserUrgencia(int urgencia) {
        return metricas.getUrgenciaLista().get(urgencia).getLabel();
    }

    public String parserStatus(int status) {
        return metricas.getStatusContratoLista().get(status).getLabel();
    }

    public List<Album> getListaAlbuns() {
        return listaAlbuns;
    }

    public void setListaAlbuns(List<Album> listaAlbuns) {
        this.listaAlbuns = listaAlbuns;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public long getQtdAlbuns() {
        return qtdAlbuns;
    }

    public void setQtdAlbuns(long qtdAlbuns) {
        this.qtdAlbuns = qtdAlbuns;
    }

    public long getQtdFotosTotal() {
        return qtdFotosTotal;
    }

    public void setQtdFotosTotal(long qtdFotosTotal) {
        this.qtdFotosTotal = qtdFotosTotal;
    }
}
