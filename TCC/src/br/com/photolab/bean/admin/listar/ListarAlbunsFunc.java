package br.com.photolab.bean.admin.listar;

import br.com.photolab.dao.relatorio.RelatorioDao;
import br.com.photolab.bean.usuario.UsuarioBean;
import br.com.photolab.relatorio.Relatorio;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListarAlbunsFunc implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;

    private long qtdDia;
    private long qtdDiaFotos;

    RelatorioDao relatorioDao = new RelatorioDao();
    private List<Relatorio> relatorioList;

    @PostConstruct
    public void listarAlbumFeito(){
        try {

            relatorioList = relatorioDao.ListarAlbunsHoje(usuarioBean.getUsuarioLogado());
            qtdDia = relatorioDao.contarAlbunsHoje(usuarioBean.getUsuarioLogado());
            qtdDiaFotos = relatorioDao.contarFotosHoje(usuarioBean.getUsuarioLogado());
        }
        catch (Exception e)
        {
            System.out.println("Exceção Relatório");
            e.printStackTrace();
            e.getMessage();

        }
    }





    public List<Relatorio> getRelatorioList() {
        return relatorioList;
    }

    public void setRelatorioList(List<Relatorio> relatorioList) {
        this.relatorioList = relatorioList;
    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public long getQtdDia() {
        return qtdDia;
    }

    public void setQtdDia(long qtdDia) {
        this.qtdDia = qtdDia;
    }

    public long getQtdDiaFotos() {
        return qtdDiaFotos;
    }

    public void setQtdDiaFotos(long qtdDiaFotos) {
        this.qtdDiaFotos = qtdDiaFotos;
    }

    public RelatorioDao getRelatorioDao() {
        return relatorioDao;
    }

    public void setRelatorioDao(RelatorioDao relatorioDao) {
        this.relatorioDao = relatorioDao;
    }
}
