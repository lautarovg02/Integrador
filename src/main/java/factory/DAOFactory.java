package factory;

import DAO.MySQLClienteDAO;
import DAO.MySQLFacturaDAO;
import DAO.MySQLProductoDAO;
import DAO.MySQLProductoFacturaDAO;


public  abstract class DAOFactory {



    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public static final int JPA_HIBERNATE = 3;
    public abstract MySQLProductoDAO getProductoDAO();
    public abstract MySQLFacturaDAO getFacturaDAO();
    public abstract MySQLClienteDAO getClienteDAO();
    public abstract MySQLProductoFacturaDAO getProductoFacturaDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : return MySqlJDBCDAOFactory.getInstance();
//            case DERBY_JDBC: return new DerbyJDBCDAOFactory();
//            case JPA_HIBERNATE: …
            default: return null;
        }
    }
}
