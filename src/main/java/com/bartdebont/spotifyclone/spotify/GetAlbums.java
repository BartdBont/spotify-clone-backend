package com.bartdebont.spotifyclone.spotify;

import com.bartdebont.spotifyclone.util.SpotifyTokenUtil;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumsTracksRequest;
import com.wrapper.spotify.requests.data.browse.GetListOfNewReleasesRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class GetAlbums {

    private static final SpotifyApi spotifyApi = ClientCredentialsSpotify.clientCredentials_Sync();

    public static AlbumSimplified[] getAlbums() {
        try {
            GetListOfNewReleasesRequest getListOfNewReleasesRequest = spotifyApi.getListOfNewReleases().build();
            final Paging<AlbumSimplified> albums = getListOfNewReleasesRequest.execute();
            return albums.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            SpotifyTokenUtil.checkIfTokenExpired(e);
            return null;
        }
    }

    public static Album getAlbum(String id) {
        try {
            GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(id).build();
            return getAlbumRequest.execute();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            SpotifyTokenUtil.checkIfTokenExpired(e);
            return null;
        }
    }

    public static TrackSimplified[] getAlbumSongs(String id) {
        try {
            GetAlbumsTracksRequest getAlbumsTracksRequest = spotifyApi.getAlbumsTracks(id).build();
            final Paging<TrackSimplified> trackSimplifiedPaging = getAlbumsTracksRequest.execute();

            return trackSimplifiedPaging.getItems();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            SpotifyTokenUtil.checkIfTokenExpired(e);
            return null;
        }
    }
}
