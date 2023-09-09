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

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");

        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);
        ClienteDAO clienteDAO = daoFactory.getClienteDAO();
        FacturaDAO facturaDAO = daoFactory.getFacturaDAO();
        ProductoDAO productoDAO = daoFactory.getProductoDAO();
        ProductoFacturaDAO productoFacturaDAO = daoFactory.getProductoFacturaDAO();


        // PARTE 1

        /*
         *Creando tablas
         */

//        clienteDAO.createTable();
//        facturaDAO.createTable();
//        productoDAO.createTable();
//        productoFacturaDAO.createTable();

        /*
         *Eliminando tablas
         */
//        clienteDAO.dropTable();
//        facturaDAO.dropTable();
//        productoDAO.dropTable();
//        productoFacturaDAO.dropTable();


        //PARTE 2 agregando datos a la tabla

//        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/productos.csv"));
//        for(CSVRecord row: parser) {
//            ProductoDAO newProducto = new ProductoDAO(parseInt(row.get("idProducto")),row.get("nombre"),parseFloat(row.get("valor")));
//            productoDAO.insert(newProducto);
//        }
//        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/clientes.csv"));
//        for (CSVRecord row : parser) {
//            ClienteDAO newCliente = new ClienteDAO<>(parseInt(row.get("idCliente")),row.get("nombre"),row.get("email"));
//            clienteDAO.insert(newCliente);
//        }


        //MOSTRANDO TABLAS
        productoDAO.showTable();

//        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas.csv"));
//        for(CSVRecord row: parser) {
//            System.out.println("\n" + row.get("idProducto"));
//            System.out.println(row.get("nombre"));
//            System.out.println(row.get("valor"));
//        }



    }
}
