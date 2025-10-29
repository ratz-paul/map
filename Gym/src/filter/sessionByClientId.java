package filter;

import domain.Session;
import filter.AbstractFilter;

public class sessionByClientId implements AbstractFilter<Session> {
    private final int clientId;
    public sessionByClientId(int clientId) { this.clientId = clientId; }
    @Override public boolean accept(Session s) {
        return s.getClientId() != null && s.getClientId() == clientId;
    }
}
