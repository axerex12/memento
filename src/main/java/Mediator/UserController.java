package Mediator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UserController {
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField messageField;
    @FXML
    private TextField recipientField;
    @FXML
    private Button sendButton;

    private User user;

    public void setUser(User user) {
        this.user = user;
        user.setController(this);
    }

    @FXML
    private void initialize() {
        sendButton.setOnAction(event -> {
            String message = messageField.getText();
            String recipient = recipientField.getText();
            if (!message.isEmpty() && !recipient.isEmpty()) {
                user.sendMessage(message, recipient);
                chatArea.appendText("Me to " + recipient + ": " + message + "\n");
                messageField.clear();
            }
        });
    }

    public void displayMessage(Message message) {
        chatArea.appendText(message.getSender() + " to Me: " + message.getContent() + "\n");
    }

    
}
