package com.bartdebont.spotifyclone.service;

import com.bartdebont.spotifyclone.exception.ResourceNotFoundException;
import com.bartdebont.spotifyclone.model.Customer;
import com.bartdebont.spotifyclone.model.Playlist;
import com.bartdebont.spotifyclone.model.Song;
import com.bartdebont.spotifyclone.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    private final SongService songService;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, SongService songService) {
        this.playlistRepository = playlistRepository;
        this.songService = songService;
    }

    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Playlist getPlaylist(Long id) throws ResourceNotFoundException {
        return playlistRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Playlist with id: %o could not be found", id)));
    }

    public List<Playlist> getPlaylists(Customer owner) {
        List<Playlist> playlists = new ArrayList<>();
        for (Playlist playlist:
             playlistRepository.findAllByOwner(owner)) {
            playlists.add(playlist);
        }
        return playlists;
    }

    public Map<String, Boolean> deletePlaylist(Long id) {
        Playlist playlistToDelete = getPlaylist(id);
        playlistRepository.delete(playlistToDelete);
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

        return playlistRepository.save(playlist);
    }

    public Playlist addSongToPlaylist(Long playlistId, Song song) {
        Playlist playlist = getPlaylist(playlistId);
        List<Song> currentSongs;

        if (!playlist.getSongs().isEmpty()){
            currentSongs = playlist.getSongs();
        } else {
            currentSongs = new ArrayList<>();
        }

        Song newSong = hasSongBeenSavedBefore(song);
        songService.addSong(newSong);

        currentSongs.add(newSong);
        playlist.setSongs(currentSongs);

        return playlistRepository.save(playlist);
    }

    private Song hasSongBeenSavedBefore(Song song) {
        //checks if song has been saved
        Song songFromDb = songService.hasSongBeenSaved(song.getSpotifyId());
        Song newSong = new Song();
        if (songFromDb == null) {
            newSong = songService.addSong(song);
        } else {
            newSong = song;
        }
        return newSong;
    }

    public Boolean deleteSongFromPlaylist(Long playlistId, String spotifyId) {
        Playlist playlist = getPlaylist(playlistId);

        //splits the lists into two
        List<Song> songsWithoutSpotifyId = playlist.getSongs().stream().filter(o -> o.getSpotifyId()==null).collect(Collectors.toList());
        List<Song> songsWithSpotifyId = playlist.getSongs().stream().filter(o -> o.getSpotifyId()!=null).collect(Collectors.toList());

        playlist.setSongs(null);

        //deletes song from playlists where spotifyId matches
        if (songsWithSpotifyId.stream().filter(o -> o.getSpotifyId().equals(spotifyId)).findFirst().isPresent()){
            songsWithSpotifyId.removeIf(o -> o.getSpotifyId().equals(spotifyId));

            //merges the 2 lists back into 1 and saves
            playlist.setSongs(Stream.concat(songsWithoutSpotifyId.stream(), songsWithSpotifyId.stream()).collect(Collectors.toList()));
            playlistRepository.save(playlist);
            return true;
        }
        return false;
    }

    public Boolean delSongFromPlaylist(Long playlistId, Long songId) {
        Playlist playlist = getPlaylist(playlistId);

        List<Song> newSongs = new ArrayList<>();

        for (Song s :
                playlist.getSongs()) {
            if (!s.getId().equals(songId)) {
                newSongs.add(s);

            }
        }
        playlist.setSongs(newSongs);
        playlistRepository.save(playlist);
        return true;
    }
}
