package com.delectable.userauth.controllers;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.delectable.shared.MessageResponse;
import com.delectable.userauth.models.ERole;
import com.delectable.userauth.models.User;
import com.delectable.userauth.models.UserViews;
import com.delectable.userauth.repository.*;
import com.delectable.userauth.security.jwt.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonView;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/pageable")
    @JsonView(UserViews.Simple.class)
    public ResponseEntity<?> getUserPage(Pageable pageable) {
        return ResponseEntity.ok(service.getUserPage(pageable));
    }

    @GetMapping("/{id}")
    @JsonView(UserViews.Simple.class)
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/groups")
    EnumSet<ERole> getRoles() {
        return EnumSet.of(ERole.ROLE_ADMIN, ERole.ROLE_USER, ERole.ROLE_VEIWER);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        try {
            return ResponseEntity.ok(service.update(id, updatedUser));
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(e.getMessage()));
        }
    }
}
