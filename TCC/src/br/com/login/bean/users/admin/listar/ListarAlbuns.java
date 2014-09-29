package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.AlbumDao;
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

    AlbumDao albumDao = new AlbumDao();
    Metricas metricas = new Metricas();
    private List<Album> listaAlbuns;
    private Contrato contratoSelecionado = new Contrato();

    @PostConstruct
    public void inicializarLista() {
        try {

            listaAlbuns = albumDao.ListarAlbunsContrato(listarCursos.getContratoSelecionado());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
}
