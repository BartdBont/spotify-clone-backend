//package com.bartdebont.spotifyclone;
//
//import com.bartdebont.spotifyclone.model.Album;
//import com.bartdebont.spotifyclone.model.Artist;
//import com.bartdebont.spotifyclone.model.Song;
//import com.bartdebont.spotifyclone.repository.SongRepository;
//import org.checkerframework.checker.units.qual.A;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class SongRepositoryIntegrationTest {
//
//    @Autowired
//    private SongRepository songRepository;
//
//    @Test
//    public void whenCalledSave_thenCorrectNumberOfSongs() {
//        List<Artist> artists = new ArrayList<>();
//        artists.add(new Artist());
//        songRepository.save(new Song("505", 3000, new Album(), artists));
//
//        List<Song> songs = (List<Song>) songRepository.findAll();
//
//        Assert.assertEquals(songs.size(), 1);
//     }
//}
