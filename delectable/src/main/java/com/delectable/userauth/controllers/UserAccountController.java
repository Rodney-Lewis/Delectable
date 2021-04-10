package com.delectable.userauth.controllers;

import java.util.Map;
import javax.persistence.EntityNotFoundException;
import com.delectable.shared.MessageResponse;
import com.delectable.userauth.models.User;
import com.delectable.userauth.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/account")
@PreAuthorize("hasRole('VEIWER')")
public class UserAccountController {

    @Autowired
    UserService service;

    @GetMapping
    public UserDetailsImpl getCurrentUserDetails(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails;
    }

    @PutMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCurrentUserDetails(
            @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody User updatedUser) {
        return ResponseEntity.ok().body(service.updateCurrentUser(userDetails, updatedUser));
    }

    @PutMapping(value = "/credentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCurrentUserPassword(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok().body(service.updateCurrentUserPassword(userDetails, payload));
    }
}
