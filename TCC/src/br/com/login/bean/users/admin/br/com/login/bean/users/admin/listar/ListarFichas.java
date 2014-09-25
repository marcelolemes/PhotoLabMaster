package br.com.login.bean.users.admin.br.com.login.bean.users.admin.listar;

import br.com.login.Dao.ContratoDao;
import br.com.login.Dao.FichaDao;
import br.com.login.model.Contrato;
import br.com.login.model.Ficha;
import br.com.login.model.Metricas;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
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

    public List<Contrato> listarCursosFicha() throws Exception {
        System.out.println("Chegou na Ficha");
        System.out.println("FICHA : " + fichaSelecionada.getNumero());

        List<Contrato> lista_contratos = contDao.listarContratosPorFicha(fichaSelecionada);

        return lista_contratos;
    }

    public String btListarCursosFicha() {
        System.out.println("Chegou no botão");
        return "/pages/admin/conteudo/visualizarcursos_ficha.xhtml?redirect=true";
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

}
