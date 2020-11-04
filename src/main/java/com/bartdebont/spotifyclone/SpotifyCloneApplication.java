package com.bartdebont.spotifyclone;

import com.bartdebont.spotifyclone.spotify.ClientCredentialsSpotify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
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

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/spotify/v1/**"))
                .apis(RequestHandlerSelectors.basePackage("com.bartdebont"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Spotify clone API",
                "API for spotify clone",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Bart de Bont", "http://google.com", "bart_b@hotmail.nl"),
                "API License",
                "http://google.com",
                Collections.emptyList()
        );
    }

}
