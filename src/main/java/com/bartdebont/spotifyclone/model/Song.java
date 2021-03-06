package com.bartdebont.spotifyclone.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String spotifyId;
    private String isrc;
    private String youtubeId;

    private String previewUrl;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Album album;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Artist> artists;

    public Song() {
    }

    public Song(String name, String previewUrl, Album album, List<Artist> artists, String spotifyId, String isrc) {
        this.name = name;
        this.previewUrl = previewUrl;
        this.album = album;
        this.artists = artists;
        this.spotifyId = spotifyId;
        this.isrc = isrc;
    }

    public Song(String name, String spotifyId, String isrc, String youtubeId, String previewUrl, Album album, List<Artist> artists) {
        this.name = name;
        this.spotifyId = spotifyId;
        this.isrc = isrc;
        this.youtubeId = youtubeId;
        this.previewUrl = previewUrl;
        this.album = album;
        this.artists = artists;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
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
