package br.com.photolab.bean.usuario;

import br.com.photolab.dao.modeloDao.UsuarioDao;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class PaginaPrincipalBean implements Serializable {

    /**
     *
     */

    UsuarioDao usuarioDao = new UsuarioDao();
    private String senhaTemporaria;
    private String senhaTemporaria2;
    private static final long serialVersionUID = -8922198411434111521L;
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;

    @PostConstruct
    public void init() {

    }

    public void mudarSenha() throws Exception {
        System.out.println("Primeira senha " + senhaTemporaria);
        System.out.println("Segunda senha " + senhaTemporaria2);

        if (senhaTemporaria != null && senhaTemporaria2 != null
                && senhaTemporaria.equals(senhaTemporaria2)) {
            usuarioBean.getUsuarioLogado().setSenha(senhaTemporaria2);
            usuarioDao.Update(usuarioBean.getUsuarioLogado());
            usuarioBean.messageAlteraSenha();
        } else {

            FacesContext
                    .getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    usuarioBean.getUsuarioLogado().getApelido(),
                                    "Senha não alterada, digite a nova senha nos campos indicados"));
        }

    }

    public String btVisualizarCursos() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getNivelAcesso() <= 2) {

                usuarioBean.autoridadeInsuficiente();

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/conteudo/visualizarcursos_index.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }
    public String btVisualizarFichas() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getNivelAcesso() <= 2) {

                usuarioBean.autoridadeInsuficiente();

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"+ usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/conteudo/visualizafichas_index.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }


    public void btGraficoProducaoMensalMontagem() throws Exception {

        if (usuarioBean.getUsuario().isLogado() /* && (usuarioBean.getUsuarioLogado() != null) */) {

            FacesContext.getCurrentInstance().getExternalContext().redirect(  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/usuario/producao_anual_montagem.jsf");

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/login_index.jsf");
        }
    }

    public void btGraficoProducaoMensalTratamento() throws Exception {

        if (usuarioBean.getUsuario().isLogado() /* && (usuarioBean.getUsuarioLogado() != null) */) {

            FacesContext.getCurrentInstance().getExternalContext().redirect(  FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/pages/usuario/producao_anual_tratamento.jsf");

        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/pages/login_index.jsf");
        }
    }

    public String btCadastrarFicha(){


        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getNivelAcesso() <= 2) {

                usuarioBean.autoridadeInsuficiente();

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/cadastro/cadastro_ficha.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btCadastro() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getNivelAcesso() <= 2) {

                usuarioBean.autoridadeInsuficiente();
                // return "result.xhtml";

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/cadastro/cadastro_index.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btCadastrarCursos() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getSetor() != 0) {

                usuarioBean.autoridadeInsuficiente();

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {
                System.out.println("Chegou no botão de cadastro!!!");

                return "/pages/admin/cadastro/cadastrarcurso_index.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btCadastrarClientes() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getSetor() != 0) {

                usuarioBean.autoridadeInsuficiente();

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/cadastro/cadastro_cliente.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btCadastrarAlbum() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getNivelAcesso()  <= 2) {

                usuarioBean.autoridadeInsuficiente();

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/cadastro/cadastro_album_index.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btListarAlbuns() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getNivelAcesso()  <= 2) {

                usuarioBean.autoridadeInsuficiente();

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/conteudo/visualizaralbuns_index.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public String btListarAlbunsCurso() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getNivelAcesso()  <= 2) {

                usuarioBean.autoridadeInsuficiente();

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/conteudo/visualizaralbuns_index.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }


    public String btListarAlbunsImpressao() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getSetor()  == 4) {

                return "/pages/admin/conteudo/visualizaralbuns_impressao.xhtml";
            } else
            {
                usuarioBean.autoridadeInsuficiente();
                System.out.println("Setor do usuario "+usuarioBean.getUsuarioLogado().getSetor());

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }


    public String btListarUsers() {

        if (usuarioBean.getUsuario().isLogado()) {
            if (usuarioBean.getUsuarioLogado().getNivelAcesso() <= 2) {

                usuarioBean.autoridadeInsuficiente();
                // return "result.xhtml";

                if (usuarioBean.getUsuarioLogado().getNivelAcesso() > 2) {
                    return "/pages/admin/conteudo/pagina_inicial_admin.xhtml";
                } else {
                    return "/pages/usuario/pagina_inicial_setor_"
                            + usuarioBean.getUsuarioLogado().getSetor() + ".xhtml";
                }
            } else {

                return "/pages/admin/conteudo/usuarios_cadastrados_index.xhtml";
            }

        } else {
            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";

        }

    }

    public void fecharSessao() {
        // remover sessão do manage bean selecionado
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioBean");
		/*
		 * usuarioBean.setUsuarioLogado(null); usuarioBean.setLogado(false);
		 */
        usuarioBean.setLogado(false);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
    }

    public String sairSessao() throws Exception {

        if (usuarioBean.getUsuario().isLogado()) {
            try {
                usuarioBean.setLogado(false);

                usuarioDao.gravarTimestamp(usuarioBean.getUsuarioLogado());

				/*
				 * usuarioBean.setUsuarioLogado(null);
				 */

                // testando
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().invalidateSession();

                // remover sessão do manage bean selecionado
				/*
				 * FacesContext.getCurrentInstance().getExternalContext()
				 * .getSessionMap().remove("usuarioBean");
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

            usuarioBean.nenhumUsuario();
            return "/pages/login_index.xhtml";
        }

    }

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
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
