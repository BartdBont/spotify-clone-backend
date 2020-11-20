package com.bartdebont.spotifyclone.spotify;

import com.bartdebont.spotifyclone.util.SpotifyTokenUtil;
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

            return trackPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            SpotifyTokenUtil.checkIfTokenExpired(e);
            return null;
        }
    }

    public static Artist getArtistById(String id) {
        try {
            final Artist artist = spotifyApi.getArtist(id).build().execute();

            return artist;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            SpotifyTokenUtil.checkIfTokenExpired(e);
            return null;
        }
    }
}
