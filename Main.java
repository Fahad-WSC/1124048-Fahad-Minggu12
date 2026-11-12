public class Main {
    public static void main(String[] args) {
        ChatRoom general = new ChatRoom("General");
        ChatRoom sports = new ChatRoom("Sports");

        general.addBadWord("kasar");

        User budi = new User("Budi");
        User andi = new User("Andi");
        User cici = new User("Cici");

        general.registerUser(budi);
        general.registerUser(andi);
        general.registerUser(cici);

        sports.registerUser(andi);
        sports.registerUser(cici);

        System.out.println();
        budi.send("General", "Halo semuanya!");
        budi.sendPrivate("General", "Cici", "Jangan berkata kasar ya.");
        andi.send("Sports", "Yuk latihan basket sore ini!");

        System.out.println();
        general.muteUser(andi);
        andi.send("General", "Apakah pesanku masuk?");
        budi.send("General", "Halo Andi, kamu masih bisa baca pesan ini.");

        System.out.println();
        general.showHistory();
        sports.showHistory();
    }
}