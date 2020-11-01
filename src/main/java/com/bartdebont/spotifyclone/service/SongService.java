package com.bartdebont.spotifyclone.service;

import com.bartdebont.spotifyclone.exception.ResourceNotFoundException;
import com.bartdebont.spotifyclone.model.Song;
import com.bartdebont.spotifyclone.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song getSong(Long id) throws ResourceNotFoundException {
        return songRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Song with id %s could not be found", id)));
    }

    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        for (Song song:
             songRepository.findAll()) {
            songs.add(song);
        }
        return songs;
    }
}
