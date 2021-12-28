package com.shahintraining.resourceserver.controller;

import com.shahintraining.resourceserver.domain.UserDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

/**
 * @author sh.khalajestanii
 * @project resource-server
 * @since 12/11/2021
 */
@RestController
@RequestMapping("/users")
public class UsersController {


    @GetMapping(value = "/status/check")
    public String status(){
        return "working ... ";
    }

//    @Secured("ROLE_developer")
    @PreAuthorize("hasRole('developer') or #userId== #jwt.subject")
    @DeleteMapping(value = "/delete/{userId}")
    public String deleteUser(@AuthenticationPrincipal Jwt jwt, @PathVariable("userId") String userId){
        String s = "shahin";
        return String.format("the user with Id of %2$s,and Name of %1$s deleted",s,userId);
    }


    //don't use postauthorize for methods that do something to data it can be used for getting data or so
    @GetMapping(value = "/{userId}")
    @PostAuthorize("returnObject.id == #jwt.subject")
    public UserDto getUser(@AuthenticationPrincipal Jwt jwt,@PathVariable("userId") String userId){
        return new UserDto("shahin","khalajestani",userId);
    }
}
