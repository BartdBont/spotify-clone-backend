package com.bartdebont.spotifyclone.util;

import com.bartdebont.spotifyclone.spotify.ClientCredentialsSpotify;

public class SpotifyTokenUtil {

    public static void checkIfTokenExpired(Exception e) {
        if (e.getMessage().equalsIgnoreCase("the access token expired")) {
            ClientCredentialsSpotify.clientCredentials_Sync();
        }
    }
}
