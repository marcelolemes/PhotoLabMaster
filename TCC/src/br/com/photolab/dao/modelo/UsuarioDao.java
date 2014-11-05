package br.com.photolab.dao.modelo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.photolab.bean.usuarios.usuario.UsuarioBean;
import br.com.photolab.modelo.Usuario;
import br.com.photolab.util.HibernateUtil;

@ViewScoped
public class UsuarioDao implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Usuario atualizar(Usuario usuario) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("apelido", usuario.getApelido()));
        sessao.close();
        return (Usuario) criteria.uniqueResult();

    }

    public void gravarTimestamp(Usuario usuario) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("apelido", usuario.getApelido()));
        usuario.setUltimoacesso(new Timestamp(new Date(System.currentTimeMillis())
                .getTime()));
        usuario.setLogado(false);
        sessao.update(usuario);
        transacao.commit();
        sessao.close();

    }

    public Usuario testarLogin(Usuario usuario) throws Exception {
        Usuario resultado;
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("apelido", usuario.getApelido()));
        resultado = (Usuario) criteria.uniqueResult();
        if (resultado != null) {
            if (resultado.getSenha() != null) {
                if (resultado.getSenha().equals(usuario.getSenha())) {
                    if (resultado.isLogado()) {
                        new UsuarioBean().reiniciarsessao();
                        sessao.close();
                        return resultado;
                    } else {
                        resultado.setLogado(true);
                        sessao.update(resultado);
                        transacao.commit();
                        sessao.close();
                        return resultado;
                    }

                } else {
                    sessao.close();
                    return null;
                }

            } else {
                sessao.close();
                return null;
            }
        } else {
            sessao.close();
            return null;
        }

    }

    public List<Usuario> ListarUsers() throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        List<Usuario> listaRetorno = criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public List<Usuario> ListarUsersMontagem() throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("setor",3));
        List<Usuario> listaRetorno = criteria.list();
        System.out.println(criteria.list().size()+" usuários da montagem");
        try{
            sessao.close();
        }
        catch (Exception e){

        }


        return listaRetorno;
    }

    public List<Usuario> ListarUsersTratamento() throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("setor",2));
        List<Usuario> listaRetorno = criteria.list();
        System.out.println(criteria.list().size()+" usuários do tratamento");
        try{
            sessao.close();
        }
        catch (Exception e){

        }


        return listaRetorno;
    }


    public boolean Update(Usuario usuario) throws Exception {
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        sessao.update(usuario);
        transacao.commit();
        sessao.close();
        return false;
    }

    public boolean Gravar(Usuario usuario) throws Exception {

        usuario.setApelido(usuario.getApelido().toLowerCase());
        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("apelido", usuario.getApelido()));
        List<Usuario> listaRetorno = criteria.list();

        if (usuario.getApelido().length() <= 3) {

            FacesContext
                    .getCurrentInstance()
                    .addMessage(
                            null,
                            new FacesMessage(
                                    FacesMessage.SEVERITY_FATAL,
                                    "Cadastro",
                                    "Não é possível salvar, o apelido é muito curto, insira um nome com ao menos 4 caracteres"));
            sessao.close();
            return false;
        } else {
            if (usuario.getSenha().length() <= 3) {

                FacesContext
                        .getCurrentInstance()
                        .addMessage(
                                null,
                                new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                        "Cadastro",
                                        "Não é possível salvar, a senha é muito curta, insira ao menos 4 caracteres"));
                sessao.close();
                return false;
            } else {

                if ((listaRetorno.size() > 0)) {
                    System.out.println("Tamanho " + criteria.list().size());

                    FacesContext.getCurrentInstance().addMessage(
                            null,
                            new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                    "Cadastro",
                                    "Não é possível salvar, o nome já existe"));
                    sessao.close();
                    return false;
                } else {
                    usuario.setLogado(false);
                    sessao.saveOrUpdate(usuario);
                    transacao.commit();
                    sessao.close();
                    return true;
                }
            }
        }
    }
}
