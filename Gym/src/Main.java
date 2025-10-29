import domain.Client;
import domain.Session;
import repository.ClientsRepository;
import repository.SessionsRepository;
import service.ClientsService;
import service.SessionsService;
import ui.UI;

public class Main {
    public static void main(String[] args) {

        ClientsRepository clientsRepository = new ClientsRepository();
        SessionsRepository sessionsRepository = new SessionsRepository();
        ClientsService clientsService = new ClientsService(clientsRepository);
        SessionsService sessionsService = new SessionsService(sessionsRepository);
        Client c1 = new Client(1, "alexandru muntean", "alex.muntean@gmail.com", "+40 721 234 567");
        Client c2 = new Client(2, "cristina popescu", "cristina.popescu@yahoo.com", "+40 733 345 678");
        Client c3 = new Client(3, "andrei marin", "andrei.marin@outlook.com", "+40 744 456 789");
        Client c4 = new Client(4, "maria rus", "maria.rus@gmail.com", "+40 755 567 890");
        Client c5 = new Client(5, "florin adrian", "florin.adrian@yahoo.com", "+40 766 678 901");
        clientsService.add(c1.getName(), c1.getEmail(), c1.getPhone());
        clientsService.add(c2.getName(), c2.getEmail(), c2.getPhone());
        clientsService.add(c3.getName(), c3.getEmail(), c3.getPhone());
        clientsService.add(c4.getName(), c4.getEmail(), c4.getPhone());
        clientsService.add(c5.getName(), c5.getEmail(), c5.getPhone());
        Session s1 = new Session(1, 1, "2025-10-25", "18:00", "upper body");
        Session s2 = new Session(2, 2, "2025-10-26", "08:00", "lower body");
        Session s3 = new Session(3, 3, "2025-10-27", "17:00", "leg day");
        Session s4 = new Session(4, 4, "2025-10-28", "19:00", "cardio");
        Session s5 = new Session(5, 5, "2025-10-29", "07:00", "full body");
        sessionsService.add(s1.getClientId(), s1.getDate(), s1.getTime(), s1.getDescription());
        sessionsService.add(s2.getClientId(), s2.getDate(), s2.getTime(), s2.getDescription());
        sessionsService.add(s3.getClientId(), s3.getDate(), s3.getTime(), s3.getDescription());
        sessionsService.add(s4.getClientId(), s4.getDate(), s4.getTime(), s4.getDescription());
        sessionsService.add(s5.getClientId(), s5.getDate(), s5.getTime(), s5.getDescription());
        UI ui = new UI(clientsService, sessionsService);
        ui.run();
    }
}
