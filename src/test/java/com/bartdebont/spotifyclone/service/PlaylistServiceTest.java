package com.bartdebont.spotifyclone.service;

import com.bartdebont.spotifyclone.model.*;
import com.bartdebont.spotifyclone.repository.PlaylistRepository;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistServiceTest {

    private PlaylistService playlistService;
    private PlaylistRepository playlistRepository;
    private SongService songService;
    private List<Song> songs;
    private Customer owner;
    private Playlist playlist;


    @Before
    public void before() throws Exception {
        playlistRepository = mock(PlaylistRepository.class);
        songService = mock(SongService.class);
        playlistService = new PlaylistService(playlistRepository, songService);

        songs = new ArrayList<>();
        owner = new Customer("Robin", "aan de Stegge", "test",null , null);
        playlist = new Playlist("vibes", "playlist to vibe to", owner , true, null);
    }

    /**
     *
     * Method: createPlaylist(Playlist playlist)
     *
     */
    @Test
    public void testCreatePlaylist() throws Exception {

        //execute
        playlistService.createPlaylist(playlist);
    }

    /**
     *
     * Method: getPlaylist(Long id)
     *
     */
    @Test
    public void testGetPlaylist() throws Exception {

        //Mocking
        when(playlistRepository.findById(1L)).thenReturn(java.util.Optional.of(playlist));

        //execute
        var playlistResult = playlistService.getPlaylist(1L);

        //assert
        assertNotNull(playlistResult);
    }

    /**
     *
     * Method: getPlaylists(Customer owner)
     *
     */
    @Test
    public void testGetPlaylists() throws Exception {
        //setup
        List<Song> songs = new ArrayList<>();
        List<Playlist> playlistsToGet = new ArrayList<>();
        Customer owner = new Customer("Robin", "aan de Stegge", "test",null , playlistsToGet);
        playlistsToGet.add(new Playlist("vibes", "playlist to vibe to", owner , true, songs));
        playlistsToGet.add(new Playlist("sad", "for sad vibes", owner , true, songs));

        //mocking
        when(playlistRepository.findAllByOwner(owner)).thenReturn(playlistsToGet);

        //execute
        List<Playlist> playlists = playlistService.getPlaylists(owner);

        //assert
        assertEquals(2, playlists.size());
    }

    /**
     *
     * Method: deletePlaylist(Long id)
     *
     */
    @Test
    public void testDeletePlaylist() throws Exception {
        //setup
        playlistService.createPlaylist(playlist);

        //mocking
        when(playlistRepository.findById(1L)).thenReturn(java.util.Optional.of(playlist));

        //execute
        playlistService.deletePlaylist(1L);

        //assert
        assertEquals(0, playlistService.getPlaylists(owner).size());
    }

    /**
     *
     * Method: updatePlaylist(Long id, Playlist playlistDetails)
     *
     */
    @Test
    public void testUpdatePlaylist() throws Exception {
        //setup
        playlistService.createPlaylist(playlist);
        Playlist updatedPlaylist = playlist;
        updatedPlaylist.setName("Rock");

        //mocking
        when(playlistRepository.findById(1L)).thenReturn(java.util.Optional.of(playlist));

        //execute
        playlistService.updatePlaylist(1L, updatedPlaylist);

        //assert
        assertEquals("Rock", playlistService.getPlaylist(1L).getName());
    }

    /**
     *
     * Method: addSongToPlaylist(Long playlistId, Song song)
     *
     */
    @Test
    public void testAddSongToPlaylist() throws Exception {
        //setup
        List<Artist> artists = new ArrayList<>();
        Song song = new Song("Rockstar", "", new Album(), artists, "", "");

        //mocking
        when(playlistRepository.findById(1L)).thenReturn(java.util.Optional.of(playlist));

        //execute
        playlist.setSongs(new ArrayList<>());
        playlistService.addSongToPlaylist(1L, song);

        //assert
        assertEquals(1, playlistService.getPlaylist(1L).getSongs().size());
    }

}
