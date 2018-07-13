package dao;

import java.util.ArrayList;
import model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

public class UsuarioDAO extends GenericDAO {

    public Usuario buscarPorCodigo(Long codigo) throws Exception {
        Session sessao = null;
        Usuario usuario = null;
        sessao = HibernateUtil.getSessionFactory().openSession();
        usuario = (Usuario) sessao.get(Usuario.class, codigo);
        return usuario;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Usuario> buscarTodos() throws Exception {
        ArrayList<Usuario> listaRetorno = new ArrayList<Usuario>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        criteria.addOrder(Order.asc("codigo"));
        listaRetorno = (ArrayList<Usuario>) criteria.list();
        return listaRetorno;
    }
    
   @SuppressWarnings("unchecked")
    public ArrayList<Usuario> buscarPorNome(String nome) throws Exception {
        ArrayList<Usuario> listaRetorno = new ArrayList<Usuario>();
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = sessao.createCriteria(Usuario.class);
        criteria.add(Restrictions.ilike("nome", "%" + nome + "%"));
        criteria.addOrder(Order.asc("nome"));
        listaRetorno = (ArrayList<Usuario>) criteria.list();
        return listaRetorno;
    }

}