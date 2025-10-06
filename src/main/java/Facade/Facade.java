package Facade;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Facade {
    public String getAttributeValueFromJson(String urlString, String attributeName) throws IllegalArgumentException, IOException {
        JokeClient client = new JokeClient();

        try {
            String jsonResponse = client.fetchJson(urlString);

            return client.extractAttribute(jsonResponse, attributeName);

        } catch (IOException e) {
            throw new IOException("Error fetching JSON from URL: " + e.getMessage());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error parsing JSON response: " + e.getMessage());
        }

    }
  

  
        
    
}
