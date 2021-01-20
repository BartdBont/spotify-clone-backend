package com.bartdebont.spotifyclone.service;

import com.bartdebont.spotifyclone.exception.ResourceNotFoundException;
import com.bartdebont.spotifyclone.model.Album;
import com.bartdebont.spotifyclone.repository.AlbumRepository;
import com.bartdebont.spotifyclone.spotify.GetAlbums;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album getAlbum(Long id) throws ResourceNotFoundException {
        return albumRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Album with id: %2 could not be found", id)));
    }

    public AlbumSimplified[] getRecentAlbums() {
        return GetAlbums.getAlbums();
    }

    public com.wrapper.spotify.model_objects.specification.Album getAlbumFromSpotify(String id) {
        return GetAlbums.getAlbum(id);
    }

    public TrackSimplified[] getAlbumSongs(String id) {
        return GetAlbums.getAlbumSongs(id);
    }
}
