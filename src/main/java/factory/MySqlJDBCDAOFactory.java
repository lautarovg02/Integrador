package factory;

import DAO.MySQLClienteDAO;
import DAO.MySQLFacturaDAO;
import DAO.MySQLProductoDAO;
import DAO.MySQLProductoFacturaDAO;
import interfaces.InterfaceClienteDAO;
import interfaces.InterfaceFacturaDAO;
import interfaces.InterfaceProductoDAO;
import interfaces.InterfaceProductoFacturaDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;


public class MySqlJDBCDAOFactory extends DAOFactory {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/integrador1";

    private static DAOFactory instance = new MySqlJDBCDAOFactory(); // Eagerly loading of singleton instance

    private MySqlJDBCDAOFactory() {

    }

    public static DAOFactory getInstance() {
        return instance;
    }

    // method to create DB connection
    public static Connection createConnection() {
        // Use DRIVER and DBURL to create a connection
        try {
            // sintaxis para variar el import de acuerdo a "driver"
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
//            Como la BBDD es MySQL requiere autenticacion por lo que pasamos la uri y el usuario y contraseña
            Connection conn = DriverManager.getConnection(DBURL, "root", "");
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InterfaceProductoDAO getProductoDAO() {
        return new MySQLProductoDAO();
    }

    @Override
    public InterfaceFacturaDAO getFacturaDAO() {
        return new MySQLFacturaDAO();
    }

    @Override
    public InterfaceClienteDAO getClienteDAO() {
        return new MySQLClienteDAO();
    }

    @Override
    public InterfaceProductoFacturaDAO getProductoFacturaDAO() {
        return new MySQLProductoFacturaDAO();
    }
}
