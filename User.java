public class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(User receiver, String message) {
        System.out.println("[" + this.name + " -> " + receiver.getName() + "]: " + message);
        receiver.receiveMessage(this, message);
    }

    public void receiveMessage(User sender, String message) {
        System.out.println("[" + this.name + "] Menerima pesan dari " + sender.getName() + ": " + message);
    }
}