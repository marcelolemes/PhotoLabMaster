package br.com.login.Dao;

import br.com.login.model.Album;
import br.com.login.model.Contrato;
import br.com.login.model.Relatorio;
import br.com.login.model.User;
import br.com.login.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            if (relatorio.getDataFinal()!=null) {

                relatorio.setTempoOperacao(relatorio.getDataFinal().getTime()- relatorio.getDataInicial().getTime());
                if (relatorio.getTempoOperacao() > 50400000)
                {
                    relatorio.setTempoOperacao(relatorio.getTempoOperacao() - 50400000);
                }

                System.out.println("Conversão "+msToHourSecond((int)relatorio.getTempoOperacao()));

            }
            sessao.saveOrUpdate(relatorio);
            transacao.commit();
        }
        catch (Exception e)
        {
            System.out.println("Relatório não salvo");


        }

        sessao.close();
    }
    public static String msToHourSecond( int ms ) {
        Calendar c = Calendar.getInstance();
        c.clear(Calendar.HOUR_OF_DAY);
        c.clear(Calendar.HOUR);
        c.clear(Calendar.AM_PM);
        c.clear(Calendar.MINUTE);
        c.clear(Calendar.SECOND);
        c.clear(Calendar.MILLISECOND);
        c.add( Calendar.MILLISECOND , ms );
        return new SimpleDateFormat( "HH:mm:ss" ).format( c.getTime() );
    }

    public List<Relatorio> ListarAlbunsFunc(User user) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Relatorio.class);
        criteria.add(Restrictions.eq("funcionario",user));
        criteria.addOrder(Order.desc("dataFinal"));
        //criteria.add(Restrictions.eq("funcionario", user));
        System.out.println("Funcionário aqui: "+user.getApelido());
        List<Relatorio> listaRetorno = criteria.list();
        System.out.println("Contagem: "+criteria.list().size());
        sessao.close();
        return listaRetorno;
    }

    public Relatorio encontrarRelatorio(User user,Album album) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Relatorio.class);
        criteria.add(Restrictions.eq("funcionario", user));
        criteria.add(Restrictions.eq("album", album));
        criteria.setMaxResults(1);
        Relatorio retorno = (Relatorio) criteria.uniqueResult();
        sessao.close();
        return retorno;
    }

    public long contarAlbunsHoje(User user) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.AM_PM);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Relatorio.class).setProjection(Projections.rowCount());
        criteria.add(Restrictions.ge("dataFinal", cal.getTime())).add(Restrictions.eq("funcionario", user));
        long retorno = (Long) criteria.uniqueResult();
        System.out.println("Contagem: "+retorno);
        System.out.println("Date aqui: "+cal.getTime());
        sessao.close();
        return retorno;
    }

    public long contarFotosHoje(User user) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.AM_PM);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        java.sql.Date date = new java.sql.Date(new Date(System.currentTimeMillis()).getTime());
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Relatorio.class).setProjection(Projections.sum("fotos"));
        criteria.add(Restrictions.ge("dataFinal", cal.getTime())).add(Restrictions.eq("funcionario", user));
        long retorno = (Long) criteria.uniqueResult();
        System.out.println("Contagem: "+retorno);
        System.out.println("Date aqui: "+cal.getTime());
        sessao.close();
        return retorno;
    }


    public List<Relatorio> ListarAlbunsHoje(User user) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.AM_PM);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        java.sql.Date date = new java.sql.Date(new Date(System.currentTimeMillis()).getTime());
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Relatorio.class);
        criteria.add(Restrictions.ge("dataFinal", cal.getTime())).add(Restrictions.eq("funcionario", user));
        criteria.addOrder(Order.desc("dataFinal"));
        List<Relatorio> listaRetorno = criteria.list();
        System.out.println("Date aqui: "+cal.getTime());
        sessao.close();
        return listaRetorno;
    }
    public List<Relatorio> ListarIntervalo(User user,Date date1, Date date2) throws Exception {
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

        java.sql.Date date = new java.sql.Date(new Date(System.currentTimeMillis()).getTime());
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Relatorio.class);
        criteria.add(Restrictions.ge("dataFinal", calendar1.getTime())).add(Restrictions.eq("funcionario", user)).add(Restrictions.le("dataFinal",calendar2.getTime()));
        criteria.addOrder(Order.asc("dataFinal"));
        List<Relatorio> listaRetorno = criteria.list();
        System.out.println("Date 1 aqui: "+calendar1.getTime());
        System.out.println("Date 2 aqui: "+calendar2.getTime());
        sessao.close();
        return listaRetorno;
    }

    public List<Relatorio> ListarAlbunsMes(User user) throws Exception {
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

          java.sql.Date date = new java.sql.Date(new Date(System.currentTimeMillis()).getTime());
          Session sessao = HibernateUtil.getSession();
          Criteria criteria = sessao.createCriteria(Relatorio.class);
          criteria.add(Restrictions.ge("dataFinal", calendar1.getTime())).add(Restrictions.eq("funcionario", user)).add(Restrictions.lt("dataFinal",calendar2.getTime()));
          criteria.addOrder(Order.asc("dataFinal"));
          List<Relatorio> listaRetorno = criteria.list();
          System.out.println("Date 1 aqui: "+calendar1.getTime());
          System.out.println("Date 2 aqui: "+calendar2.getTime());
          sessao.close();
          return listaRetorno;
      }

    public long AlbunsRestantesMontagem(Contrato contrato) throws Exception {

        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Album.class).setProjection(Projections.rowCount());
        criteria.add(Restrictions.eq("contrato",contrato));
        System.out.println("Contrato para contagem : "+contrato.getNumeroContrato());
        criteria.add(Restrictions.le("status",12));
        long retorno = (Long) criteria.uniqueResult();
        System.out.println("Contagem restantes: "+retorno);

        sessao.close();
        return retorno;


    }


}
