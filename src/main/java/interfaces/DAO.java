package interfaces;

public interface DAO<T> {


    void insert(T t);
    void createTable(String query);
}