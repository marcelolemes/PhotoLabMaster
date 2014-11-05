package br.com.photolab.bean.usuarios.admin.listar;

import br.com.photolab.dao.modelo.UsuarioDao;
import br.com.photolab.modelo.apoio.Metricas;
import br.com.photolab.modelo.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListaUsuariosBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6208235532882903687L;
    UsuarioDao usuarioDao = new UsuarioDao();
    Metricas metricas = new Metricas();
    private List<Usuario> listaUsuarios;

    @PostConstruct
    public void inicializarLista() {
        try {

            listaUsuarios = usuarioDao.ListarUsers();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String parserNivel(Usuario usuario) {
        try {
            return metricas.getNivelAcesso().get(usuario.getNivelAcesso())
                    .getLabel();
        }
        catch (Exception e) {
            System.out.println("Não deu");
            return null;
        }
    }

    public String parserSetor(Usuario usuario) {
        try {
            return metricas.getSetores().get(usuario.getSetor()).getLabel();
        } catch (Exception e) {
            System.out.println("Não deu");
            return null;
        }

    }


    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

}
