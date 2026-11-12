public class Main {
    public static void main(String[] args) {
        ChatRoom general = new ChatRoom("General");
        ChatRoom sports = new ChatRoom("Sports");

        User budi = new User("Budi");
        User andi = new User("Andi");
        User cici = new User("Cici");

        general.registerUser(budi);
        general.registerUser(andi);
        general.registerUser(cici);

        sports.registerUser(andi);
        sports.registerUser(cici);

        budi.send("General", "Selamat datang di General!");
        andi.send("Sports", "Ada yang mau main futsal?");
        System.out.println();

        general.removeUser(andi, cici);
        general.removeUser(budi, cici);
        cici.send("General", "Tes apakah masih bisa kirim pesan?");
    }
}