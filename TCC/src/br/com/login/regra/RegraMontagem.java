package br.com.login.regra;

import br.com.login.Dao.RegraMontagemDao;
import br.com.login.Dao.UserDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Album;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

/**
 * Created by marcelo on 26/09/2014.
 */
@ManagedBean

public class RegraMontagem implements Serializable {
    UserDao userDao = new UserDao();
    //private Contrato contratoAtual;
    private Album albumMontar;
    private RegraMontagemDao regDao = new RegraMontagemDao();
    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    @PostConstruct
    public void iniciar(){

        try {

            //albumMontar= regDao.NovoAlbum();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btPegarAlbum() throws Exception {
        userBean.getUserLogado().setAlbumAtual(regDao.NovoAlbum(userBean.getUserLogado()));
        userDao.Update(userBean.getUserLogado());
    }

    public void btCancelarAlbum() throws Exception {
        regDao.albumCancelado(userBean.getUserLogado().getAlbumAtual());
        userBean.getUserLogado().setAlbumAtual(null);
        userDao.Update(userBean.getUserLogado());
    }

    public void btTerminarAlbum() throws Exception {
        regDao.albumTerminado(userBean.getUserLogado().getAlbumAtual());
        userBean.getUserLogado().setAlbumAtual(null);
        userDao.Update(userBean.getUserLogado());
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
}
