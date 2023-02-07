package services;

import java.util.List;

public interface IService<T> {

    public void Create(T t);

    public void Read(T t);

    public void Update(T t);

    public List<T> Delete(T t);

}
