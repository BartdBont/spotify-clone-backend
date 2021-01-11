package com.bartdebont.spotifyclone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Artist> artists;

    private String image;

    private String name;

    private String release_date;


    @OneToMany(mappedBy = "album", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Song> songs;

    public Album(List<Artist> artists, String image, String name, String release_date, List<Song> songs) {
        this.artists = artists;
        this.image = image;
        this.name = name;
        this.release_date = release_date;
        this.songs = songs;
    }

    public Album(List<Artist> artists, String image, String name, String release_date) {
        this.artists = artists;
        this.image = image;
        this.name = name;
        this.release_date = release_date;
    }

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
