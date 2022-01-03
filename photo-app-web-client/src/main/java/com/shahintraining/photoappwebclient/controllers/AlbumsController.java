package com.shahintraining.photoappwebclient.controllers;

import com.shahintraining.photoappwebclient.response.Album;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AlbumsController {


    @GetMapping("/albums")
    public String getAlbums(Model model) {
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
        model.addAttribute("albums", albums);
        return "albums";
    }

}


