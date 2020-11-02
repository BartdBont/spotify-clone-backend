package com.bartdebont.spotifyclone.repository;

import com.bartdebont.spotifyclone.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
}
