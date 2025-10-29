package filter;

import domain.Client;
import filter.AbstractFilter;

public class clientByName implements AbstractFilter<Client> {
    private final String needle;

    public clientByName(String needle) {
        this.needle = needle == null ? "" : needle.toLowerCase();
    }

    @Override
    public boolean accept(Client c) {
        return c.getName() != null && c.getName().toLowerCase().contains(needle);
    }
}
