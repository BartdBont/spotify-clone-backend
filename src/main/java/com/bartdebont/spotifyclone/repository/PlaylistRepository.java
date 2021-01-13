package com.bartdebont.spotifyclone.repository;

import com.bartdebont.spotifyclone.model.Customer;
import com.bartdebont.spotifyclone.model.Playlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
    Iterable<Playlist> findAllByOwner(Customer owner);
}
