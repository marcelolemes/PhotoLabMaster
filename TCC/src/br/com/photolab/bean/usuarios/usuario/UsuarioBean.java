package br.com.photolab.bean.usuarios.usuario;

import br.com.photolab.dao.modelo.UsuarioDao;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.modelo.Mes;
import br.com.photolab.modelo.apoio.Metricas;
import br.com.photolab.modelo.Usuario;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@ManagedBean(name = "usuarioBean", eager = true)
@SessionScoped
public class UsuarioBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<SelectItem> nivelAcessoCadastro = new Metricas()
            .getNivelAcesso();
    private List<SelectItem> setores = new Metricas().getSetores();
    private Contrato contratoSelecionado= new Contrato();
    private Mes mesSelecionado= new Mes();
    private int anoSelecionado= Calendar.getInstance().get(Calendar.YEAR);
    public UsuarioBean() {

        usuario = new Usuario();
        usuario.setLogado(false);

    }

    private Usuario usuario;
    private Usuario usuarioLogado;
    UsuarioDao usuarioDao;
    private String sessao = new String();


    private static boolean logado = false;

    public Usuario getUsuario() {
        return usuario;

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String atualizar() throws Exception {

        usuarioLogado = usuarioDao.atualizar(usuarioLogado);
        // return "result.xhtml";
        return "/pages/result_index.xhtml";
    }

    public String logar() throws Exception {
        if (usuario.isLogado()) {
            loginAtivo();
            if (usuarioLogado.getNivelAcesso() > 2) {
                return "/pages/admin/result_index.xhtml";
            } else {
                return "/pages/usuario/result_index" + usuarioLogado.getSetor()
                        + ".xhtml?faces-redirect=true";
            }

        } else {
            usuarioDao = new UsuarioDao();
            if ((usuarioLogado = usuarioDao.testarLogin(usuario)) != null) {
                System.out.print(" Encontrado ");
                setSessao(usuario.getApelido());
                usuario = new Usuario();
                usuario.setLogado(true);
                return messageSucessoLogin();

            } else {
                System.out.print("Não encontrado");
                usuario = new Usuario();
                usuario.setLogado(false);
                messageErroLogin();
            }
            return null;
        }
    }

    public String verificarLogado() throws Exception {

        if (usuario.isLogado() /* && (userBean.getUsuarioLogado() != null) */) {

            if (usuarioLogado.getNivelAcesso() > 2) {
                return "/pages/admin/result_index.xhtml";
            } else {
                return "/pages/usuario/result_index" + usuarioLogado.getSetor()
                        + ".xhtml";
            }

        } else {
            return "/pages/login_index.xhtml";
        }
    }

    public void btHome() throws Exception {

        if (usuario.isLogado() /* && (userBean.getUsuarioLogado() != null) */) {

            if (usuarioLogado.getNivelAcesso() > 2) {
                FacesContext.getCurrentInstance().getExternalContext().redirect( FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/admin/result_index.jsf");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect(  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/usuario/result_index" + usuarioLogado.getSetor()
                        + ".jsf");
            }

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/login_index.jsf");
        }
    }
    public void btProducao() throws Exception {

        if (usuario.isLogado() /* && (userBean.getUsuarioLogado() != null) */) {

            FacesContext.getCurrentInstance().getExternalContext().redirect(  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/usuario/relatorio_user.jsf");

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/login_index.jsf");
        }
    }




    public void btTrocarSenha() throws Exception {

        if (usuario.isLogado() /* && (userBean.getUsuarioLogado() != null) */) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('TrocarSenha').show()");

        } else {

        }
    }

    public String verificarAutoridadeLoginVisualizarCursos() throws Exception {

        if (getUsuarioLogado() != null && getUsuarioLogado().getNivelAcesso() < 2) {

            return "/pages/admin/visualizarcursos_index.xhtml";

        } else {
            return "/pages/admin/result_index.xhtml";
        }
    }

    public void loginRedirect() throws Exception {

        if (getUsuarioLogado() != null) {

            if (usuarioLogado.getNivelAcesso() > 2) {
                FacesContext.getCurrentInstance().getExternalContext().redirect( FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/admin/result_index.jsf");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/usuario/result_index" + usuarioLogado.getSetor()
                        + ".jsf");
            }

        } else {
        }

    }

    public String resultRedirectAdmin() throws Exception {

        if (getUsuarioLogado() != null) {

            if (usuarioLogado.getNivelAcesso() > 2 || usuarioLogado.getNivelAcesso() == 0) {
                return "";
            } else {
                return "/pages/usuario/result_index" + usuarioLogado.getSetor()
                        + ".xhtml";
            }


        } else {
            return "/pages/login_index.xhtml";
        }
    }
    public String resultRedirectComum() throws Exception {

        if (getUsuarioLogado() != null) {

            if (usuarioLogado.getNivelAcesso() > 2) {
                return "/pages/admin/result_index.xhtml";
            } else {
                return "";
            }


        } else {
            return "/pages/login_index.xhtml";
        }
    }

    public String gravar() {
        usuarioDao = new UsuarioDao();
        try {
            if (usuarioDao.Gravar(usuario)) {
                messageSucessoGravar();
                usuario = new Usuario();
                usuario.setLogado(true);
            }
            return "/pages/admin/result_index.xhtml";

        } catch (Exception ex) {
            messageErroCadastro();
            ex.printStackTrace();
            ex.getMessage();
            return null;
        }

    }



    public void messageAlteraSenha() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, sessao,
                        "Sua senha foi alterada com sucesso"));

    }

    public String messageSucessoLogin() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Login",
                        "Seja bem vindo " + sessao));

        if (usuarioLogado.getNivelAcesso() > 2) {
            return "/pages/admin/result_index.xhtml";
        }

        else {
            return "/pages/usuario/result_index" + usuarioLogado.getSetor()
                    + ".xhtml";
        }

    }

    public String loginAtivo() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Ativo",
                        "Sessão ainda ativa para o usuário:  " + sessao));
        // return "result.xhtml";
        if (usuarioLogado.getNivelAcesso() < 4) {
            return "/pages/admin/result_index.xhtml";
        }

        else {
            return "/pages/usuario/result_index" + usuarioLogado.getSetor()
                    + ".xhtml";
        }

    }

    public String reiniciarsessao() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Ativo",
                        "Sessão ativa reiniciada:  " + sessao));
        // return "result.xhtml";
        return "/pages/admin/result_index.xhtml";

    }

    public void messageSucessoGravar() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "gravar",
                        "Cadastro realizado com sucesso, Seja bem vindo "
                                + sessao));

    }

    public void messageErroLogin() {
        FacesContext
                .getCurrentInstance()
                .addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login",
                                "Usuário/Senha incorretos, por favor, tente novamente"));
        // remover sessão do manage bean selecionado
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
        // .remove("userBean");
    }

    public void nenhumUsuario() {
        FacesContext
                .getCurrentInstance()
                .addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro",
                                "Nenhum usuário logado, para realizar alguma operação, efetue seu login"));

    }

    public void autoridadeInsuficiente() {
        FacesContext
                .getCurrentInstance()
                .addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, usuarioLogado
                                .getApelido(),
                                "Seu acesso á esta função não é permitida, portanto acesso negado!"));

    }

    public void messageErroCadastro() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro",
                        "O Cadastro falhou, por favor, tente novamente"));
    }


    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        UsuarioBean.logado = logado;
    }

    public List<SelectItem> getNivelAcessoCadastro() {
        return nivelAcessoCadastro;
    }

    public void setNivelAcessoCadastro(List<SelectItem> nivelAcessoCadastro) {
        this.nivelAcessoCadastro = nivelAcessoCadastro;
    }



    public List<SelectItem> getSetores() {
        return setores;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setSetores(List<SelectItem> setores) {
        this.setores = setores;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Contrato getContratoSelecionado() {
        return contratoSelecionado;
    }

    public void setContratoSelecionado(Contrato contratoSelecionado) {
        this.contratoSelecionado = contratoSelecionado;
    }

    public Mes getMesSelecionado() {
        return mesSelecionado;
    }

    public void setMesSelecionado(Mes mesSelecionado) {
        this.mesSelecionado = mesSelecionado;
    }

    public int getAnoSelecionado() {
        return anoSelecionado;
    }

    public void setAnoSelecionado(int anoSelecionado) {
        this.anoSelecionado = anoSelecionado;
    }
}
