package br.com.login.regra;

import br.com.login.Dao.*;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.Relatorio;
import br.com.login.model.RelatorioDiario;

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
public class RegraMontagem implements Serializable {
    UserDao userDao = new UserDao();
    private Relatorio relatorio;

    private Album albumMontar;
    private RegraMontagemDao regDao = new RegraMontagemDao();
    private RelatorioDao relatorioDao = new RelatorioDao();
    RelatorioDiario relatorioDiario;
    RelatorioDiarioDao relatorioDiarioDao =new RelatorioDiarioDao();
    AlbumDao albumDao = new AlbumDao();

    private long albunsRestantes;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;
    ContratoDao contDao;
    private String cont;
    Contrato contratoAtual;
    @PostConstruct
    public void iniciar(){

        try {
            contDao = new ContratoDao();
            contratoAtual= contDao.listarContratoStatus(10, 13, 1);
            albunsRestantes = albumDao.AlbunsRestantesMontagem(contratoAtual);
            cont =contratoAtual.getNumeroContrato();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exceção albuns restantes" );
        }

    }

    public void btInserirObs() throws Exception {
        //System.out.println("Album obs "+userBean.getUserLogado().getAuxiliar());
        //userBean.getUserLogado().getAlbumAtual().setObs(userBean.getUserLogado().getAuxiliar());

        albumDao.gravar(userBean.getUserLogado().getAlbumAtual());

    }

    public void btPegarAlbum() throws Exception {


        try {
            userBean.getUserLogado().setAlbumAtual(regDao.NovoAlbum(userBean.getUserLogado()));
            userBean.getUserLogado().setAuxiliar(userBean.getUserLogado().getAlbumAtual().getContrato().getCaminho()+File.separator+ userBean.getUserLogado().getAlbumAtual().getNumero());
            relatorio = new Relatorio();
            relatorio.setDataInicial(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            relatorio.setAlbum(userBean.getUserLogado().getAlbumAtual());
            relatorio.setFuncionario(userBean.getUserLogado());
            relatorioDao.salvarRelatorio(relatorio);
            userDao.Update(userBean.getUserLogado());
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
        regDao.albumCancelado(userBean.getUserLogado().getAlbumAtual());
        relatorio = relatorioDao.encontrarRelatorio(userBean.getUserLogado(),userBean.getUserLogado().getAlbumAtual());
        relatorioDao.deletarRelatorio(relatorio);
        userBean.getUserLogado().setAlbumAtual(null);
        userDao.Update(userBean.getUserLogado());
        userBean.btHome();
    }

    public void btMenosDeVinte() throws Exception {
        Album album =userBean.getUserLogado().getAlbumAtual();
        userBean.getUserLogado().setAlbumAtual(null);
        userDao.Update(userBean.getUserLogado());
        relatorio = relatorioDao.encontrarRelatorio(userBean.getUserLogado(),album);
        relatorioDao.deletarRelatorio(relatorio);
        regDao.albumDeletado(album);
        //   userBean.btHome();
    }

    public void btTerminarAlbum() throws Exception {
        try{

            relatorio = relatorioDao.encontrarRelatorio(userBean.getUserLogado(),userBean.getUserLogado().getAlbumAtual());
            userBean.getUserLogado().getAlbumAtual().setQtdFotos(qtdFotosAtual(userBean.getUserLogado().getAlbumAtual().getContrato().getCaminho()+File.separator+userBean.getUserLogado().getAlbumAtual().getNumero())); //inverter barras quando mudar de sistema operacional
            relatorio.setDataFinal(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            relatorio.setFotos(userBean.getUserLogado().getAlbumAtual().getQtdFotos());
            relatorioDao.salvarRelatorio(relatorio);
            regDao.albumTerminado(userBean.getUserLogado().getAlbumAtual());
            userBean.getUserLogado().setAlbumAtual(null);
            userDao.Update(userBean.getUserLogado());

            relatorioDiario = relatorioDiarioDao.encontrarRelatorio(userBean.getUserLogado(),new Timestamp(new Date(System.currentTimeMillis()).getTime()));
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
                relatorioDiario.setFuncionario(userBean.getUserLogado());
            }
            relatorioDiario.setQtdAlbuns((int) relatorioDao.contarAlbunsHoje(userBean.getUserLogado()));
            relatorioDiario.setFotos((int) relatorioDao.contarFotosHoje(userBean.getUserLogado()));
            relatorioDiarioDao.salvarRelatorio(relatorioDiario);
            userBean.btHome();

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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
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
