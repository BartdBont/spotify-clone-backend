package com.bartdebont.spotifyclone.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class SearchTracksExample {

    private static final SpotifyApi spotifyApi = ClientCredentialsSpotify.clientCredentials_Sync();

    private static Track[] searchTracks_Sync(String q) {
        try {
            SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q)
                    .build();
            final Paging<Track> trackPaging = searchTracksRequest.execute();

            System.out.println("Total: " + trackPaging.toString());
            return trackPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        searchTracks_Sync("Abba");
    }
}
