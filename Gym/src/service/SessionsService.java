package service;

import domain.Session;
import filter.AbstractFilter;
import repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionsService {
    private final IRepository<Integer, Session> repo;
    private int nextId;

    public SessionsService(IRepository<Integer, Session> repo) {
        this.repo = repo;
        this.nextId = repo.getAll().stream().map(Session::getId).max(Integer::compareTo).orElse(0) + 1;
    }

    public Session add(int clientId, String date, String time, String description) {
        Session s = new Session(nextId, clientId, date, time, description);
        repo.add(nextId, s);
        nextId++;
        return s;
    }

    public List<Session> all() { return repo.getAll(); }
    public Optional<Session> byId(int id) { return repo.findById(id); }

    public Session update(int id, int clientId, String date, String time, String description) {
        Session s = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found id=" + id));
        s.setClientId(clientId);
        s.setDate(date);
        s.setTime(time);
        s.setDescription(description);
        return repo.modify(id, s);
    }
    public boolean remove(int id) { return repo.delete(id); }

    public int count() { return repo.getAll().size(); }
    public List<Session> filter(AbstractFilter<Session> filter) {
        List<Session> out = new ArrayList<>();
        for (Session s : repo.getAll()) if (filter.accept(s)) out.add(s);
        return out;
    }
}
