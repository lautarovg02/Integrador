package interfaces;

import java.sql.SQLException;

public interface InterfaceClienteDAO<T> extends DAO<T> {

    void showTable();
    void insert(T t) throws SQLException;
    void createTable();
    void dropTable();

    void getClientesMasFacturados() throws SQLException;
}