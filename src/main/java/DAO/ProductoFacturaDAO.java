package DAO;

import factory.MySqlJDBCDAOFactory;
import interfaces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoFacturaDAO<T> implements DAO<T> {

    private int idFactura;
    private int idProducto;
    private int cantidad;

    @Override
    public String toString() {
        return "\n{" +
                "idFactura=" + idFactura +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }

    public ProductoFacturaDAO() {
    }

    public ProductoFacturaDAO(int idFactura, int idProducto, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public void showTable() {
        try {
            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String select = "SELECT f.idFactura, c.nombre AS `Cliente`, p.nombre AS `Producto`, fp.cantidad, p.valor AS `Valor x unidad` " +
                    "FROM `factura_producto` fp " +
                    "JOIN producto p ON fp.idProducto = p.idProducto " +
                    "JOIN factura f ON fp.idFactura = f.idFactura " +
                    "JOIN cliente c ON f.idCliente = c.idCliente";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("IdFactura:" + rs.getInt(1) + ", Cliente: " + rs.getString(2) + " , Producto:" + rs.getString(3)
                        + ", Cantidad: " + rs.getInt(4) + " , Valor por unidad:" + rs.getInt(5));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insert(Object o) throws SQLException {
        Connection conn = MySqlJDBCDAOFactory.createConnection();
        try {
            ProductoFacturaDAO productoFacturado = (ProductoFacturaDAO) o;
            String insert = "INSERT INTO factura_producto (idFactura, IdProducto, cantidad) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, productoFacturado.getIdFactura());
            ps.setInt(2, productoFacturado.getIdProducto());
            ps.setInt(3, productoFacturado.getCantidad());
            ps.executeUpdate();
            ps.close();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }

    }

    @Override
    public void createTable() {
        try {

            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String table = "CREATE TABLE IF NOT EXISTS factura_producto(" +
                    "idFactura INT," +
                    "idProducto INT," +
                    "cantidad INT, " +
                    "PRIMARY KEY(idFactura, idProducto)," +
                    "FOREIGN KEY (idProducto) REFERENCES producto(idProducto) ON DELETE CASCADE," +
                    "FOREIGN KEY (idFactura) REFERENCES factura(idFactura) ON DELETE CASCADE)";
            conn.prepareStatement(table).execute();
            conn.commit();
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
