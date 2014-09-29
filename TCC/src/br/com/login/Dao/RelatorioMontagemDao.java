package br.com.login.Dao;

import br.com.login.model.RelatorioMontagem;
import br.com.login.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by marcelo on 28/09/14.
 */
public class RelatorioMontagemDao  {
    RelatorioMontagem relatorio = new RelatorioMontagem();

    public void salvarRelatorio(RelatorioMontagem relatorio) throws Exception{

        Session sessao = HibernateUtil.getSession();
        org.hibernate.Transaction transacao = sessao.beginTransaction();
        try {

        }
        catch (Exception e)
        {
            sessao.save(relatorio);
            transacao.commit();

        }

        sessao.close();
    }
}
