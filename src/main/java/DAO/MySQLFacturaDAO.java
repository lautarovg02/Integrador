package DAO;

import factory.MySqlJDBCDAOFactory;
import interfaces.InterfaceFacturaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLFacturaDAO<T> implements InterfaceFacturaDAO<T> {
    private int idFactura;
    private int idCliente;

    public MySQLFacturaDAO(int idFactura, int idCliente) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
    }

    public MySQLFacturaDAO() {
    }

    public int getIdFactura() {
        return this.idFactura;
    }

    public void setIdFactura(int idFactura) {this.idFactura = idFactura;}

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public void showTable() {
        try {
            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String select = "SELECT f.idFactura, c.nombre AS `Cliente`, c.email FROM `factura` f " +
                            "JOIN cliente c ON f.idCliente = c.idCliente;";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println( "IdFactura:" + rs.getInt(1) + ",  Cliente: " + rs.getString(2) + " , Email:" + rs.getString(3) );
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Object o) throws SQLException {
        Connection conn = MySqlJDBCDAOFactory.createConnection();
        try{
            MySQLFacturaDAO mySQLFacturaDAO = (MySQLFacturaDAO) o;
            String insert =  "INSERT INTO factura (idFactura, idCliente) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, mySQLFacturaDAO.getIdFactura());
            ps.setInt(2, mySQLFacturaDAO.getIdCliente());
            ps.executeUpdate();
            ps.close();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            conn.close();
        }
    }

    @Override
    public void createTable() {
        Connection conn;
        try {
            conn = MySqlJDBCDAOFactory.createConnection();
            String table = "CREATE TABLE IF NOT EXISTS factura (" +
                    "idCliente INT," +
                    "idFactura INT," +
                    "PRIMARY KEY (idFactura)," +
                    "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente) ON DELETE CASCADE" +
                    ")";
            conn.prepareStatement(table).execute();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "\n{" +
                "idFactura=" + idFactura +
                ", idCliente=" + idCliente +
                '}';
    }

    @Override
    public void dropTable() {
        try {
            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String table = "DROP TABLE factura";
            conn.prepareStatement(table).execute();
            conn.commit();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
