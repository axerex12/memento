package Mediator;

public class Message {
    private String content, sender, receiver;


    public Message(String content, String sender, String receiver)
    {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }
}