package Mediator;

public class User {
    private String username;
    private Mediator mediator;
    private UserController controller;

    public User(String username, Mediator mediator) {
        this.username = username;
        this.mediator = mediator;
        mediator.addUser(this);
    }

    public String getName() {
        return username;
    }
    public void setController(UserController controller) {
        this.controller = controller;
    }
    public void sendMessage(String message, String receiver) {
        mediator.sendMessage(message, this.username, receiver);
    }
    public void receiveMessage(Message message) {
        if (controller != null) {
            controller.displayMessage(message);
        }
    }
    
}
