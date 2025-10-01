package Mediator;
import java.util.HashMap;
import java.util.Map;

public class MediatorImpl implements Mediator {
    private Map<String, User> users = new HashMap<>();


    @Override
    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public void sendMessage(String message, String sender, String receiver) {

        User receiverUser = users.get(receiver);
        receiverUser.receiveMessage(new Message(message, sender, receiver));

    }

   
}
