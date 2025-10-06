package Facade;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.simple.JSONObject; // add Maven dependency for JSON.simple
import org.json.simple.parser.JSONParser; // add Maven dependency for JSON.simple
import org.json.simple.parser.ParseException;


public class JokeClient {
    public String getRandomJoke() throws Exception {
        String jsonResult = fetchJson("https://api.chucknorris.io/jokes/random");
        return extractAttribute(jsonResult, "value");
    }

    public String fetchJson(String urlString) throws IOException {
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

    public String extractAttribute(String json, String attributeName)
            throws ParseException, IllegalArgumentException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);

        Object value = jsonObject.get(attributeName);
        if (value == null) {
            throw new IllegalArgumentException("Attribute '" + attributeName + "' not found in JSON response.");
        }
        return value.toString();
    }

    public static void main(String[] args) {
        JokeClient client = new JokeClient();
        try {
            String jokeText = client.getRandomJoke();
            System.out.println(jokeText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
