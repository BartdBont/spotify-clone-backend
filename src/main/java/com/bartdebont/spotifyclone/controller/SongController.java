package com.bartdebont.spotifyclone.controller;

import com.bartdebont.spotifyclone.exception.ResourceNotFoundException;
import com.bartdebont.spotifyclone.model.Song;
import com.bartdebont.spotifyclone.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/spotify/v1/")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("songs")
    public Song addSong(@RequestBody Song song) {
        return songService.addSong(song);
    }

    @GetMapping("songs/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(songService.getSong(id));
    }

    @GetMapping("songs")
    public ResponseEntity<List<Song>> getSongs() {
        return ResponseEntity.ok(songService.getSongs());
    }

    @GetMapping(value = "songs", params = "name")
    public ResponseEntity<List<Song>> getSongsByName(String name) {
        return ResponseEntity.ok(songService.getSongsByName(name));
    }
}
