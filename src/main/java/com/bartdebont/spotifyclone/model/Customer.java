package com.bartdebont.spotifyclone.model;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private File image;

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    private List<Playlist> playlists;

    public Customer(String username, String email, String password, File image, List<Playlist> playlists) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.playlists = playlists;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}

