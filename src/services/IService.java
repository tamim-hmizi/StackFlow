package services;

import java.util.List;
import java.sql.SQLException;

public interface IService<T> {

    public void Create(T t) throws SQLException;

    public List<T> Read(T t) throws SQLException;

    public void Update(T t) throws SQLException;

    public void Delete(T t) throws SQLException;

}
