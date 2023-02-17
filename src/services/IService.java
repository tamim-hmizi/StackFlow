package services;

import java.util.List;

public interface IService<T> {

    public void Create(T t);

    public List<T> Read(T t);

    public void Update(T t);

    public void Delete(T t);

}
