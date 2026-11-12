import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ChatRoom {
    String roomName;
    List<User> members;
    List<String> badWords;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.members = new ArrayList<>();
        this.badWords = new ArrayList<>();
    }

    public String getRoomName() {
        return roomName;
    }

    public void addBadWord(String word) {
        if (word != null && !word.trim().isEmpty()) {
            badWords.add(word.trim());
        }
    }

    public String filterMessage(String message) {
        if (message == null) return "";
        String filtered = message;
        for (String badWord : badWords) {
            filtered = filtered.replaceAll("(?i)" + Pattern.quote(badWord), "***");
        }
        return filtered;
    }

    public void registerUser(User user) {
        if (user != null && !members.contains(user)) {
            members.add(user);
            user.setChatRoom(this);
        }
    }

    public void sendBroadcast(User sender, String message) {
        String cleanMessage = filterMessage(message);
        System.out.println("[ChatRoom - " + roomName + "] (Broadcast dari " + sender.getName() + "): " + cleanMessage);
        for (User member : members) {
            if (!member.equals(sender)) {
                member.receiveMessage(sender, cleanMessage);
            }
        }
    }

    public void sendPrivateMessage(User sender, String recipientName, String message) {
        User recipient = null;
        for (User member : members) {
            if (member.getName().equalsIgnoreCase(recipientName)) {
                recipient = member;
                break;
            }
        }
        if (recipient == null) {
            System.out.println("[ChatRoom - " + roomName + "] Pengguna '" + recipientName + "' tidak ditemukan di room.");
            return;
        }
        String cleanMessage = filterMessage(message);
        System.out.println("[ChatRoom - " + roomName + "] (Private " + sender.getName() + " -> " + recipient.getName() + "): " + cleanMessage);
        recipient.receivePrivateMessage(sender, cleanMessage);
    }
}