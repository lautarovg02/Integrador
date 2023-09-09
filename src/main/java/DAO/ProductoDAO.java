package DAO;

import factory.MySqlJDBCDAOFactory;
import interfaces.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductoDAO<T> implements DAO<T> {

   private int idProducto;
   private String nombre;
   private float valor;


    @Override
    public void insert(Object o) {

    }

    @Override
    public void createTable() {
        try {

            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String table = "CREATE TABLE producto(" +
                    "idProducto INT," +
                    "nombre VARCHAR(500)," +
                    "valor FLOAT, " +
                    "PRIMARY KEY (idProducto))";
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
            String table = "DROP TABLE producto";
            conn.prepareStatement(table).execute();
            conn.commit();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
