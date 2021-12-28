package com.shahintraining.resourceserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sh.khalajestanii
 * @project resource-server
 * @since 12/15/2021
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping
    public Jwt getToken(@AuthenticationPrincipal Jwt jwt){
//        return Collections.singletonMap("principal",jwt);
        return jwt;
    }

}
