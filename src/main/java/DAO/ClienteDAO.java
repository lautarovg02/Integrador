package DAO;

import factory.DAOFactory;
import factory.MySqlJDBCDAOFactory;
import interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClienteDAO<T> implements DAO<T> {
    private int idCliente;
    private String nombre;
    private String email;

    public ClienteDAO() {
    }

    public ClienteDAO(int idCliente, String nombre, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void showTable() {
        try {
            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String select = "SELECT * FROM cliente ";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println( "IdCliente:" + rs.getInt(1) + ",  Cliente: " + rs.getString(2) + " , Email:" + rs.getString(3) );
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return " \n{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public void insert(Object o) throws SQLException {
        Connection conn = MySqlJDBCDAOFactory.createConnection();
        try{
            ClienteDAO cliente = (ClienteDAO) o;
            String insert =  "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            ps.executeUpdate();
            ps.close();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }
    }

    public static void getClientesMasFacturados() throws SQLException{
        try {
            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String get = "SELECT c.idCliente, c.nombre, c.email, SUM(p.valor * fp.cantidad) AS 'facturacion' FROM cliente c " +
                         "JOIN factura f ON c.idCliente = f.idCliente " +
                         "JOIN factura_producto fp ON f.idFactura = fp.idFactura " +
                         "JOIN producto p ON fp.idProducto = p.idProducto " +
                         "GROUP BY c.idCLiente, c.nombre, c.email " +
                         "ORDER BY facturacion DESC";
            PreparedStatement ps = conn.prepareStatement(get);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(" idCliente: " + rs.getInt(1) +" Nombre: " + rs.getString(2) +" Email: " + rs.getString(3) +" Cantidad: " + rs.getInt(4));
            }
            conn.commit();
            conn.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTable() {

        try {

            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String table = "CREATE TABLE IF NOT EXISTS cliente(" +
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
