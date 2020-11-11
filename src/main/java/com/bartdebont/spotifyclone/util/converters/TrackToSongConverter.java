package com.bartdebont.spotifyclone.util.converters;

import com.bartdebont.spotifyclone.model.Album;
import com.bartdebont.spotifyclone.model.Artist;
import com.bartdebont.spotifyclone.model.Song;
import com.bartdebont.spotifyclone.spotify.SearchTracksExample;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackToSongConverter {

    public static Song ConvertTrackToSong(Track track) {
        return new Song(track.getName(), track.getDurationMs(), track.getPreviewUrl(),
                ConvertAlbumSimplifiedToAlbum(track.getAlbum()),
                ConvertArtistsSimplifiedToArtists(track.getArtists()));
    }

    public static Album ConvertAlbumSimplifiedToAlbum(AlbumSimplified albumSimplified) {
        return new Album(ConvertArtistsSimplifiedToArtists(albumSimplified.getArtists()), albumSimplified.getImages()[0].getUrl(), albumSimplified.getName(), albumSimplified.getReleaseDate());
    }

    public static List<Artist> ConvertArtistsSimplifiedToArtists(ArtistSimplified[] artistsSimplified) {
        List<Artist> artists = new ArrayList<>();
        for (ArtistSimplified item:
             artistsSimplified) {
            //gets non-simplified artist object from api
            com.wrapper.spotify.model_objects.specification.Artist artist = SearchTracksExample.getArtistById(item.getId());
            //converts to my artist object and adds to list
            if (artist.getGenres().length > 0 && artist.getImages().length > 0) {
                artists.add(new Artist(artist.getGenres()[0], artist.getImages()[0].getUrl(), artist.getName()));
            } else {
                artists.add(new Artist("none", "none", artist.getName()));
            }

        }
        return artists;
    }

}
