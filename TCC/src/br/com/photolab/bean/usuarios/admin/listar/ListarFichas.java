package br.com.photolab.bean.usuarios.admin.listar;

import br.com.photolab.dao.modelo.ContratoDao;
import br.com.photolab.dao.modelo.FichaDao;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.modelo.Ficha;
import br.com.photolab.modelo.apoio.Metricas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListarFichas implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static Ficha fichaSelecionada = new Ficha();
    /**
     *
     */
    FichaDao fichaDao = new FichaDao();
    Metricas metricas = new Metricas();
    ContratoDao contDao = new ContratoDao();
    private List<Ficha> listaFichas;
    List<Contrato> contratosFiltrados = new ArrayList<Contrato>();

    public static Ficha getFichaSelecionada() {
        return fichaSelecionada;
    }

    public void setFichaSelecionada(Ficha fichaSelecionada) {
        this.fichaSelecionada = fichaSelecionada;
    }


    @PostConstruct
    public void inicializarLista() {
        try {

            listaFichas = fichaDao.ListarFichas();
        } catch (Exception e) {
            //
            e.printStackTrace();
        }

    }

    public void atualizar() throws Exception {
        this.listaFichas.clear();
        this.fichaDao.ListarFichas().clear();
        this.listaFichas = fichaDao.ListarFichas();
    }

    public List<Contrato> listarCursosFicha() throws Exception {

        List<Contrato> lista_contratos = contDao.listarContratosPorFicha(fichaSelecionada);

        return lista_contratos;
    }

    public String btListarCursosFicha() {
        return "/pages/admin/conteudo/visualizarcursos_ficha.xhtml?redirect=false"; //não mexer
    }

    public String parserUrgencia(int urgencia) {
        return metricas.getUrgenciaLista().get(urgencia).getLabel();
    }

    public String parserStatus(int status) {
        return metricas.getStatusContratoLista().get(status).getLabel();
    }


    public List<Ficha> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(List<Ficha> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public List<Contrato> getContratosFiltrados() {
        return contratosFiltrados;
    }

    public void setContratosFiltrados(List<Contrato> contratosFiltrados) {
        this.contratosFiltrados = contratosFiltrados;
    }

}
