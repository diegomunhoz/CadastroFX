package util;

public class GeraTabela {

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}