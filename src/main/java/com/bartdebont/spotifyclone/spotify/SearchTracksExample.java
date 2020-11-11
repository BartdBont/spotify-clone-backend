package com.bartdebont.spotifyclone.spotify;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class SearchTracksExample {

    private static final SpotifyApi spotifyApi = ClientCredentialsSpotify.clientCredentials_Sync();

    public static Track[] searchTracks_Sync(String q) {
        try {
            SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q).market(CountryCode.US)
                    .build();
            final Paging<Track> trackPaging = searchTracksRequest.execute();

            System.out.println("Total: " + trackPaging.toString());
            return trackPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static Artist getArtistById(String id) {
        try {
            final Artist artist = spotifyApi.getArtist(id).build().execute();

            System.out.println("Name: " + artist.getName());
            return artist;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        searchTracks_Sync("Abba");
    }
}
