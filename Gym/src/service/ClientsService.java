package service;

import domain.Client;
import filter.AbstractFilter;
import repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientsService {
    private final IRepository<Integer, Client> repo;
    private int nextId;

    public ClientsService(IRepository<Integer, Client> repo) {
        this.repo = repo;
        this.nextId = repo.getAll().stream().map(Client::getId).max(Integer::compareTo).orElse(0) + 1;
    }

    public Client add(String name, String email, String phone) {
        Client c = new Client(nextId, name, email, phone);
        repo.add(nextId, c);
        nextId++;
        return c;
    }

    public List<Client> all() { return repo.getAll(); }

    public Optional<Client> byId(int id) { return repo.findById(id); }

    public Client update(int id, String name, String email, String phone) {
        Client existing = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found id=" + id));
        existing.setName(name);
        existing.setEmail(email);
        existing.setPhone(phone);
        return repo.modify(id, existing);
    }

    public boolean remove(int id) { return repo.delete(id); }

    public int count() { return repo.getAll().size(); }

    public List<Client> filter(AbstractFilter<Client> filter) {
        List<Client> out = new ArrayList<>();
        for (Client c : repo.getAll()) if (filter.accept(c)) out.add(c);
        return out;
    }
}
