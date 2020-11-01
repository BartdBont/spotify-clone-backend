package com.bartdebont.spotifyclone.repository;

import com.bartdebont.spotifyclone.model.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
}
