public class Main {
    public static void main(String[] args) {
        ChatRoom general = new ChatRoom("General");
        general.addBadWord("kasar");
        general.addBadWord("bodoh");

        User budi = new User("Budi");
        User andi = new User("Andi");
        User cici = new User("Cici");

        general.registerUser(budi);
        general.registerUser(andi);
        general.registerUser(cici);

        budi.send("Halo semuanya, jangan berkata kasar ya!");
        System.out.println();
        andi.sendPrivate("Cici", "Kamu tidak bodoh kok, tenang saja.");
    }
}