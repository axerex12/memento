package Facade;

public class Main {
    public static void main(String[] args) {
        Facade api = new Facade();

        try {
            String joke = api.getAttributeValueFromJson("https://api.chucknorris.io/jokes/random", "value");
            System.out.println("Random Chuck Norris Joke: " + joke);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
