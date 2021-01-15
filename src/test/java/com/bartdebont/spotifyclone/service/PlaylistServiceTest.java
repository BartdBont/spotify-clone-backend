package com.bartdebont.spotifyclone.service;

import com.bartdebont.spotifyclone.model.Customer;
import com.bartdebont.spotifyclone.model.Playlist;
import com.bartdebont.spotifyclone.model.Song;
import com.bartdebont.spotifyclone.repository.PlaylistRepository;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaylistServiceTest {

    private PlaylistService playlistService;
    private PlaylistRepository playlistRepository;
    private SongService songService;


    @Before
    public void before() throws Exception {
        playlistRepository = mock(PlaylistRepository.class);
        songService = mock(SongService.class);
        playlistService = new PlaylistService(playlistRepository, songService);
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: createPlaylist(Playlist playlist)
     *
     */
    @Test
    public void testCreatePlaylist() throws Exception {
        //setup
        List<Song> songs = new ArrayList<>();
        var playlist = new Playlist("vibes", "playlist to vibe to", new Customer(), true, songs);

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
        //setup
        List<Song> songs = new ArrayList<>();
        var playlist = new Playlist("vibes", "playlist to vibe to", new Customer(), true, songs);

        //Mocking
        when(playlistRepository.findById(1L)).thenReturn(java.util.Optional.of(playlist));

        //execute
        var playlistResult = playlistService.getPlaylist(1L);

        //assert
        assertTrue(playlistResult != null);
    }

    /**
     *
     * Method: getPlaylists(Customer owner)
     *
     */
    @Test
    public void testGetPlaylists() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: deletePlaylist(Long id)
     *
     */
    @Test
    public void testDeletePlaylist() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: updatePlaylist(Long id, Playlist playlistDetails)
     *
     */
    @Test
    public void testUpdatePlaylist() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: addSongToPlaylist(Long playlistId, Song song)
     *
     */
    @Test
    public void testAddSongToPlaylist() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: deleteSongFromPlaylist(Long playlistId, String spotifyId)
     *
     */
    @Test
    public void testDeleteSongFromPlaylist() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: delSongFromPlaylist(Long playlistId, Long songId)
     *
     */
    @Test
    public void testDelSongFromPlaylist() throws Exception {
//TODO: Test goes here...
    }


    /**
     *
     * Method: hasSongBeenSavedBefore(Song song)
     *
     */
    @Test
    public void testHasSongBeenSavedBefore() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = PlaylistService.getClass().getMethod("hasSongBeenSavedBefore", Song.class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }
}
