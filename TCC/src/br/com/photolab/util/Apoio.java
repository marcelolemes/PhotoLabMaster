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
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
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
    Date data = new Date();
    ContratoDao contDao = new ContratoDao();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Apoio() {
        qtdFotos =0;
        qtdAlbuns =0;
        maisde50 =0;
        menosde50 =0;
        data.getTime();
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
        File file = new File(contrato.getCaminho());

        if (file.isDirectory()) {
            File[] pastas;
            File diretorio = new File(contrato.getCaminho());
            pastas = diretorio.listFiles(pastaFilter);
            System.out.println("Albuns Gerados");
            persistirListaAlbum(pastas, contrato);
            contrato.setQtdFotos(qtdFotos);
            contrato.setQtdAlbum(qtdAlbuns);
            contrato.setMaisde50(maisde50);
            contrato.setMenosde50(menosde50);
            contrato.setDataEntrega(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            contDao.Gravar(contrato);

            FacesContext
                    .getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, //não testado
                                    "Contrato atualizado",
                                    pastas.length + " albuns atualizados, do contrato " + contrato.getNumeroContrato()));

        }
        else {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Atualizar Contrato", "Caminho incorreto, contrato provavelmente movido!"));

        }
    }


    public void persistirListaAlbum(File[] albuns, Contrato contrato)
            throws Exception {
        List<Album> listaAlbum = new ArrayList<Album>();

        for (File file : albuns) {
            Album album = new Album();
            album.setNumero(file.getName());
            album.setQtdFotos(file.listFiles(fotoFilter).length);
            if(album.getQtdFotos()>=50)
            {
                maisde50++;
            }
            else{
                menosde50++;
            }
            album.setOcupado(false);
            album.setContrato(contrato);
            qtdFotos = qtdFotos+ album.getQtdFotos();
            album.setStatus(contrato.getStatus());
            listaAlbum.add(album);
            qtdAlbuns++;
        }

        //   System.out.println("Lista Pronta para persistencia "+listaAlbum.size());

        albumDao.AtualizarLista(listaAlbum, contrato);
        File file = new File(contrato.getCaminho());
        if(file.isDirectory()){
            gerarFechamento(contrato.getCaminho()+"\\"+contrato.getNumeroContrato()+" FECHAMENTO.html",contrato,menosde50,maisde50,qtdFotos,qtdAlbuns);
        }

        else {
            FacesContext
                    .getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Atualizar Contrato", "Caminho incorreto, contrato provavelmente movido!"));
        }
    }

    public void gerarFechamento(String caminho,Contrato contrato,int Menos50Foto, int Mais50Foto, int QtdFotos,int QtdAlbuns) throws FileNotFoundException {

        Formatter saida = new Formatter(caminho);
        saida.format("<html>\n" +
                "<body>\n" +
                "<blockquote><blockquote>\n" +
                "<font face=arial>\n" +
                "<center>\n" +
                "<table border=1 cellspacing=0 cellpadding= 4> \n" +
                "<tr> <td colspan=4><b><center> Produção Megapixel </center> </b></td> </tr> \n" +
                "<tr> <td><b>Data entrega </b></td>\n" +
                "<td><i> DATA: "+simpleDateFormat.format(data)+ "</i></td>\n" +
                "<td> FICHA: "+contrato.getFicha().getNumero()+"/"+contrato.getFicha().getAno()+"</td>\n" +
                "<td><font color=red face=verdana size=2><b><center> Albuns Trabalhados </center></font></b> </td> </tr>\n" +
                "<tr> <td><b>Curso </b></td>\n" +
                "<td>"+contrato.getCurso()+"</td>\n" +
                "<td><b>CIDADE </b></td>\n" +
                "<td>"+contrato.getCidade()+"</td></tr> \n" +
                "<tr> <td><b>Contrato </b></td>\n" +
                "<td> "+contrato.getNumeroContrato()+"</td>\n" +
                "<td> </td>\n" +
                "<td rowspan=7><IMG src=N:logo.jpg></IMG> </td>\n" +
                "</tr> \n" +
                "<tr> <td><b>Produção</b></td>\n" +
                "<td>"+QtdFotos+"</td>\n" +
                "<td><b><center>"+contrato.getTamanho()+" </center></b></td>\n" +
                "</tr> \n" +
                "<tr> <td><b>TOTAL ALBUNS </b></td>\n" +
                "<td> "+QtdAlbuns+"</td>\n" +
                "<td></td>\n" +
                "</tr> \n" +
                "<tr> <td><b>+ 50 fotos </b></td>\n" +
                "<td> "+Mais50Foto+"</td>\n" +
                "<td></td>\n" +
                "</tr> \n" +
                "<tr> <td><b>- 50 fotos </b></td>\n" +
                "<td>"+Menos50Foto+"</td>\n" +
                "<td></td>\n" +
                "</tr> \n" +
                "<tr> <td><b>- 20 fotos </b></td>\n" +
                "<td> ??</td>\n" +
                "<td></td>\n" +
                "</tr> \n" +
                "<tr> <td><b>IDENTIFICAÇÃO</b></td>\n" +
                "<td>"+QtdAlbuns*2+"  </td>\n" +
                "<td><b>10 X 15</b></td>\n" +
                "</tr> \n" +
                "</table>\n" +
                "<br>\n" +
                "<br>\n" +
                "<blockquote>_____________________________________</blockquote><br>\n" +
                "<blockquote><blockquote>Megapixel Laboratório fotográfico</blockquote></blockquote></blockquote><br><br>\n" +
                "<center>\n" +
                "RECEBIDO POR ____________________________________</blockquote></blockquote<br><br><center>\n" +
                "ASSINATURA______________________________________<br>\n" +
                "<blockquote><blockquote><blockquote><blockquote><b>"+contrato.getFicha().getCliente().getNome()+"</blockquote></blockquote></blockquote></blockquote></b>\n" +
                "</body>\n");
        saida.close();
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
