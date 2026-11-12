public class Main {
    public static void main(String[] args) {
        User budi = new User("Budi");
        User andi = new User("Andi");

        budi.sendMessage(andi, "Halo Andi!");
    }
}