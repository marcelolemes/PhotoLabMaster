package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.AlbumDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.Metricas;

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
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    AlbumDao albumDao = new AlbumDao();
    Metricas metricas = new Metricas();
    private List<Album> listaAlbuns;
    private Contrato contratoSelecionado = new Contrato();
    private long qtdAlbuns;
    private long qtdFotosTotal;

    @PostConstruct
    public void inicializarLista() {
        try {

            albumDao = new AlbumDao();

            qtdAlbuns = albumDao.ContarAlbunsContrato(listarCursos.getContratoSelecionado());
            qtdFotosTotal = albumDao.ContarFotosContrato(listarCursos.getContratoSelecionado());
            listaAlbuns = albumDao.ListarAlbunsContrato(listarCursos.getContratoSelecionado());
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

    public Contrato getContratoSelecionado() {
        return contratoSelecionado;
    }

    public void setContratoSelecionado(Contrato contratoSelecionado) {
        this.contratoSelecionado = contratoSelecionado;
    }

    public ListarCursos getListarCursos() {
        return listarCursos;
    }

    public void setListarCursos(ListarCursos listarCursos) {
        this.listarCursos = listarCursos;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
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
