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
import io.micrometer.core.ipc.http.HttpSender.Response;
import javax.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonView;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService service;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/all")
    @JsonView(UserViews.Simple.class)
    public ResponseEntity<Map<String, Object>> getAllUsersAndRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.ASC, "username"));
        Page<User> userPages;

        userPages = userRepository.findByIdNot(pageable, 1L);
        if (userPages.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", userPages.getContent());
        response.put("page", userPages.getNumber());
        response.put("size", userPages.getSize());
        response.put("totalPages", userPages.getTotalPages());
        response.put("totalElements", userPages.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(UserViews.Simple.class)
    public User getUserById(@PathVariable long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("/groups")
    EnumSet<ERole> getRoles() {
        return EnumSet.of(ERole.ROLE_ADMIN, ERole.ROLE_USER, ERole.ROLE_VEIWER);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
        try {
            return ResponseEntity.ok(service.updateUser(id, updatedUser));
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(e.getMessage()));
        }
    }



}
