package filter;

import domain.Client;
import filter.AbstractFilter;

public class clientByEmail implements AbstractFilter<Client> {
    private final String domain;
    public clientByEmail(String domain) {
        this.domain = domain == null ? "" : domain.toLowerCase();
    }
    @Override public boolean accept(Client c) {
        if (c.getEmail() == null) return false;
        int at = c.getEmail().lastIndexOf('@');
        if (at < 0 || at == c.getEmail().length()-1) return false;
        String d = c.getEmail().substring(at + 1).toLowerCase();
        return d.equals(domain);
    }
}