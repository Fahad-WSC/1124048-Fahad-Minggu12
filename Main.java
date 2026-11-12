public class Main {
    public static void main(String[] args) {
        User budi = new User("Budi");
        User andi = new User("Andi");
        User cici = new User("Cici");

        budi.addContact(andi);
        budi.addContact(cici);

        andi.addContact(budi);
        andi.addContact(cici);

        cici.addContact(budi);
        cici.addContact(andi);

        budi.sendGroupMessage("Halo semuanya!");
    }
}