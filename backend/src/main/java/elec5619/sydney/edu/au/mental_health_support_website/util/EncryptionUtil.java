package elec5619.sydney.edu.au.mental_health_support_website.util;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class EncryptionUtil {
    public static String encrypt(String password) throws IOException, InterruptedException {
        String apiUrl = "https://encryptor.p.rapidapi.com/static_encryption?data=" + password;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("X-RapidAPI-Key", "d318270e6amsh1c8c0be8a527338p1b62c6jsn78c102a691b8")
                .header("X-RapidAPI-Host", "encryptor.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObject = new JSONObject(response.body());
        Map<String, Object> map = jsonObject.toMap();
        String encrypted = (String) map.get("encrypted_string");
        return encrypted;
    }
}
