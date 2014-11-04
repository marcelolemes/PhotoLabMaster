package br.com.login.bean.users.admin.listar;

import br.com.login.Dao.RelatorioDiarioDao;
import br.com.login.Dao.UserDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Mes;
import br.com.login.model.Metricas;
import br.com.login.model.RelatorioDiario;
import br.com.login.model.User;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import org.primefaces.model.chart.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class ListarMeseslBean
{

    private List<Mes> meses;

    public ListarMeseslBean()  {
        try {
            meses = new ArrayList<Mes>();
            meses.add(new Mes(0,"Janeiro"));
            meses.add(new Mes(1,"Fevereiro"));
            meses.add(new Mes(2,"Março"));
            meses.add(new Mes(3,"Abril"));
            meses.add(new Mes(4,"Maio"));
            meses.add(new Mes(5,"Junho"));
            meses.add(new Mes(6,"Julho"));
            meses.add(new Mes(7,"Agosto"));
            meses.add(new Mes(8,"Setembro"));
            meses.add(new Mes(9,"Outubro"));
            meses.add(new Mes(10,"Novembro"));
            meses.add(new Mes(11,"Dezembro"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btProducaoMensalMontagem() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect( FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/user/producao_mensal_montagem.jsf");
    }

    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }




}
