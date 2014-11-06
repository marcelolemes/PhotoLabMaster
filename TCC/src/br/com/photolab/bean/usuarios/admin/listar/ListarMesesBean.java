package br.com.photolab.bean.usuarios.admin.listar;

import br.com.photolab.modelo.Mes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.*;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class ListarMesesBean
{

    private List<Mes> meses;

    public ListarMesesBean()  {
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

    public String btProducaoMensalMontagem(){
        return "/pages/usuario/producao_mensal_montagem.jsf";
    }
    public String btProducaoMensalTratamento(){
          return "/pages/usuario/producao_mensal_tratamento.jsf";
      }
    public List<Mes> getMeses() {
        return meses;
    }

    public void setMeses(List<Mes> meses) {
        this.meses = meses;
    }




}
