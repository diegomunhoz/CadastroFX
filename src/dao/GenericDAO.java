package dao;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public abstract class GenericDAO {

    public void excluir(Object object) throws SQLException {
        Session sessao = null;
        Transaction transacao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.delete(object);
            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
        } finally {
            sessao.close();
        }
    }

    public void salvar(Object object) throws SQLException {
        Session sessao = null;
        Transaction transacao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();

            sessao.saveOrUpdate(object);
            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
            sessao.close();
        } finally {
            sessao.close();
        }
    }
}