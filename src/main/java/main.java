import DAO.ProductoDAO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello Wordl");



        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("csv/productos.csv"));
        for(CSVRecord row: parser) {
            System.out.println(row.get("idProducto"));
            System.out.println(row.get("nombre"));
            System.out.println(row.get("valor"));
        }
    }
}
