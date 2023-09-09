package DAO;

import factory.DAOFactory;
import factory.MySqlJDBCDAOFactory;
import interfaces.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class ClienteDAO<T> implements DAO<T> {
    private int idCliente;
    private String nombre;
    private String email;

    @Override
    public void insert(T t) {

    }

    @Override
    public void createTable() {

        try {

            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String table = "CREATE TABLE cliente(" +
                    "idCliente INT," +
                    "nombre VARCHAR(500)," +
                    "email VARCHAR(150), " +
                    "PRIMARY KEY(idCliente))";
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
            String table = "DROP TABLE cliente";
            conn.prepareStatement(table).execute();
            conn.commit();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
