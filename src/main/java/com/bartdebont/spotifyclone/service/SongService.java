package com.bartdebont.spotifyclone.service;

import com.bartdebont.spotifyclone.exception.ResourceNotFoundException;
import com.bartdebont.spotifyclone.model.Song;
import com.bartdebont.spotifyclone.repository.SongRepository;
import com.bartdebont.spotifyclone.spotify.SearchTracksExample;
import com.bartdebont.spotifyclone.util.YoutubeUtil;
import com.bartdebont.spotifyclone.util.converters.TrackToSongConverter;
import com.wrapper.spotify.model_objects.specification.Track;
import org.hibernate.NonUniqueResultException;
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
        Song songFromDb = hasSongBeenSaved(song.getSpotifyId());
        if (songFromDb == null)
            return songRepository.save(song);
        else
            return song;
    }

    public Song saveSpotifySong(Track track) {
        return songRepository.save(TrackToSongConverter.convertTrackToSong(track));
    }

    public List<Song> getSongs() {
        List<Song> songs = new ArrayList<>();
        for (Song song:
             songRepository.findAll()) {
            songs.add(song);
        }
        return songs;
    }

    public List<Song> getSongsByName(String name) {
        Boolean alreadySaved = false;
        List<Song> songs = new ArrayList<>();
        Iterable<Song> savedSongs = songRepository.findAll();
        Track[] result = SearchTracksExample.searchTracks_Sync(name);
        if (result != null) {
            for (Track track:
                    result) {
                for (Song songFromDb:
                     savedSongs) {
                    if (songFromDb.getSpotifyId().equals(track.getId())){
                        songs.add(songFromDb);
                        alreadySaved = true;
                        break;
                    }
                }
                if (!alreadySaved) {
                    Song song = TrackToSongConverter.convertTrackToSong(track);
                    songs.add(song);
                }
                alreadySaved = false;
            }
        }
        return songs;
    }

    public boolean doesSongHaveYoutubeId(String youtubeId) {
        return songRepository.findByYoutubeId(youtubeId) != null;
    }

    public Song hasSongBeenSaved(String spotifyId) {
        try {
            return songRepository.findBySpotifyId(spotifyId);
        } catch (NonUniqueResultException | javax.persistence.NonUniqueResultException e) {
            return new Song();
        }

    }

    public String getYoutubeId(Song song) {
        try {
            if (doesSongHaveYoutubeId(song.getYoutubeId())) {
                return songRepository.findByYoutubeId(song.getYoutubeId()).getYoutubeId();
            }
            String youtubeId = YoutubeUtil.getYoutubeId(song.getIsrc());
            if (youtubeId == null) {
                return YoutubeUtil.getYoutubeId(song.getName() + " " + song.getArtist());
            }
            return youtubeId;
        } catch (Exception e) {
            return null;
        }
    }
}
