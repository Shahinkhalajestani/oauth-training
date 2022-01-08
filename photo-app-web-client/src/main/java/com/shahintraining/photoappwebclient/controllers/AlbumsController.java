package com.shahintraining.photoappwebclient.controllers;

import com.shahintraining.photoappwebclient.response.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AlbumsController {


    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName());

        String jwtAccessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();

        System.out.println("AccessToken Is = " + jwtAccessToken);


        System.out.println("Princibal =" + principal);

        OidcIdToken idToken = principal.getIdToken();

        String tokenValue = idToken.getTokenValue();

        System.out.println("token value =" + tokenValue);

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


