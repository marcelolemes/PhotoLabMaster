package br.com.login.Dao;

import br.com.login.model.*;
import br.com.login.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 28/09/14.
 *
 *
 * Implementar o salvamento de relatório diário
 */
public class RelatorioDiarioDao {
    RelatorioDiario relatorio = new RelatorioDiario();

    public void salvarRelatorio(RelatorioDiario relatorio) throws Exception{

        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        try {
            sessao.saveOrUpdate(relatorio);
            transacao.commit();
            System.out.println("Salvo");
        }
        catch (Exception e)
        {
            System.out.println("Relatório não salvo");


        }

        sessao.close();
    }


    public RelatorioDiario encontrarRelatorio(User user,Date data) throws Exception {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(data);
        calendar1.clear(Calendar.HOUR_OF_DAY);
        calendar1.clear(Calendar.HOUR);
        calendar1.clear(Calendar.AM_PM);
        calendar1.clear(Calendar.MINUTE);
        calendar1.clear(Calendar.SECOND);
        calendar1.clear(Calendar.MILLISECOND);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(data);
        calendar2.add(Calendar.DAY_OF_MONTH,1);
        calendar2.clear(Calendar.HOUR_OF_DAY);
        calendar2.clear(Calendar.HOUR);
        calendar2.clear(Calendar.AM_PM);
        calendar2.clear(Calendar.MINUTE);
        calendar2.clear(Calendar.SECOND);
        calendar2.clear(Calendar.MILLISECOND);

        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(RelatorioDiario.class);
        criteria.add(Restrictions.eq("funcionario", user));
        criteria.add(Restrictions.ge("dataRelatorio", calendar1.getTime())).add(Restrictions.lt("dataRelatorio",calendar2.getTime()));
        criteria.setMaxResults(1);
        System.out.println("Hoje aqui: "+calendar1.getTime());
        System.out.println("Amanhã aqui: "+calendar2.getTime());
        RelatorioDiario retorno = (RelatorioDiario) criteria.uniqueResult();
        sessao.close();
        return retorno;
    }

    public List<RelatorioDiario> ListarIntervalo(User user,Date date1, Date date2) throws Exception {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.setTime(date1);
        calendar2.setTime(date2);

        calendar1.clear(Calendar.HOUR_OF_DAY);
        calendar1.clear(Calendar.HOUR);
        calendar1.clear(Calendar.AM_PM);
        calendar1.clear(Calendar.MINUTE);
        calendar1.clear(Calendar.SECOND);
        calendar1.clear(Calendar.MILLISECOND);

        calendar2.clear(Calendar.HOUR_OF_DAY);
        calendar2.clear(Calendar.HOUR);
        calendar2.clear(Calendar.AM_PM);
        calendar2.clear(Calendar.MINUTE);
        calendar2.clear(Calendar.SECOND);
        calendar2.clear(Calendar.MILLISECOND);

        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(RelatorioDiario.class);
        criteria.add(Restrictions.ge("dataRelatorio", calendar1.getTime())).add(Restrictions.eq("funcionario", user)).add(Restrictions.le("dataRelatorio",calendar2.getTime()));
        criteria.addOrder(Order.asc("dataRelatorio"));
        List<RelatorioDiario> listaRetorno = criteria.list();
        System.out.println("Data 1 aqui: "+calendar1.getTime());
        System.out.println("Data 2 aqui: "+calendar2.getTime());
        sessao.close();
        return listaRetorno;
    }


    public List<RelatorioDiario> ListarMes(User user) throws Exception {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        Date date1 = new Date();
        date1.getTime();
        calendar1.setTime(date1);
        calendar2.setTime(date1);


        calendar1.clear(Calendar.DAY_OF_YEAR);
        calendar1.clear(Calendar.DAY_OF_WEEK);
        calendar1.clear(Calendar.DAY_OF_MONTH);
        calendar1.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
        calendar1.clear(Calendar.HOUR_OF_DAY);
        calendar1.clear(Calendar.WEEK_OF_YEAR);
        calendar1.clear(Calendar.WEEK_OF_MONTH);
        calendar1.clear(Calendar.HOUR);
        calendar1.clear(Calendar.AM_PM);
        calendar1.clear(Calendar.MINUTE);
        calendar1.clear(Calendar.SECOND);
        calendar1.clear(Calendar.MILLISECOND);


        calendar2.clear(Calendar.DAY_OF_YEAR);
        calendar2.clear(Calendar.DAY_OF_WEEK);
        calendar2.clear(Calendar.DAY_OF_MONTH);
        calendar2.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
        calendar2.clear(Calendar.WEEK_OF_YEAR);
        calendar2.clear(Calendar.WEEK_OF_MONTH);
        calendar2.clear(Calendar.HOUR_OF_DAY);
        calendar2.clear(Calendar.HOUR);
        calendar2.clear(Calendar.AM_PM);
        calendar2.clear(Calendar.MINUTE);
        calendar2.clear(Calendar.SECOND);
        calendar2.clear(Calendar.MILLISECOND);
        calendar2.add(Calendar.MONTH,1);

        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(RelatorioDiario.class);
        criteria.add(Restrictions.gt("dataRelatorio", calendar1.getTime())).add(Restrictions.eq("funcionario", user)).add(Restrictions.lt("dataRelatorio",calendar2.getTime()));
        criteria.addOrder(Order.asc("dataRelatorio"));
        List<RelatorioDiario> listaRetorno = criteria.list();
        System.out.println("Mes 1 aqui: "+calendar1.getTime());
        System.out.println("Mes 2 aqui: "+calendar2.getTime());
        System.out.println("User : "+user.getApelido()+" dias "+listaRetorno.size());
        sessao.close();
        return listaRetorno;
    }

    public double ListarMediaMes(User user) throws Exception {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        Date date1 = new Date();
        date1.getTime();
        calendar1.setTime(date1);
        calendar2.setTime(date1);


        calendar1.clear(Calendar.DAY_OF_YEAR);
        calendar1.clear(Calendar.DAY_OF_WEEK);
        calendar1.clear(Calendar.DAY_OF_MONTH);
        calendar1.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
        calendar1.clear(Calendar.HOUR_OF_DAY);
        calendar1.clear(Calendar.WEEK_OF_YEAR);
        calendar1.clear(Calendar.WEEK_OF_MONTH);
        calendar1.clear(Calendar.HOUR);
        calendar1.clear(Calendar.AM_PM);
        calendar1.clear(Calendar.MINUTE);
        calendar1.clear(Calendar.SECOND);
        calendar1.clear(Calendar.MILLISECOND);


        calendar2.clear(Calendar.DAY_OF_YEAR);
        calendar2.clear(Calendar.DAY_OF_WEEK);
        calendar2.clear(Calendar.DAY_OF_MONTH);
        calendar2.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
        calendar2.clear(Calendar.WEEK_OF_YEAR);
        calendar2.clear(Calendar.WEEK_OF_MONTH);
        calendar2.clear(Calendar.HOUR_OF_DAY);
        calendar2.clear(Calendar.HOUR);
        calendar2.clear(Calendar.AM_PM);
        calendar2.clear(Calendar.MINUTE);
        calendar2.clear(Calendar.SECOND);
        calendar2.clear(Calendar.MILLISECOND);
        calendar2.add(Calendar.MONTH,1);

        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(RelatorioDiario.class);
        criteria.add(Restrictions.gt("dataRelatorio", calendar1.getTime())).add(Restrictions.eq("funcionario", user)).add(Restrictions.lt("dataRelatorio",calendar2.getTime())).setProjection(Projections.avg("fotos"));;
        criteria.addOrder(Order.asc("dataRelatorio"));
        double listaRetorno = (Double)criteria.uniqueResult();
        System.out.println("Mes 1 aqui: "+calendar1.getTime());
        System.out.println("Mes 2 aqui: "+calendar2.getTime());

        sessao.close();
        return listaRetorno;
    }

    public long ListarTotalMes(User user) throws Exception {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        Date date1 = new Date();
        date1.getTime();
        calendar1.setTime(date1);
        calendar2.setTime(date1);


        calendar1.clear(Calendar.DAY_OF_YEAR);
        calendar1.clear(Calendar.DAY_OF_WEEK);
        calendar1.clear(Calendar.DAY_OF_MONTH);
        calendar1.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
        calendar1.clear(Calendar.HOUR_OF_DAY);
        calendar1.clear(Calendar.WEEK_OF_YEAR);
        calendar1.clear(Calendar.WEEK_OF_MONTH);
        calendar1.clear(Calendar.HOUR);
        calendar1.clear(Calendar.AM_PM);
        calendar1.clear(Calendar.MINUTE);
        calendar1.clear(Calendar.SECOND);
        calendar1.clear(Calendar.MILLISECOND);


        calendar2.clear(Calendar.DAY_OF_YEAR);
        calendar2.clear(Calendar.DAY_OF_WEEK);
        calendar2.clear(Calendar.DAY_OF_MONTH);
        calendar2.clear(Calendar.DAY_OF_WEEK_IN_MONTH);
        calendar2.clear(Calendar.WEEK_OF_YEAR);
        calendar2.clear(Calendar.WEEK_OF_MONTH);
        calendar2.clear(Calendar.HOUR_OF_DAY);
        calendar2.clear(Calendar.HOUR);
        calendar2.clear(Calendar.AM_PM);
        calendar2.clear(Calendar.MINUTE);
        calendar2.clear(Calendar.SECOND);
        calendar2.clear(Calendar.MILLISECOND);
        calendar2.add(Calendar.MONTH,1);

        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(RelatorioDiario.class);
        criteria.add(Restrictions.gt("dataRelatorio", calendar1.getTime())).add(Restrictions.eq("funcionario", user)).add(Restrictions.lt("dataRelatorio",calendar2.getTime())).setProjection(Projections.sum("fotos"));;
        criteria.addOrder(Order.asc("dataRelatorio"));
        long listaRetorno = (Long)criteria.uniqueResult();
        System.out.println("Mes 1 aqui: "+calendar1.getTime());
        System.out.println("Mes 2 aqui: "+calendar2.getTime());

        sessao.close();
        return listaRetorno;
    }


}
