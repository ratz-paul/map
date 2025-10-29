package repository;
import java.util.List;
import java.util.Optional;

public interface IRepository<ID, T> {
    T add(ID id, T entity);
    boolean delete (ID id);
    T modify (ID id, T entity);
    Optional <T> findById(ID id);
    List<T> getAll();
}
