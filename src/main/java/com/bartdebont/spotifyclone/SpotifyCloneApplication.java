package com.bartdebont.spotifyclone;

import com.bartdebont.spotifyclone.spotify.ClientCredentialsSpotify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpotifyCloneApplication {

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    public static void main(String[] args) {
        ClientCredentialsSpotify clientCredentialsSpotify = new ClientCredentialsSpotify();
        clientCredentialsSpotify.clientCredentials_Sync();
        SpringApplication.run(SpotifyCloneApplication.class, args);
    }

}
