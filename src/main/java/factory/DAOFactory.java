package factory;

import interfaces.InterfaceClienteDAO;
import interfaces.InterfaceFacturaDAO;
import interfaces.InterfaceProductoDAO;
import interfaces.InterfaceProductoFacturaDAO;

public abstract class DAOFactory {

    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public static final int JPA_HIBERNATE = 3;

    public abstract InterfaceProductoDAO getProductoDAO();

    public abstract InterfaceFacturaDAO getFacturaDAO();

    public abstract InterfaceClienteDAO getClienteDAO();

    public abstract InterfaceProductoFacturaDAO getProductoFacturaDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC:
                return MySqlJDBCDAOFactory.getInstance();
//            case DERBY_JDBC: return new DerbyJDBCDAOFactory();
//            case JPA_HIBERNATE: …
            default:
                return null;
        }
    }
}
