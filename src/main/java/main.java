import DAO.MySQLClienteDAO;
import DAO.MySQLFacturaDAO;
import DAO.MySQLProductoDAO;
import DAO.MySQLProductoFacturaDAO;
import factory.DAOFactory;
import interfaces.InterfaceClienteDAO;
import interfaces.InterfaceFacturaDAO;
import interfaces.InterfaceProductoDAO;
import interfaces.InterfaceProductoFacturaDAO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class main {
    public static void main(String[] args) throws IOException, SQLException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
        InterfaceClienteDAO mySQLClienteDAO = daoFactory.getClienteDAO();
        InterfaceFacturaDAO mySQLFacturaDAO = daoFactory.getFacturaDAO();
        InterfaceProductoDAO mySQLProductoDAO = daoFactory.getProductoDAO();
        InterfaceProductoFacturaDAO mySQLProductoFacturaDAO = daoFactory.getProductoFacturaDAO();

        // DROP TABLE
        mySQLProductoFacturaDAO.dropTable();
        mySQLFacturaDAO.dropTable();
        mySQLClienteDAO.dropTable();
        mySQLProductoDAO.dropTable();


        // PARTE 1 - CREANDO ESQUEMA
        mySQLClienteDAO.createTable();
        mySQLProductoDAO.createTable();
        mySQLFacturaDAO.createTable();
        mySQLProductoFacturaDAO.createTable();

        //        //PARTE 2 - CARGANDO DATOS A LA BD
//
        CSVParser parserProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/productos.csv"));
        for (CSVRecord row : parserProductos) {
            InterfaceProductoDAO newProducto = new MySQLProductoDAO(parseInt(row.get("idProducto")), row.get("nombre"), parseFloat(row.get("valor")));
            mySQLProductoDAO.insert(newProducto);
        }

        CSVParser parserClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/clientes.csv"));
        for (CSVRecord row : parserClientes) {
            InterfaceClienteDAO newCliente = new MySQLClienteDAO(parseInt(row.get("idCliente")), row.get("nombre"), row.get("email"));
            mySQLClienteDAO.insert(newCliente);
        }

        CSVParser parserFacturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas.csv"));
        for (CSVRecord row : parserFacturas) {
            InterfaceFacturaDAO newFactura = new MySQLFacturaDAO(parseInt(row.get("idFactura")), parseInt(row.get("idCliente")));
            mySQLFacturaDAO.insert(newFactura);
        }

        CSVParser parserProductoFactura = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas-productos.csv"));
        for (CSVRecord row : parserProductoFactura) {
            InterfaceProductoFacturaDAO newProductoFactura = new MySQLProductoFacturaDAO(parseInt(row.get("idFactura")), parseInt(row.get("idProducto")), parseInt(row.get("cantidad")));
            mySQLProductoFacturaDAO.insert(newProductoFactura);
        }

        // EJERCICIO 3 - PRODUCTO QUE MAS RECAUDO
        InterfaceProductoDAO masRecaudo = mySQLProductoDAO.getProductoQueMasRecaudo();
        System.out.println(masRecaudo+"\n");

        // EJERCICIO 4 - CLIENTES CON MAS FACTURACION
        mySQLClienteDAO.getClientesMasFacturados();

        //MOSTRANDO TABLAS
//        mySQLClienteDAO.showTable();
//        mySQLProductoDAO.showTable();
//        mySQLFacturaDAO.showTable();
//        mySQLProductoFacturaDAO.showTable();



    }
}
