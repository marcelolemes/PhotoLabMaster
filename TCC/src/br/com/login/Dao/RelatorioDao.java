package br.com.login.Dao;

import br.com.login.model.Relatorio;
import br.com.login.model.User;
import br.com.login.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 28/09/14.
 */
public class RelatorioDao {
    Relatorio relatorio = new Relatorio();

    public void salvarRelatorio(Relatorio relatorio) throws Exception{

        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        try {
            sessao.save(relatorio);
            transacao.commit();
        }
        catch (Exception e)
        {
            System.out.println("Relatório não salvo");


        }

        sessao.close();
    }

    public List<Relatorio> ListarAlbunsFunc(User user) throws Exception {
            Session sessao = HibernateUtil.getSession();
            Criteria criteria = sessao.createCriteria(Relatorio.class);
        criteria.add(Restrictions.eq("funcionario",user));
        criteria.addOrder(Order.desc("dataOperacao"));
        //criteria.add(Restrictions.eq("funcionario", user));
        System.out.println("Funcionário aqui: "+user.getApelido());
        List<Relatorio> listaRetorno = criteria.list();
        System.out.println("Contagem: "+criteria.list().size());
            sessao.close();
            return listaRetorno;
        }

    public long ListarAlbunsHoje(User user) throws Exception {
               Session sessao = HibernateUtil.getSession();
               Criteria criteria = sessao.createCriteria(Relatorio.class).setProjection(Projections.rowCount());
        criteria.add(Restrictions.eq("funcionario",user)).add(Restrictions.eq("dataOperacao",new Timestamp(new Date().getTime())));

        long retorno = (Long) criteria.uniqueResult();
           System.out.println("Contagem: "+retorno);
               sessao.close();
               return retorno;
           }
}
