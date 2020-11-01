package com.bartdebont.spotifyclone.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int duration_ms;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    private Album album;

    @ManyToMany
    private List<Artist> artists;

    public Song(String name, int duration_ms, Album album, List<Artist> artists) {
        this.name = name;
        this.duration_ms = duration_ms;
        this.album = album;
        this.artists = artists;
    }

    public Song() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artist> getArtist() {
        return artists;
    }

    public void setArtist(List<Artist> artists) {
        this.artists = artists;
    }
}
