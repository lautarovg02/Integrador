package interfaces;

import java.sql.SQLException;

import DAO.MySQLProductoDAO;

public interface InterfaceProductoDAO<T> extends DAO<T> {

    void showTable();
    void insert(T t) throws SQLException;
    void createTable();
    void dropTable();

    MySQLProductoDAO<T> getProductoQueMasRecaudo();
}
