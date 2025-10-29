package repository;
import domain.Identifiable;
import java.util.*;
public class MemoryRepository<ID, T extends Identifiable> implements IRepository<ID, T> {
    protected Map<ID, T> elements = new HashMap<>();
    @Override
    public T add(ID id, T entity) {
        if (id == null || entity == null) throw new IllegalArgumentException("id or entity is null");
        if (elements.containsKey(id)) throw  new IllegalArgumentException("element already exists");
        entity.setId(id);
        elements.put(id, entity);
        return entity;
    }

    @Override
    public boolean delete(ID id) {
        return elements.remove(id) != null;
    }

    @Override
    public T modify(ID id, T entity) {
        if(!elements.containsKey(id)) throw  new IllegalArgumentException("element not exists");
        entity.setId(id);
        elements.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(elements.get(id));
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(elements.values());
    }
}
