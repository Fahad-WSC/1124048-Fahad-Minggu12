public class User {
    String name;
    ChatRoom chatRoom;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public void send(String message) {
        if (chatRoom == null) {
            System.out.println("[" + name + "] Belum tergabung ke ChatRoom manapun.");
            return;
        }
        System.out.println("[" + name + "]: Memproses pengiriman pesan grup...");
        chatRoom.sendBroadcast(this, message);
    }

    public void sendPrivate(String recipientName, String message) {
        if (chatRoom == null) {
            System.out.println("[" + name + "] Belum tergabung ke ChatRoom manapun.");
            return;
        }
        System.out.println("[" + name + "]: Mengirim pesan privat ke " + recipientName + "...");
        chatRoom.sendPrivateMessage(this, recipientName, message);
    }

    public void receiveMessage(User sender, String message) {
        System.out.println("[" + this.name + "] Menerima pesan dari " + sender.getName() + ": " + message);
    }

    public void receivePrivateMessage(User sender, String message) {
        System.out.println("[" + this.name + "] Menerima pesan privat dari " + sender.getName() + ": " + message);
    }
}