package dao.abstracts;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<K, T> {

    public void insert(T t);
    public void update(T t);
    public void delete(T t);
    public List<T> find();
    public T findById(K id) throws SQLException;

}
