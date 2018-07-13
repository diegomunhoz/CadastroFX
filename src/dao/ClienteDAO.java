package dao;

import java.util.ArrayList;
import model.Cliente;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class ClienteDAO extends GenericDAO {

    public Cliente buscarPorCodigo(Long codigo) throws Exception {
        Session sessao = null;
        Cliente cliente = null;
        sessao = HibernateUtil.getSessionFactory().openSession();
        cliente = (Cliente) sessao.get(Cliente.class, codigo);
        return cliente;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Cliente> buscarTodos() throws Exception {
        ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Cliente.class);
        criteria.addOrder(Order.asc("codigo"));
        listaRetorno = (ArrayList<Cliente>) criteria.list();
        return listaRetorno;
    }
    
   @SuppressWarnings("unchecked")
    public ArrayList<Cliente> buscarPorNome(String nome) throws Exception {
        ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Cliente.class);
        criteria.add(Restrictions.ilike("nome", "%" + nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Cliente>) criteria.list();
        return listaRetorno;
    }

}