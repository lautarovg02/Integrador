package factory;

import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.ProductoDAO;
import DAO.ProductoFacturaDAO;


import java.sql.Connection;

import java.sql.SQLException;



public  abstract class DAOFactory {

    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public static final int JPA_HIBERNATE = 3;
    public abstract ProductoDAO getProductoDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract ClienteDAO getClienteDAO();
    public abstract ProductoFacturaDAO getProductoFacturaDAO();




    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : return new MySqlJDBCDAOFactory();
//            case DERBY_JDBC: return new DerbyJDBCDAOFactory();
//            case JPA_HIBERNATE: …
            default: return null;
        }
    }
}
