package com.bartdebont.spotifyclone.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class GetTrackExample {
    private static final String id = "01iyCAUm8EvOFqVWYJ3dVX";

    private static final SpotifyApi spotifyApi = ClientCredentialsSpotify.clientCredentials_Sync();

    private static final GetTrackRequest getTrackRequest = spotifyApi.getTrack(id)
            .build();

    private static void getTrack_Sync() {
        try {
            final Track track = getTrackRequest.execute();

            System.out.println("Total: " + track.toString());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getTrack_Sync();
    }
}
