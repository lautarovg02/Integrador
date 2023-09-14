package DAO;

import factory.MySqlJDBCDAOFactory;
import interfaces.InterfaceProductoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLProductoDAO<T> implements InterfaceProductoDAO<T> {

   private int idProducto;
   private String nombre;
   private float valor;

    public MySQLProductoDAO() {
    }

    public MySQLProductoDAO(int idProducto, String nombre, float valor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "\n {" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }

    @Override
    public void insert(Object o) throws SQLException {
        Connection conn = MySqlJDBCDAOFactory.createConnection();
        try{
            MySQLProductoDAO producto = (MySQLProductoDAO) o;
            String insert =  "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
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
    public void showTable() {
        try {
            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String select = "SELECT * FROM `producto`;";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " , " + rs.getString(2) + ", " + rs.getFloat(3) );
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void createTable() {
        try {
            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String table = "CREATE TABLE IF NOT EXISTS producto(" +
                    "idProducto INT," +
                    "nombre VARCHAR(45)," +
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

    public MySQLProductoDAO<T> getProductoQueMasRecaudo(){
        try {
            Connection conn = MySqlJDBCDAOFactory.createConnection();
            String query = "SELECT p.valor, p.idProducto, p.nombre, SUM(fp.cantidad * p.valor) AS recaudacion " +
                    "FROM producto AS p " +
                    "JOIN factura_producto AS fp ON p.idProducto = fp.idProducto " +
                    "GROUP BY p.idProducto, p.nombre " +
                    "ORDER BY recaudacion DESC " +
                    "LIMIT 1";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            MySQLProductoDAO<T> productoMasReacudado = null;
            while(rs.next()){
                productoMasReacudado = new MySQLProductoDAO(rs.getInt("idProducto"), rs.getString("nombre"), rs.getFloat("valor"));
            }
            conn.commit();
            conn.close();
            return productoMasReacudado;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return this.valor;
    }

    public  void setValor(float valor) {
        this.valor = valor;
    }
}
