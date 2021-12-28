package com.shahintraining.albums.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        record Photo(Long photoNumber, Date time, String shooter) {
        }
        Photo photo1 = new Photo(1L, new Date(System.currentTimeMillis()), "shahin");
        Photo photo2 = new Photo(2L, new Date(System.currentTimeMillis()), "ruherim");
        return new ResponseEntity<>(List.of(photo1, photo2), HttpStatus.OK);
    }

}
