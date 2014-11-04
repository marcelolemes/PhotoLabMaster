package br.com.login.bean.users;

import br.com.login.Dao.UserDao;
import br.com.login.model.Contrato;
import br.com.login.model.Mes;
import br.com.login.model.Metricas;
import br.com.login.model.User;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean implements Serializable {
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
    public UserBean() {

        user = new User();
        user.setLogado(false);

    }

    private User user;
    private User userLogado;
    UserDao userDao;
    private String sessao = new String();


    private static boolean logado = false;

    public User getUser() {
        return user;

    }

    public void setUser(User user) {
        this.user = user;
    }

    public String atualizar() throws Exception {

        userLogado = userDao.atualizar(userLogado);
        // return "result.xhtml";
        return "/pages/result_index.xhtml";
    }

    public String logar() throws Exception {
        if (user.isLogado()) {
            loginAtivo();
            if (userLogado.getNivelAcesso() > 4) {
                return "/pages/admin/result_index.xhtml";
            } else {
                return "/pages/user/result_index" + userLogado.getSetor()
                        + ".xhtml?faces-redirect=true";
            }

        } else {
            userDao = new UserDao();
            if ((userLogado = userDao.testarLogin(user)) != null) {
                System.out.print(" Encontrado ");
                setSessao(user.getApelido());
                user = new User();
                user.setLogado(true);
                return messageSucessoLogin();

            } else {
                System.out.print("Não encontrado");
                user = new User();
                user.setLogado(false);
                messageErroLogin();
            }
            return null;
        }
    }

    public String verificarLogado() throws Exception {

        if (user.isLogado() /* && (userBean.getUserLogado() != null) */) {

            if (userLogado.getNivelAcesso() > 4) {
                return "/pages/admin/result_index.xhtml";
            } else {
                return "/pages/user/result_index" + userLogado.getSetor()
                        + ".xhtml";
            }

        } else {
            return "/pages/login_index.xhtml";
        }
    }

    public void btHome() throws Exception {

        if (user.isLogado() /* && (userBean.getUserLogado() != null) */) {

            if (userLogado.getNivelAcesso() > 4) {
                FacesContext.getCurrentInstance().getExternalContext().redirect( FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/admin/result_index.jsf");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect(  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/user/result_index" + userLogado.getSetor()
                        + ".jsf");
            }

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/login_index.jsf");
        }
    }
    public void btProducao() throws Exception {

        if (user.isLogado() /* && (userBean.getUserLogado() != null) */) {

            FacesContext.getCurrentInstance().getExternalContext().redirect(  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/user/relatorio_user.jsf");

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/login_index.jsf");
        }
    }




    public void btTrocarSenha() throws Exception {

        if (user.isLogado() /* && (userBean.getUserLogado() != null) */) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('TrocarSenha').show()");

        } else {

        }
    }

    public String verificarAutoridadeLoginVisualizarCursos() throws Exception {

        if (getUserLogado() != null && getUserLogado().getNivelAcesso() < 2) {

            return "/pages/admin/visualizarcursos_index.xhtml";

        } else {
            return "/pages/admin/result_index.xhtml";
        }
    }

    public void loginRedirect() throws Exception {

        if (getUserLogado() != null) {

            if (userLogado.getNivelAcesso() > 4) {
                FacesContext.getCurrentInstance().getExternalContext().redirect( FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/admin/result_index.jsf");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/user/result_index" + userLogado.getSetor()
                        + ".jsf");
            }

        } else {
        }

    }

    public String resultRedirectAdmin() throws Exception {

        if (getUserLogado() != null) {

            if (userLogado.getNivelAcesso() > 4) {
                return "";
            } else {
                return "/pages/user/result_index" + userLogado.getSetor()
                        + ".xhtml";
            }


        } else {
            return "/pages/login_index.xhtml";
        }
    }
    public String resultRedirectComum() throws Exception {

        if (getUserLogado() != null) {

            if (userLogado.getNivelAcesso() > 4) {
                return "/pages/admin/result_index.xhtml";
            } else {
                return "";
            }


        } else {
            return "/pages/login_index.xhtml";
        }
    }

    public String gravar() {
        userDao = new UserDao();
        try {
            if (userDao.Gravar(user)) {
                messageSucessoGravar();
                user = new User();
                user.setLogado(true);
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

        if (userLogado.getNivelAcesso() > 4) {
            return "/pages/admin/result_index.xhtml";
        }

        else {
            return "/pages/user/result_index" + userLogado.getSetor()
                    + ".xhtml";
        }

    }

    public String loginAtivo() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Ativo",
                        "Sessão ainda ativa para o usuário:  " + sessao));
        // return "result.xhtml";
        if (userLogado.getNivelAcesso() < 4) {
            return "/pages/admin/result_index.xhtml";
        }

        else {
            return "/pages/user/result_index" + userLogado.getSetor()
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
                        new FacesMessage(FacesMessage.SEVERITY_WARN, userLogado
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

    public User getUserLogado() {
        return userLogado;
    }

    public void setUserLogado(User userLogado) {
        this.userLogado = userLogado;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        UserBean.logado = logado;
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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setSetores(List<SelectItem> setores) {
        this.setores = setores;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
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
