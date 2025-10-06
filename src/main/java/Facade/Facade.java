package Facade;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Facade {
    public String getAttributeValueFromJson(String urlString, String attributeName) throws IllegalArgumentException, IOException {

        try {
            String jsonResponse = fetchJson(urlString);

            return extractAttribute(jsonResponse, attributeName);

        } catch (IOException e) {
            throw new IOException("Error fetching JSON from URL: " + e.getMessage());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error parsing JSON response: " + e.getMessage());
        }

    }

    private String fetchJson(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } finally {
            con.disconnect();
        }
    }

     private String extractAttribute(String json, String attributeName)
            throws ParseException, IllegalArgumentException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);

        Object value = jsonObject.get(attributeName);
        if (value == null) {
            throw new IllegalArgumentException("Attribute '" + attributeName + "' not found in JSON response.");
        }
        return value.toString();
    }

  
        
    
}
