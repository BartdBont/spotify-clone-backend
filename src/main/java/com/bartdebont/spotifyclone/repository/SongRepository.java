package com.bartdebont.spotifyclone.repository;

import com.bartdebont.spotifyclone.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    Song findBySpotifyId(String spotifyId);
    Song findByYoutubeId(String youtubeId);
}
