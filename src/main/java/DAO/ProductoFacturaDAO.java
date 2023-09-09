package DAO;
import factory.MySqlJDBCDAOFactory;
import interfaces.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductoFacturaDAO<T> implements DAO<T> {

    private int idFactura;
    private int idProducto;
    private int cantidad;
    @Override
    public void insert(T t) {

    }

    @Override
    public void createTable() {
        try {

            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String table = "CREATE TABLE factura_producto(" +
                    "idFactura INT," +
                    "idProducto INT," +
                    "cantidad INT, " +
                    "PRIMARY KEY(idFactura))";
            conn.prepareStatement(table).execute();
            conn.commit();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropTable() {
        try {
            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String table = "DROP TABLE factura_producto";
            conn.prepareStatement(table).execute();
            conn.commit();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
