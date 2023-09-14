package interfaces;

import java.sql.SQLException;

public interface InterfaceFacturaDAO<T> extends DAO<T> {

    void showTable();

    void insert(T t) throws SQLException;

    void createTable();

    void dropTable();
}
