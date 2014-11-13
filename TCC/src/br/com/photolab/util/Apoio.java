package br.com.photolab.util;

import br.com.photolab.dao.modeloDao.AlbumDao;
import br.com.photolab.dao.modeloDao.ContratoDao;
import br.com.photolab.modelo.Album;
import br.com.photolab.modelo.Contrato;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class Apoio implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6549414550211411055L;
    private static int qtdFotos;
    private static int qtdAlbuns;
    private static int menosde50;
    private static int maisde50;
    ContratoDao contDao = new ContratoDao();


    public Apoio() {
        qtdFotos =0;
        qtdAlbuns =0;
        maisde50 =0;
        menosde50 =0;
    }

    FilenameFilter pastaFilter = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.startsWith("20")) {
                return true;
            } else {
                return false;
            }

        }
    };
    FilenameFilter fotoFilter = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".jpg")) {
                return true;
            } else {
                return false;
            }

        }
    };
    /**
     *
     */

    private Album album = new Album();
    private AlbumDao albumDao = new AlbumDao();
    private String contratoPesquisarNumero;


    public void atualizarAlbunsCurso(Contrato contrato) throws Exception {

        File[] pastas;
        File diretorio = new File(contrato.getCaminho());
        pastas = diretorio.listFiles(pastaFilter);
        System.out.println("Albuns Gerados");
        persistirListaAlbum(pastas, contrato);
        contrato.setQtdFotos(qtdFotos);
        contrato.setQtdAlbum(qtdAlbuns);
        contrato.setMaisde50(maisde50);
        contrato.setMenosde50(menosde50);
        contDao.Gravar(contrato);

        FacesContext
                .getCurrentInstance()
                .addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, //não testado
                                "Gerar albuns",
                                pastas.length + " albuns gerados, do contrato " + contrato.getNumeroContrato()));

    }



    public void persistirListaAlbum(File[] albuns, Contrato contrato)
            throws Exception {
        List<Album> listaAlbum = new ArrayList<Album>();

        for (File file : albuns) {
            Album album = new Album();
            album.setNumero(file.getName());
            album.setQtdFotos(file.listFiles(fotoFilter).length);
            album.setOcupado(false);
            album.setContrato(contrato);
            qtdFotos = qtdFotos+ album.getQtdFotos();
            if(qtdFotos>=50)
            {
                maisde50++;
            }
            else{
                menosde50++;
            }
            album.setStatus(contrato.getStatus());
            listaAlbum.add(album);
            qtdAlbuns++;
        }

        System.out.println("Lista Pronta para persistencia "+listaAlbum.size());

        albumDao.AtualizarLista(listaAlbum, contrato);

    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getContratoPesquisarNumero() {
        return contratoPesquisarNumero;
    }

    public void setContratoPesquisarNumero(String contratoPesquisarNumero) {
        this.contratoPesquisarNumero = contratoPesquisarNumero;
    }

}
