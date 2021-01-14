package com.bartdebont.spotifyclone.controller;

import com.bartdebont.spotifyclone.model.Album;
import com.bartdebont.spotifyclone.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Album getAlbum(@PathVariable Long id) {
        return albumService.getAlbum(id);
    }
}
