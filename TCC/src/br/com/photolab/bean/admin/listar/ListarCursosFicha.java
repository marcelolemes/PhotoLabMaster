package br.com.photolab.bean.admin.listar;

import br.com.photolab.dao.modeloDao.ContratoDao;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.modelo.apoio.Metricas;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class ListarCursosFicha implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */

    ContratoDao contDao = new ContratoDao();
    private Metricas metricas = new Metricas();
    private List<Contrato> listaContrato;
    public ListarCursosFicha() throws Exception {

        listaContrato = contDao.listarContratos();

    }


    public String atualizar() throws Exception {
        try {
            listaContrato.clear();
            contDao.listarContratos().clear();
        } catch (Exception ex) {
        }
        listaContrato = contDao.listarContratos();

        return "/pages/admin/conteudo/visualizarcursos_ficha.xhtml";
    }

    public ContratoDao getContDao() {
        return contDao;
    }

    public void setContDao(ContratoDao contDao) {
        this.contDao = contDao;
    }

    public Metricas getMetricas() {
        return metricas;
    }

    public void setMetricas(Metricas metricas) {
        this.metricas = metricas;
    }


}
