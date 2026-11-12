import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ChatRoom {
    String roomName;
    List<User> members;
    List<String> badWords;
    User admin;
    List<User> mutedUsers;
    List<String> chatHistory;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.members = new ArrayList<>();
        this.badWords = new ArrayList<>();
        this.mutedUsers = new ArrayList<>();
        this.chatHistory = new ArrayList<>();
    }

    public String getRoomName() {
        return roomName;
    }

    public User getAdmin() {
        return admin;
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
            if (admin == null) {
                admin = user;
                System.out.println("[ChatRoom - " + roomName + "] " + user.getName() + " bergabung sebagai Admin.");
            } else {
                System.out.println("[ChatRoom - " + roomName + "] " + user.getName() + " bergabung ke room.");
            }
            members.add(user);
            user.setChatRoom(this);
        }
    }

    public void removeUser(User user) {
        if (user != null && members.remove(user)) {
            mutedUsers.remove(user);
            user.removeChatRoom(this);
            System.out.println("[ChatRoom - " + roomName + "] " + user.getName() + " telah dikeluarkan dari room.");
        }
    }

    public void removeUser(User requester, User target) {
        if (requester == null || !requester.equals(admin)) {
            System.out.println("[ChatRoom - " + roomName + "] Akses ditolak: Hanya admin (" + (admin != null ? admin.getName() : "-") + ") yang dapat mengeluarkan pengguna.");
            return;
        }
        if (target != null && target.equals(admin)) {
            System.out.println("[ChatRoom - " + roomName + "] Admin tidak dapat mengeluarkan dirinya sendiri.");
            return;
        }
        removeUser(target);
    }

    public void muteUser(User user) {
        if (user != null && members.contains(user) && !mutedUsers.contains(user)) {
            mutedUsers.add(user);
            System.out.println("[ChatRoom - " + roomName + "] " + user.getName() + " telah di-mute.");
        }
    }

    public void unmuteUser(User user) {
        if (user != null && mutedUsers.remove(user)) {
            System.out.println("[ChatRoom - " + roomName + "] " + user.getName() + " telah di-unmute.");
        }
    }

    public void sendBroadcast(User sender, String message) {
        if (!members.contains(sender)) {
            System.out.println("[ChatRoom - " + roomName + "] " + sender.getName() + " bukan anggota room ini.");
            return;
        }
        if (mutedUsers.contains(sender)) {
            System.out.println("[ChatRoom - " + roomName + "] Pesan dari " + sender.getName() + " tidak disebarkan karena sedang di-mute.");
            return;
        }
        String cleanMessage = filterMessage(message);
        chatHistory.add("[" + roomName + "] (Broadcast) " + sender.getName() + ": " + cleanMessage);

        System.out.println("[ChatRoom - " + roomName + "] (Broadcast dari " + sender.getName() + "): " + cleanMessage);
        for (User member : members) {
            if (!member.equals(sender)) {
                member.receiveMessage(sender, cleanMessage);
            }
        }
    }

    public void sendPrivateMessage(User sender, String recipientName, String message) {
        if (!members.contains(sender)) {
            System.out.println("[ChatRoom - " + roomName + "] " + sender.getName() + " bukan anggota room ini.");
            return;
        }
        if (mutedUsers.contains(sender)) {
            System.out.println("[ChatRoom - " + roomName + "] Pesan privat dari " + sender.getName() + " tidak disebarkan karena sedang di-mute.");
            return;
        }
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
        chatHistory.add("[" + roomName + "] (Private " + sender.getName() + " -> " + recipient.getName() + "): " + cleanMessage);

        System.out.println("[ChatRoom - " + roomName + "] (Private " + sender.getName() + " -> " + recipient.getName() + "): " + cleanMessage);
        recipient.receivePrivateMessage(sender, cleanMessage);
    }

    public void showHistory() {
        System.out.println("=== Chat History (" + roomName + ") ===");
        if (chatHistory.isEmpty()) {
            System.out.println("(Belum ada riwayat pesan)");
        } else {
            for (int i = 0; i < chatHistory.size(); i++) {
                System.out.println((i + 1) + ". " + chatHistory.get(i));
            }
        }
    }
}