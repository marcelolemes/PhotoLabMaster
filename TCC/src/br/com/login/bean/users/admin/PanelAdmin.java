package br.com.login.bean.users.admin;

import br.com.login.Dao.UserDao;
import br.com.login.bean.users.UserBean;
import br.com.login.model.Contrato;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class PanelAdmin implements Serializable {

    /**
     *
     */

    UserDao userDao = new UserDao();
    private String senhaTemporaria;
    private String senhaTemporaria2;
    private static final long serialVersionUID = -8922198411434111521L;
    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    @PostConstruct
    public void init() {

    }

    public void mudarSenha() throws Exception {
        System.out.println("Primeira senha " + senhaTemporaria);
        System.out.println("Segunda senha " + senhaTemporaria2);

        if (senhaTemporaria != null && senhaTemporaria2 != null
                && senhaTemporaria.equals(senhaTemporaria2)) {
            userBean.getUserLogado().setSenha(senhaTemporaria2);
            userDao.Update(userBean.getUserLogado());
            userBean.messageAlteraSenha();
        } else {

            FacesContext
                    .getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    userBean.getUserLogado().getApelido(),
                                    "Senha não alterada, digite a nova senha nos campos indicados"));
        }

    }

    public String btVisualizarCursos() {

        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getNivelAcesso() < 4) {

                userBean.autoridadeInsuficiente();

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/visualizarcursos_index.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }
    public String btVisualizarFichas() {

        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getNivelAcesso() < 4) {

                userBean.autoridadeInsuficiente();

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/visualizafichas_index.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btCadastrarFicha(){


        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getNivelAcesso() < 4) {

                userBean.autoridadeInsuficiente();

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/cadastro/cadastro_ficha.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btCadastro() {

        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getNivelAcesso() < 4) {

                userBean.autoridadeInsuficiente();
                // return "result.xhtml";

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/cadastro/cadastro_index.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btCadastrarCursos() {

        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getSetor() != 0) {

                userBean.autoridadeInsuficiente();

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {
                System.out.println("Chegou no botão de cadastro!!!");

                return "/pages/admin/cadastro/cadastrarcurso_index.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btCadastrarClientes() {

        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getSetor() != 0) {

                userBean.autoridadeInsuficiente();

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/cadastro/cadastro_cliente.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btCadastrarAlbum() {

        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getNivelAcesso() < 4) {

                userBean.autoridadeInsuficiente();

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/cadastro/cadastro_album_index.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btListarAlbuns() {

        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getNivelAcesso() < 4) {

                userBean.autoridadeInsuficiente();

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/visualizaralbuns_index.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btListarAlbunsCurso() {

        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getNivelAcesso() < 4) {

                userBean.autoridadeInsuficiente();

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/visualizaralbuns_index.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btListarUsers() {

        if (userBean.getUser().isLogado()) {
            if (userBean.getUserLogado().getNivelAcesso() < 4) {

                userBean.autoridadeInsuficiente();
                // return "result.xhtml";

                if (userBean.getUserLogado().getNivelAcesso() > 4) {
                    return "/pages/admin/result_index.xhtml";
                } else {
                    return "/pages/user/result_index"
                            + userBean.getUserLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/usuarios_cadastrados_index.xhtml";
            }

        } else {
            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public void fecharSessao() {
        // remover sessão do manage bean selecionado
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("userBean");
		/*
		 * userBean.setUserLogado(null); userBean.setLogado(false);
		 */
        userBean.setLogado(false);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
    }

    public String sairSessao() throws Exception {

        if (userBean.getUser().isLogado()) {
            try {
                userBean.setLogado(false);

                userDao.gravarTimestamp(userBean.getUserLogado());

				/*
				 * userBean.setUserLogado(null);
				 */

                // testando
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().invalidateSession();

                // remover sessão do manage bean selecionado
				/*
				 * FacesContext.getCurrentInstance().getExternalContext()
				 * .getSessionMap().remove("userBean");
				 */
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Logoff",
                                "Sessão encerrada"));

            } catch (Exception ex) {
                // TODO: handle exception
            }

            return "/pages/login_index.xhtml";
        } else {

            userBean.nenhumUsuario();
            return "/pages/login_index.xhtml";
        }

    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String getSenhaTemporaria() {
        return senhaTemporaria;
    }

    public void setSenhaTemporaria(String senhaTemporaria) {
        this.senhaTemporaria = senhaTemporaria;
    }

    public String getSenhaTemporaria2() {
        return senhaTemporaria2;
    }

    public void setSenhaTemporaria2(String senhaTemporaria2) {
        this.senhaTemporaria2 = senhaTemporaria2;
    }

}
