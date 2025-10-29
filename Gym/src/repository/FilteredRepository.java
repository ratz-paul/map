package repository;
import domain.Identifiable;
import filter.AbstractFilter;
import java.util.List;

public class FilteredRepository<ID, T extends Identifiable<ID>> extends MemoryRepository<ID, T> {
    private final AbstractFilter<T> filter;

    public FilteredRepository(AbstractFilter<T> filter) {
        super();
        this.filter = filter;
    }

    @Override
    public List<T> getAll() {
        return super.getAll().stream().filter(filter::accept).toList();
    }
}
