package com.bartdebont.spotifyclone.controller;

import com.bartdebont.spotifyclone.service.AlbumService;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spotify/v1/")
@CrossOrigin(origins = "*")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("albums/{id}")
    public Album getAlbum(@PathVariable String id) {
        return albumService.getAlbumFromSpotify(id);
    }

    @GetMapping("albums/{id}/songs")
    public TrackSimplified[] getAlbumSongs(@PathVariable String id) {
        return albumService.getAlbumSongs(id);
    }

    @GetMapping("albums/recent")
    public AlbumSimplified[] getRecentAlbums() {
        return albumService.getRecentAlbums();
    }
}
