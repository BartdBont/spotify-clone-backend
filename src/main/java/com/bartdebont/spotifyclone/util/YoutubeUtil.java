package com.bartdebont.spotifyclone.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

public class YoutubeUtil {
    private static final String API_KEY = "AIzaSyAs14FzwXYnfSjKt6J2wmGAL4g22CgTUtc";
    private static final int MAX_RESULTS = 1;

    public static String getYoutubeId(String search) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String get = MessageFormat.format("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults={0}&q={1}&type=video&key={2}",
                MAX_RESULTS,
                search,
                API_KEY).replaceAll(" ", "%20");

        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(get))
                    .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response.body());
            String videoId = jsonNode.get("items").findValue("id").findValue("videoId").asText();
            return videoId;
        }
        catch (Throwable e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
