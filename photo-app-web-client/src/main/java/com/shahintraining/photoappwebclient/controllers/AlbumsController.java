package com.shahintraining.photoappwebclient.controllers;

import com.shahintraining.photoappwebclient.response.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class AlbumsController {


    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    private RestTemplate restTemplate;

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

        String url = "http://localhost:8095/albums";

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization","Bearer "+jwtAccessToken);
        
        HttpEntity<List<Album>> requestEntity = new HttpEntity<>(headers);


        ResponseEntity<List<Album>> responseEntity = restTemplate
                .exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Album>>() {
        });
        model.addAttribute("albums", responseEntity.getBody());
        return "albums";
    }

}


