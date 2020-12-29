package com.delectable.userauth.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import com.delectable.userauth.models.ERole;
import com.delectable.userauth.models.Role;
import com.delectable.userauth.models.User;
import com.delectable.userauth.payload.request.LoginRequest;
import com.delectable.userauth.payload.request.SignupRequest;
import com.delectable.userauth.payload.response.JwtResponse;
import com.delectable.userauth.payload.response.MessageResponse;
import com.delectable.userauth.repository.*;
import com.delectable.userauth.security.jwt.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_USER')")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("{groups/id}")
    public ResponseEntity<?> updateUserGroups(@PathVariable Long id,
            @RequestBody Set<String> newRoles) {

        Optional<User> optUser = userRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        } else {

            User user = optUser.get();
            Set<Role> roles = new HashSet<>();

            newRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN).orElseThrow(
                                () -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "user":
                        Role modRole = roleRepository.findByName(ERole.USER).orElseThrow(
                                () -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    case "veiwer":
                        Role userRole = roleRepository.findByName(ERole.VEIWER).orElseThrow(
                                () -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });

            user.setRoles(roles);
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }

    }

}
