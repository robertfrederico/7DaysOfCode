package br.com.alura.DaysOfCode;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static final String API_KEY = "";
    private static final String URL = "https://imdb-api.com/ptr/API/";
    private static final String END_POINT_250_MOVIES = "Top250Movies/";

    public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException, InterruptedException {
        SpringApplication.run(Application.class, args);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL + END_POINT_250_MOVIES + API_KEY))
                .GET()
                .timeout(Duration.ofSeconds(15))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

             HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
             System.out.println(response.body());

    }

}
