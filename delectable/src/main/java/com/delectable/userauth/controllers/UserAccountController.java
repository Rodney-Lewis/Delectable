package com.delectable.userauth.controllers;

import java.util.Map;
import java.util.Optional;
import com.delectable.userauth.models.User;
import com.delectable.userauth.payload.response.MessageResponse;
import com.delectable.userauth.repository.*;
import com.delectable.userauth.security.jwt.*;
import com.delectable.userauth.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/account")
public class UserAccountController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping
    public UserDetailsImpl getCurrentUserDetails(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails;
    }

    @PutMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCurrentUserDetails(
            @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody User updatedUser) {
        Optional<User> optUser = userRepository.findById(userDetails.getId());
        if (optUser.isPresent()) {
            User user = new User();
            user = optUser.get();
            updatedUser.setId(user.getId());
            updatedUser.setRole(user.getRole());
            updatedUser.setPassword(user.getPassword());
            userRepository.save(updatedUser);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new MessageResponse("User updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("User not found"));
        }
    }

    @PutMapping(value = "/credentials", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCurrentUserPassword(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody Map<String, Object> payload) {
        Optional<User> optUser = userRepository.findById(userDetails.getId());
        if (optUser.isPresent()) {
            User user = new User();
            user = optUser.get();
            if (encoder.matches((CharSequence) payload.get("oldPassword"), user.getPassword())
                    && payload.get("newPassword").equals(payload.get("confirmedPassword"))) {
                user.setPassword(encoder.encode((CharSequence) payload.get("newPassword")));
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new MessageResponse("User updated successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Unexpected user credentials"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("User not found"));
        }
    }
}
