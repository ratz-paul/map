package domain;

import java.util.Objects;

public class Session implements Identifiable<Integer> {
    private Integer id;
    private Integer clientId;
    private String date;
    private String time;
    private String description;

    public Session(Integer id, int clientId, String date, String time, String description) {
        this.id = id;
        this.clientId = clientId;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public Session(int clientId, String date, String time, String description) {
        this(null, clientId, date, time, description);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session s = (Session) o;
        return Objects.equals(id, s.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Session{id=" + id +
                ", clientId=" + clientId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
