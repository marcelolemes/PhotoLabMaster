package br.com.photolab.regra;

import br.com.photolab.dao.modelo.AlbumDao;
import br.com.photolab.dao.modelo.ContratoDao;
import br.com.photolab.dao.modelo.UsuarioDao;
import br.com.photolab.bean.usuarios.usuario.UsuarioBean;
import br.com.photolab.dao.regra.RegraTratamentoDao;
import br.com.photolab.dao.relatorio.RelatorioDao;
import br.com.photolab.dao.relatorio.RelatorioDiarioDao;
import br.com.photolab.modelo.Album;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.relatorio.Relatorio;
import br.com.photolab.relatorio.RelatorioDiario;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FilenameFilter;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by marcelo on 26/09/2014.
 */
@ManagedBean
@ViewScoped
public class RegraTratamento implements Serializable {
    UsuarioDao usuarioDao = new UsuarioDao();
    private Relatorio relatorio;

    private Album albumMontar;
    private RegraTratamentoDao regDao = new RegraTratamentoDao();
    private RelatorioDao relatorioDao = new RelatorioDao();
    RelatorioDiario relatorioDiario;
    RelatorioDiarioDao relatorioDiarioDao =new RelatorioDiarioDao();
    AlbumDao albumDao = new AlbumDao();

    private long albunsRestantes;
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;
    ContratoDao contDao;
    private String cont;
    Contrato contratoAtual;
    @PostConstruct
    public void iniciar(){

        try {
            contDao = new ContratoDao();
            contratoAtual= contDao.listarContratoStatus(5, 10,1);
            albunsRestantes = albumDao.AlbunsRestantesTratamento(contratoAtual);
            cont =contratoAtual.getNumeroContrato();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exceção albuns restantes");
        }

    }

    public void btInserirObs() throws Exception {
        //System.out.println("Album obs "+usuarioBean.getUsuarioLogado().getAuxiliar());
        //usuarioBean.getUsuarioLogado().getAlbumAtual().setObs(usuarioBean.getUsuarioLogado().getAuxiliar());

        albumDao.gravar(usuarioBean.getUsuarioLogado().getAlbumAtual());

    }

    public void btPegarAlbum() throws Exception {



        try {
            usuarioBean.getUsuarioLogado().setAlbumAtual(regDao.NovoAlbum(usuarioBean.getUsuarioLogado()));
            usuarioBean.getUsuarioLogado().setAuxiliar(usuarioBean.getUsuarioLogado().getAlbumAtual().getContrato().getCaminho()+File.separator+ usuarioBean.getUsuarioLogado().getAlbumAtual().getNumero());
            relatorio = new Relatorio();
            relatorio.setDataInicial(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            relatorio.setAlbum(usuarioBean.getUsuarioLogado().getAlbumAtual());
            relatorio.setFuncionario(usuarioBean.getUsuarioLogado());
            relatorioDao.salvarRelatorio(relatorio);
            usuarioDao.Update(usuarioBean.getUsuarioLogado());
            iniciar();
        }
        catch (Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Algum erro ocorreu",
                            "Não foi possivel pegar o album, tente atualizar a página"));
        }



    }

    public void btCancelarAlbum() throws Exception {
        regDao.albumCancelado(usuarioBean.getUsuarioLogado().getAlbumAtual());
        relatorio = relatorioDao.encontrarRelatorio(usuarioBean.getUsuarioLogado(), usuarioBean.getUsuarioLogado().getAlbumAtual());
        relatorioDao.deletarRelatorio(relatorio);
        usuarioBean.getUsuarioLogado().setAlbumAtual(null);
        usuarioDao.Update(usuarioBean.getUsuarioLogado());
        usuarioBean.btHome();
    }

    public void btMenosDeVinte() throws Exception {
        Album album = usuarioBean.getUsuarioLogado().getAlbumAtual();
        usuarioBean.getUsuarioLogado().setAlbumAtual(null);
        usuarioDao.Update(usuarioBean.getUsuarioLogado());
        relatorio = relatorioDao.encontrarRelatorio(usuarioBean.getUsuarioLogado(),album);
        relatorioDao.deletarRelatorio(relatorio);
        regDao.albumDeletado(album);
        //   usuarioBean.btHome();
    }

    public void btTerminarAlbum() throws Exception {
        try{

            relatorio = relatorioDao.encontrarRelatorio(usuarioBean.getUsuarioLogado(), usuarioBean.getUsuarioLogado().getAlbumAtual());
            usuarioBean.getUsuarioLogado().getAlbumAtual().setQtdFotos(qtdFotosAtual(usuarioBean.getUsuarioLogado().getAlbumAtual().getContrato().getCaminho()+File.separator+ usuarioBean.getUsuarioLogado().getAlbumAtual().getNumero())); //inverter barras quando mudar de sistema operacional
            relatorio.setDataFinal(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            relatorio.setFotos(usuarioBean.getUsuarioLogado().getAlbumAtual().getQtdFotos());
            relatorioDao.salvarRelatorio(relatorio);
            regDao.albumTerminado(usuarioBean.getUsuarioLogado().getAlbumAtual());
            usuarioBean.getUsuarioLogado().setAlbumAtual(null);
            usuarioDao.Update(usuarioBean.getUsuarioLogado());

            relatorioDiario = relatorioDiarioDao.encontrarRelatorio(usuarioBean.getUsuarioLogado(),new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            if (relatorioDiario == null)
            {


                relatorioDiario =  new RelatorioDiario();



                Calendar calendar1 = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();

                calendar1.setTime(new Date());


                calendar1.clear(Calendar.HOUR_OF_DAY);
                calendar1.clear(Calendar.HOUR);
                calendar1.clear(Calendar.AM_PM);
                calendar1.clear(Calendar.MINUTE);
                calendar1.clear(Calendar.SECOND);
                calendar1.clear(Calendar.MILLISECOND);

                relatorioDiario.setDataOperacao(new Timestamp(calendar1.getTime().getTime()));
                relatorioDiario.setFuncionario(usuarioBean.getUsuarioLogado());
            }
            relatorioDiario.setQtdAlbuns((int) relatorioDao.contarAlbunsHoje(usuarioBean.getUsuarioLogado()));
            relatorioDiario.setFotos((int) relatorioDao.contarFotosHoje(usuarioBean.getUsuarioLogado()));
            relatorioDiarioDao.salvarRelatorio(relatorioDiario);
            usuarioBean.btHome();

        }
        catch (Exception e){
            System.out.println("Exception terminar album");
            e.printStackTrace();
            System.out.println("Message "+e.getMessage());
        }
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

    public Album getAlbumMontar() {

        return albumMontar;
    }

    public void setAlbumMontar(Album albumMontar) {
        this.albumMontar = albumMontar;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public long getAlbunsRestantes() {
        return albunsRestantes;
    }

    public void setAlbunsRestantes(long albunsRestantes) {
        this.albunsRestantes = albunsRestantes;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
