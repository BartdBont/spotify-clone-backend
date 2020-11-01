package com.bartdebont.spotifyclone.service;

import com.bartdebont.spotifyclone.exception.ResourceNotFoundException;
import com.bartdebont.spotifyclone.model.Playlist;
import com.bartdebont.spotifyclone.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Playlist getPlaylist(Long id) throws ResourceNotFoundException {
        return playlistRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Playlist with id: %2 could not be found", id)));
    }

    public List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (Playlist playlist:
             playlistRepository.findAll()) {
            playlists.add(playlist);
        }
        return playlists;
    }

    public Map<String, Boolean> deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", true);
        return result;
    }

    public Playlist updatePlaylist(Long id, Playlist playlistDetails) {
        Playlist playlist = getPlaylist(id);

        playlist.setName(playlistDetails.getName());
        playlist.setOwner(playlistDetails.getOwner());
        playlist.setSongs(playlistDetails.getSongs());
        playlist.setVisible(playlistDetails.getVisible());

        Playlist updatedPlaylist = playlistRepository.save(playlist);

        return updatedPlaylist;
    }
}
