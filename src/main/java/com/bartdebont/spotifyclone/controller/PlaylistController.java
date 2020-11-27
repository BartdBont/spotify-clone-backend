package com.bartdebont.spotifyclone.controller;

import com.bartdebont.spotifyclone.exception.ResourceNotFoundException;
import com.bartdebont.spotifyclone.model.Playlist;
import com.bartdebont.spotifyclone.model.Song;
import com.bartdebont.spotifyclone.service.PlaylistService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spotify/v1/")
@CrossOrigin(origins = "*")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("playlists")
    @ApiOperation(value = "Gets all playlists")
    public ResponseEntity<List<Playlist>> getPlaylists() {
        return ResponseEntity.ok(playlistService.getPlaylists());
    }

    @PostMapping("playlists")
    @ApiOperation(value = "Creates an empty playlist")
    public ResponseEntity<Object> createPlaylist(@RequestBody Playlist playlist) {
        playlistService.createPlaylist(playlist);
        return new ResponseEntity<>("Playlist is created successfully", HttpStatus.CREATED);
    }

    @GetMapping("playlists/{id}")
    @ApiOperation(value = "Gets a playlist by id", notes = "Provide an id to look up specific playlists")
    public ResponseEntity<Object> getPlaylistById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(playlistService.getPlaylist(id));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("playlists/{id}")
    @ApiOperation(value = "Edits the content/details of a playlist by id", notes = "Provide an id to edit a specific playlist")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {
        return ResponseEntity.ok(playlistService.updatePlaylist(id, playlist));
    }

    @DeleteMapping("playlists/{id}")
    @ApiOperation(value = "Deletes a playlist by id", notes = "Provide an id to delete a specific playlist")
    public ResponseEntity<Map<String, Boolean>> deletePlaylist(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.deletePlaylist(id));
    }

    @PostMapping("playlists/{id}/songs")
    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable Long id, @RequestBody Song song) {
        return ResponseEntity.ok(playlistService.addSongToPlaylist(id, song));
    }

    @DeleteMapping("playlists/{id}/songs")
    public ResponseEntity<Boolean> deleteSongFromPlaylist(@PathVariable Long id, @RequestBody Song song) {
        return ResponseEntity.ok(playlistService.deleteSongFromPlaylist(id, song));
    }

}
