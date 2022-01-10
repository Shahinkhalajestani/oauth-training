package com.shahintraining.albums.controllers;

import com.shahintraining.albums.response.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author sh.khalajestanii
 * @project albums
 * @since 12/25/2021
 */
@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private record Photo(Long photoNumber, Date time, String shooter) {
    }

    @GetMapping
    public ResponseEntity<?> getAlbums() {
        Album album1 = Album.builder().albumId("1")
                .albumUrl("http://localhost:8026/albums/1")
                .userId("shahin1")
                .albumDescription("first Photo album")
                .albumTitle("number one").build();

        Album album2 = Album.builder().albumId("1")
                .albumUrl("http://localhost:8026/albums/2")
                .userId("shahin2")
                .albumDescription("second Photo album")
                .albumTitle("number two").build();

        Album album3 = Album.builder().albumId("3")
                .albumUrl("http://localhost:8026/albums/3")
                .userId("shahin3")
                .albumDescription("third Photo album")
                .albumTitle("number three").build();
        List<Album> albums = List.of(album1, album2, album3);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

}
