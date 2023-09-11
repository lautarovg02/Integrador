import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.ProductoDAO;
import DAO.ProductoFacturaDAO;
import factory.DAOFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class main {
    public static void main(String[] args) throws IOException, SQLException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
        ClienteDAO clienteDAO = daoFactory.getClienteDAO();
        FacturaDAO facturaDAO = daoFactory.getFacturaDAO();
        ProductoDAO productoDAO = daoFactory.getProductoDAO();
        ProductoFacturaDAO productoFacturaDAO = daoFactory.getProductoFacturaDAO();

        // DROP TABLE
//        productoFacturaDAO.dropTable();
//        facturaDAO.dropTable();
//        clienteDAO.dropTable();
//        productoDAO.dropTable();


        // PARTE 1 - CREANDO ESQUEMA
        clienteDAO.createTable();
        productoDAO.createTable();
        facturaDAO.createTable();
        productoFacturaDAO.createTable();

        //        //PARTE 2 - CARGANDO DATOS A LA BD
//
//        CSVParser parserProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/productos.csv"));
//        for(CSVRecord row: parserProductos) {
//            ProductoDAO newProducto = new ProductoDAO(parseInt(row.get("idProducto")),row.get("nombre"),parseFloat(row.get("valor")));
//            productoDAO.insert(newProducto);
//        }
//
//        CSVParser parserClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/clientes.csv"));
//        for (CSVRecord row : parserClientes) {
//            ClienteDAO newCliente = new ClienteDAO<>(parseInt(row.get("idCliente")),row.get("nombre"),row.get("email"));
//            clienteDAO.insert(newCliente);
//        }
//
//        CSVParser parserFacturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas.csv"));
//        for (CSVRecord row : parserFacturas) {
//            FacturaDAO newFactura = new FacturaDAO<>(parseInt(row.get("idFactura")), parseInt(row.get("idCliente")));
//            facturaDAO.insert(newFactura);
//        }
//
//        CSVParser parserProductoFactura = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas-productos.csv"));
//        for (CSVRecord row : parserProductoFactura) {
//            ProductoFacturaDAO newProductoFactura = new ProductoFacturaDAO<>(parseInt(row.get("idFactura")), parseInt(row.get("idProducto")), parseInt(row.get("cantidad")));
//            newProductoFactura.insert(newProductoFactura);
//        }

        // EJERCICIO 3 - PRODUCTO QUE MAS RECAUDO
//        ProductoDAO masRecaudo = productoDAO.getProductoQueMasRecaudo();
//        System.out.println(masRecaudo);

        // EJERCICIO 4 - CLIENTES CON MAS FACTURACION
//        ClienteDAO.getClientesMasFacturados();

        //MOSTRANDO TABLAS
//        clienteDAO.showTable();
//        productoDAO.showTable();
//        facturaDAO.showTable();
//        productoFacturaDAO.showTable();

    }
}
