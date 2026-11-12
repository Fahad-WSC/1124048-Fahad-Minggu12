import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    ChatRoom chatRoom;
    List<ChatRoom> chatRooms;

    public User(String name) {
        this.name = name;
        this.chatRooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        if (chatRoom != null && !chatRooms.contains(chatRoom)) {
            chatRooms.add(chatRoom);
        }
    }

    public void removeChatRoom(ChatRoom room) {
        chatRooms.remove(room);
        if (room != null && room.equals(this.chatRoom)) {
            this.chatRoom = chatRooms.isEmpty() ? null : chatRooms.get(0);
        }
    }

    private ChatRoom findRoom(String roomName) {
        for (ChatRoom room : chatRooms) {
            if (room.getRoomName().equalsIgnoreCase(roomName)) {
                return room;
            }
        }
        return null;
    }

    public void send(String message) {
        if (chatRoom == null) {
            System.out.println("[" + name + "] Belum tergabung ke ChatRoom manapun.");
            return;
        }
        System.out.println("[" + name + "]: Memproses pengiriman pesan grup...");
        chatRoom.sendBroadcast(this, message);
    }

    public void send(String roomName, String message) {
        ChatRoom targetRoom = findRoom(roomName);
        if (targetRoom == null) {
            System.out.println("[" + name + "] Tidak tergabung ke ChatRoom '" + roomName + "'.");
            return;
        }
        System.out.println("[" + name + "]: Memproses pengiriman pesan grup ke " + roomName + "...");
        targetRoom.sendBroadcast(this, message);
    }

    public void sendPrivate(String recipientName, String message) {
        if (chatRoom == null) {
            System.out.println("[" + name + "] Belum tergabung ke ChatRoom manapun.");
            return;
        }
        System.out.println("[" + name + "]: Mengirim pesan privat ke " + recipientName + "...");
        chatRoom.sendPrivateMessage(this, recipientName, message);
    }

    public void sendPrivate(String roomName, String recipientName, String message) {
        ChatRoom targetRoom = findRoom(roomName);
        if (targetRoom == null) {
            System.out.println("[" + name + "] Tidak tergabung ke ChatRoom '" + roomName + "'.");
            return;
        }
        System.out.println("[" + name + "]: Mengirim pesan privat ke " + recipientName + " di " + roomName + "...");
        targetRoom.sendPrivateMessage(this, recipientName, message);
    }

    public void receiveMessage(User sender, String message) {
        System.out.println("[" + this.name + "] Menerima pesan dari " + sender.getName() + ": " + message);
    }

    public void receivePrivateMessage(User sender, String message) {
        System.out.println("[" + this.name + "] Menerima pesan privat dari " + sender.getName() + ": " + message);
    }
}