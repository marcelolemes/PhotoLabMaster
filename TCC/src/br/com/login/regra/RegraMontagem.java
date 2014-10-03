package br.com.login.regra;

import br.com.login.Dao.AlbumDao;
import br.com.login.Dao.RegraMontagemDao;
import br.com.login.Dao.RelatorioDao;
import br.com.login.Dao.UserDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Album;
import br.com.login.model.Relatorio;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.sql.Timestamp;
import java.io.Serializable;
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
    AlbumDao albumDao = new AlbumDao();
    private RegraMontagemDao regDao = new RegraMontagemDao();
    private RelatorioDao relatorioDao = new RelatorioDao();
    private long albunsRestantes;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    @PostConstruct
    public void iniciar(){

        try {

            albunsRestantes = relatorioDao.AlbunsRestantesMontagem(regDao.contratoAtual());


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
            userBean.getUserLogado().setAuxiliar(userBean.getUserLogado().getAlbumAtual().getContrato().getCaminho()+"\\"+ userBean.getUserLogado().getAlbumAtual().getNumero());
            iniciar();
        }
        catch (Exception e)
        {

        }

        userDao.Update(userBean.getUserLogado());

    }

    public void btCancelarAlbum() throws Exception {
        regDao.albumCancelado(userBean.getUserLogado().getAlbumAtual());
        userBean.getUserLogado().setAlbumAtual(null);
        userDao.Update(userBean.getUserLogado());
    }

    public void btTerminarAlbum() throws Exception {
        try{
            relatorio = new Relatorio();
            relatorio.setAlbum(userBean.getUserLogado().getAlbumAtual());
            relatorio.setFuncionario(userBean.getUserLogado());
            relatorio.setDataOperacao(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            relatorioDao.salvarRelatorio(relatorio);
            userBean.getUserLogado().setAlbumAtual(null);
            userDao.Update(userBean.getUserLogado());

            iniciar();

        }
        catch (Exception e){}

        regDao.albumTerminado(userBean.getUserLogado().getAlbumAtual());




    }

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
}
