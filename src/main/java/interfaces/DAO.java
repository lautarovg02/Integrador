package interfaces;

public interface DAO<T> {

    void showTable();
    void insert(T t);
    void createTable();

    void dropTable();
}
