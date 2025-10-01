package Mediator;

public interface Mediator {
    void sendMessage(String message, String sender, String receiver);
    void addUser(User user);
}
