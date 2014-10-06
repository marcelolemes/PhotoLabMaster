package br.com.login.bean.users.admin.cadastro;

import br.com.login.Dao.FichaDao;
import br.com.login.model.Ficha;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class CadastrarFicha implements Serializable {

    FichaDao fichaDao;
    private Ficha ficha = new Ficha();
    public CadastrarFicha() {
        fichaDao = new FichaDao();
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }



    public void salvarFicha() throws Exception {
        fichaDao.Gravar(ficha);

    }


}
