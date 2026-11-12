import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    List<User> contacts;

    public User(String name) {
        this.name = name;
        this.contacts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addContact(User user) {
        if (user != null && user != this && !contacts.contains(user)) {
            contacts.add(user);
        }
    }

    public void sendGroupMessage(String message) {
        System.out.println("[" + this.name + " -> Group]: " + message);
        for (User contact : contacts) {
            contact.receiveMessage(this, message);
        }
    }

    public void receiveMessage(User sender, String message) {
        System.out.println("[" + this.name + "] Menerima pesan dari " + sender.getName() + ": " + message);
    }
}