package br.com.photolab.regra;

import br.com.photolab.bean.usuario.UsuarioBean;
import br.com.photolab.dao.modeloDao.AlbumDao;
import br.com.photolab.dao.modeloDao.ContratoDao;
import br.com.photolab.dao.regraDao.RegraImpressaoDao;
import br.com.photolab.modelo.Album;
import br.com.photolab.modelo.Contrato;
import com.lowagie.text.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelo on 26/09/2014.
 */
@ManagedBean
@ViewScoped
public class RegraImpressao implements Serializable {
    private RegraImpressaoDao regDao = new RegraImpressaoDao();
    AlbumDao albumDao = new AlbumDao();
    ContratoDao contDao = new ContratoDao();
    private List<Album> listaAlbuns=new ArrayList<Album>();
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;
    @PostConstruct
    public void iniciar(){

        try {
            listaAlbuns = albumDao.ListarAlbunsContrato(contDao.listarContratoStatus(15));
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void btContratoImpressao(Contrato contrato) throws Exception {


        regDao.contratoImpressao(contrato);
        usuarioBean.btHome();
    }


    public void btImprimindo(Album album) throws Exception {

        album.setQtdFotos(qtdFotosAtual(album.getContrato().getCaminho()+File.separator+album.getNumero()));
        regDao.albumemImpressao(album);

    }
    public void btImpresso(Album album) throws Exception {


        File file = new File(album.getContrato().getCaminho()+File.separator+album.getNumero());
        if (file.isDirectory()){
            album.setQtdFotos(qtdFotosAtual(album.getContrato().getCaminho()+File.separator+album.getNumero()));
        }
        else {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Atualizar Contrato", "Caminho incorreto, contrato provavelmente movido!"));
        }


        regDao.albumImpresso(album);

    }

    public int qtdFotosAtual(String caminho){
        try {
            File file = new File(caminho);
            return file.listFiles(fotoFilter).length;
        }
        catch (Exception e){
            return 0;
        }

    }

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

    public void listaPdfAlbuns(Object document) throws IOException,
            BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.add(new Paragraph("Setor impressão"));
        pdf.add(new Paragraph("Lista de albuns do contrato: "+usuarioBean.getContratoSelecionado().getNumeroContrato()+" curso: "+usuarioBean.getContratoSelecionado().getCurso()));
        pdf.add(new Paragraph("Total de fotos do contrato: "+usuarioBean.getQtdFotosAux()+"  Total de albuns do contrato: "+usuarioBean.getContratoSelecionado().getQtdAlbum()));
        pdf.add(new Paragraph(" "));


    }
    public void posListaPdfAlbuns(Object document) throws IOException,
            BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.add(new Paragraph(" "));
        pdf.add(new Paragraph("Total de fotos do contrato: "+usuarioBean.getQtdFotosAux()+"  Total de albuns do contrato: "+usuarioBean.getContratoSelecionado().getQtdAlbum()));



    }
    public void preFicha(Object document) throws Exception {
        Document pdf = (Document) document;
        pdf.open();
        pdf.add(new Paragraph("PhotoLabMaster© Setor impressão"));
        pdf.add(new Paragraph("Lista de albuns do contrato: "+listaAlbuns.get(0).getContrato().getNumeroContrato()));
        pdf.add(new Paragraph("Curso de: "+listaAlbuns.get(0).getContrato().getCurso()));
        pdf.add(new Paragraph("da cidade: "+listaAlbuns.get(0).getContrato().getCidade()));
        pdf.add(new Paragraph("Localizado em "+listaAlbuns.get(0).getContrato().getCaminho()));
        pdf.add(new Paragraph(" "));
        pdf.add(new Paragraph(" "));
        pdf.addAuthor("PhotoLabMaster");

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
}
