package com.bartdebont.spotifyclone.spotify.beans;

import com.bartdebont.spotifyclone.spotify.SearchTracksExample;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TracksBean {
    @Bean
    SearchTracksExample searchTracksExample() {
        return new SearchTracksExample();
    }
}
