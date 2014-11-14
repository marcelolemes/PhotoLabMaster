package br.com.photolab.dao.regraDao;

import br.com.photolab.bean.usuario.UsuarioBean;
import br.com.photolab.dao.modeloDao.AlbumDao;
import br.com.photolab.dao.modeloDao.ContratoDao;
import br.com.photolab.modelo.Album;
import br.com.photolab.modelo.Contrato;
import br.com.photolab.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by marcelo on 27/09/2014.
 */
@ManagedBean
public class RegraPatraoDao implements Serializable {
    public List<Contrato> ListarContratoMes() throws Exception {
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
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.gt("dataEntrega", calendar1.getTime())).add(Restrictions.lt("dataEntrega",calendar2.getTime()));
        List<Contrato> listaRetorno = criteria.list();
        sessao.close();
        return listaRetorno;
    }

    public long ContarFotosMes() throws Exception {
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
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.gt("dataEntrega", calendar1.getTime())).add(Restrictions.lt("dataEntrega",calendar2.getTime())).setProjection(Projections.sum("qtdFotos"));
        long retorno = (Long) criteria.uniqueResult();
        sessao.close();
        return retorno;
    }

    public long ContarAlbumMes() throws Exception {
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
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.gt("dataEntrega", calendar1.getTime())).add(Restrictions.lt("dataEntrega",calendar2.getTime())).setProjection(Projections.sum("qtdAlbum"));
        long retorno = (Long) criteria.uniqueResult();
        sessao.close();
        return retorno;
    }
    public List<Contrato> ListarContratoMes(Calendar calendar1,Calendar calendar2) throws Exception {
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.gt("dataEntrega", calendar1.getTime())).add(Restrictions.lt("dataEntrega",calendar2.getTime()));
        List<Contrato> listaRetorno = criteria.list();
        System.out.println("Date 1 aqui: "+calendar1.getTime());
        System.out.println("Date 2 aqui: "+calendar2.getTime());
        sessao.close();
        return listaRetorno;
    }

    public long ContarFotosMes(Calendar calendar1,Calendar calendar2) throws Exception {
        long retorno  =0;
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.gt("dataEntrega", calendar1.getTime())).add(Restrictions.lt("dataEntrega",calendar2.getTime())).setProjection(Projections.sum("qtdFotos"));
        if(criteria.uniqueResult()!=null){
            retorno = (Long) criteria.uniqueResult();
        }
        System.out.println("Date 1 aqui: "+calendar1.getTime());
        System.out.println("Date 2 aqui: "+calendar2.getTime());
        sessao.close();
        return retorno;
    }

    public long ContarAlbumMes(Calendar calendar1,Calendar calendar2) throws Exception {

        long retorno =0;
        Session sessao = HibernateUtil.getSession();
        Criteria criteria = sessao.createCriteria(Contrato.class);
        criteria.add(Restrictions.gt("dataEntrega", calendar1.getTime())).add(Restrictions.lt("dataEntrega",calendar2.getTime())).setProjection(Projections.sum("qtdAlbum"));
        if(criteria.uniqueResult()!=null){
            retorno = (Long) criteria.uniqueResult();
        }

        System.out.println("Date 1 aqui: "+calendar1.getTime());
        System.out.println("Date 2 aqui: "+calendar2.getTime());
        sessao.close();

        return retorno;
    }

}
