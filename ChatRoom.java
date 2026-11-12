import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    String roomName;
    List<User> members;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.members = new ArrayList<>();
    }

    public String getRoomName() {
        return roomName;
    }

    public void registerUser(User user) {
        if (user != null && !members.contains(user)) {
            members.add(user);
            user.setChatRoom(this);
        }
    }

    public void sendBroadcast(User sender, String message) {
        System.out.println("[ChatRoom - " + roomName + "] (Broadcast dari " + sender.getName() + "): " + message);
        for (User member : members) {
            if (!member.equals(sender)) {
                member.receiveMessage(sender, message);
            }
        }
    }
}