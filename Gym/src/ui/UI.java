package ui;

import service.ClientsService;
import service.SessionsService;
import repository.ClientsRepository;
import repository.SessionsRepository;
import repository.FilteredRepository;
import domain.Client;
import domain.Session;
import filter.clientByEmail;
import filter.clientByName;
import filter.sessionByClientId;
import filter.sessionByDate;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final ClientsService clients;
    private final SessionsService sessions;
    private final Scanner in = new Scanner(System.in);

    public UI(ClientsService clients, SessionsService sessions) {
        this.clients = clients;
        this.sessions = sessions;
    }

    private void menu() {
        System.out.println("""
                GYM MANAGEMENT
                CLIENT OPERATIONS
                1. List all clients
                2. Add client
                3. Update client
                4. Delete client
                5. Find client by ID
                6. Filter clients by name
                7. Filter clients by email domain
                
                SESSION OPERATIONS
                8. List all sessions
                9. Add session
                10. Update session
                11. Delete session
                12. Find session by ID
                13. Filter sessions by client ID
                14. Filter sessions by date range
                
                0. Exit
                """);
        System.out.print("Choice: ");
    }

    public void run() {
        while (true) {
            menu();
            String ch = in.nextLine().trim();
            try {
                switch (ch) {
                    case "1" -> listClients(clients.all());
                    case "2" -> addClient();
                    case "3" -> updateClient();
                    case "4" -> deleteClient();
                    case "5" -> findClient();
                    case "6" -> filterClientsByName();
                    case "7" -> filterClientsByEmail();
                    case "8" -> listSessions(sessions.all());
                    case "9" -> addSession();
                    case "10" -> updateSession();
                    case "11" -> deleteSession();
                    case "12" -> findSession();
                    case "13" -> filterSessionsByClientId();
                    case "14" -> filterSessionsByDate();
                    case "0" -> {
                        return;
                    }

                    default -> System.out.println("Unknown option.\n");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private void listClients(List<Client> list) {
        if (list.isEmpty()) System.out.println("(no clients)");
        else list.forEach(System.out::println);
        System.out.println("Total: " + clients.count());
    }

    private void addClient() {
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Phone: ");
        String phone = in.nextLine();
        System.out.println("Added: " + clients.add(name, email, phone));
    }

    private void updateClient() {
        System.out.print("Client ID: ");
        int id = Integer.parseInt(in.nextLine());
        System.out.print("New name: ");
        String name = in.nextLine();
        System.out.print("New email: ");
        String email = in.nextLine();
        System.out.print("New phone: ");
        String phone = in.nextLine();
        System.out.println("Updated: " + clients.update(id, name, email, phone));
    }

    private void deleteClient() {
        System.out.print("Client ID: ");
        int id = Integer.parseInt(in.nextLine());
        System.out.println(clients.remove(id) ? "Deleted." : "Not found.");
    }

    private void findClient() {
        System.out.print("Client ID: ");
        int id = Integer.parseInt(in.nextLine());
        System.out.println(clients.byId(id).map(Object::toString).orElse("Not found."));
    }

    private void filterClientsByName() {
        System.out.print("Name: ");
        String s = in.nextLine();
        FilteredRepository<Integer, Client> fr =
                new FilteredRepository<>(new clientByName(s));
        for (Client c : clients.all()) fr.add(c.getId(), c);
        listClients(fr.getAll());
    }

    private void filterClientsByEmail() {
        System.out.print("Domain (e.g. gmail.com): ");
        String domain = in.nextLine();
        FilteredRepository<Integer, Client> fr =
                new FilteredRepository<>(new clientByEmail(domain));
        for (Client c : clients.all()) fr.add(c.getId(), c);
        listClients(fr.getAll());
    }

    private void listSessions(List<Session> list) {
        if (list.isEmpty()) System.out.println("(no sessions)");
        else list.forEach(System.out::println);
        System.out.println("Total: " + sessions.count());
    }

    private void addSession() {
        System.out.print("Client ID: ");
        int clientId = Integer.parseInt(in.nextLine());
        System.out.print("Date (yyyy-MM-dd): ");
        String date = in.nextLine();
        System.out.print("Time (HH:mm): ");
        String time = in.nextLine();
        System.out.print("Description: ");
        String desc = in.nextLine();
        System.out.println("Added: " + sessions.add(clientId, date, time, desc));
    }

    private void updateSession() {
        System.out.print("Session ID: ");
        int id = Integer.parseInt(in.nextLine());
        System.out.print("Client ID: ");
        int clientId = Integer.parseInt(in.nextLine());
        System.out.print("Date (yyyy-MM-dd): ");
        String date = in.nextLine();
        System.out.print("Time (HH:mm): ");
        String time = in.nextLine();
        System.out.print("Description: ");
        String desc = in.nextLine();
        System.out.println("Updated: " + sessions.update(id, clientId, date, time, desc));
    }

    private void deleteSession() {
        System.out.print("Session ID: ");
        int id = Integer.parseInt(in.nextLine());
        System.out.println(sessions.remove(id) ? "Deleted." : "Not found.");
    }

    private void findSession() {
        System.out.print("Session ID: ");
        int id = Integer.parseInt(in.nextLine());
        System.out.println(sessions.byId(id).map(Object::toString).orElse("Not found."));
    }

    private void filterSessionsByClientId() {
        System.out.print("Client ID: ");
        int clientId = Integer.parseInt(in.nextLine());
        FilteredRepository<Integer, Session> fr =
                new FilteredRepository<>(new sessionByClientId(clientId));
        for (Session s : sessions.all()) fr.add(s.getId(), s);
        listSessions(fr.getAll());
    }

    private void filterSessionsByDate() {
        System.out.print("Start date (yyyy-MM-dd): ");
        String startDate = in.nextLine();
        System.out.print("Start time (HH:mm): ");
        String startTime = in.nextLine();
        System.out.print("End date (yyyy-MM-dd): ");
        String endDate = in.nextLine();
        System.out.print("End time (HH:mm): ");
        String endTime = in.nextLine();
        FilteredRepository<Integer, Session> fr =
                new FilteredRepository<>(new sessionByDate(startDate, startTime, endDate, endTime));
        for (Session s : sessions.all()) fr.add(s.getId(), s);
        listSessions(fr.getAll());
    }
}
