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

        clienteDAO.createTable();
        facturaDAO.createTable();
        productoDAO.createTable();
        productoFacturaDAO.createTable();

        /*
        *Eliminando tablas
        */
//        clienteDAO.dropTable();
//        facturaDAO.dropTable();
//        productoDAO.dropTable();
//        productoFacturaDAO.dropTable();


        //PARTE 2

        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/productos.csv"));
        for(CSVRecord row: parser) {
            System.out.println("\n" + row.get("idProducto"));
            System.out.println(row.get("nombre"));
            System.out.println(row.get("valor"));
        }


//        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/java/csv/facturas.csv"));
//        for(CSVRecord row: parser) {
//            System.out.println("\n" + row.get("idProducto"));
//            System.out.println(row.get("nombre"));
//            System.out.println(row.get("valor"));
//        }



    }
}
