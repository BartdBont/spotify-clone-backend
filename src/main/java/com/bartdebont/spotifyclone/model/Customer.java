package com.bartdebont.spotifyclone.model;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String display_name;

    private String email;

    private String password;

    private File image;

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    private List<Playlist> playlists;

    public Customer(String display_name, String email, String password, File image, List<Playlist> playlists) {
        this.display_name = display_name;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
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

