package br.com.photolab.bean.usuarios.usuario;

import br.com.photolab.dao.relatorio.RelatorioDao;
import br.com.photolab.relatorio.Relatorio;
import br.com.photolab.modelo.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 06/10/14.
 */
@ManagedBean
@ViewScoped
public class HistoricoUserBean
{
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;
    private Relatorio relatorio = new Relatorio();
    private RelatorioDao relatorioDao = new RelatorioDao();
    private List<Relatorio> relatorioList;
    private Date dataInicial;
    private Date dataFinal;


    public HistoricoUserBean() {
        relatorioList = new ArrayList<Relatorio>();
    }

    public void inicializarLista() throws Exception {

        albunsEspaçoTempo(usuarioBean.getUsuarioLogado(),dataInicial,dataFinal);
    }
    public List<Relatorio> inicializarListaMes() throws Exception {

          return relatorioDao.ListarAlbunsMes(usuarioBean.getUsuarioLogado());
      }

    public void albunsEspaçoTempo(Usuario usuario,Date dataInicial,Date dataFinal) throws Exception {

        relatorioList =relatorioDao.ListarIntervalo(usuario,dataInicial,dataFinal);
    }


    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    public RelatorioDao getRelatorioDao() {
        return relatorioDao;
    }

    public void setRelatorioDao(RelatorioDao relatorioDao) {
        this.relatorioDao = relatorioDao;
    }

    public List<Relatorio> getRelatorioList() {
        return relatorioList;
    }

    public void setRelatorioList(List<Relatorio> relatorioList) {
        this.relatorioList = relatorioList;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }
}
