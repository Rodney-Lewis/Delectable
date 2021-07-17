package com.delectable.userauth.controllers;

import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import com.delectable.shared.MessageResponse;
import com.delectable.userauth.models.ERole;
import com.delectable.userauth.models.User;
import com.delectable.userauth.payload.request.LoginRequest;
import com.delectable.userauth.payload.request.SignupRequest;
import com.delectable.userauth.payload.response.JwtResponse;
import com.delectable.userauth.repository.UserRepository;
import com.delectable.userauth.security.jwt.JwtUtils;
import com.delectable.userauth.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    public User get(long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("Error: User not found!");
        } else {
            return user.get();
        }
    }

    public Page<User> getUserPage(Pageable pageable) {
        return repository.findByIdNot(pageable, 1l);
    }

    public MessageResponse update(long id, User updatedUser) {
        User user = get(id);
        boolean usernameInUse = repository.existsByUsername(updatedUser.getUsername());
        boolean usernameIsTheSame = updatedUser.getUsername().compareTo(user.getUsername()) == 0;
        boolean emailInUse = repository.existsByEmail(updatedUser.getEmail());
        boolean emailIsNull =
                updatedUser.getEmail().compareTo("") == 0 || updatedUser.getEmail() == null;
        boolean emailIsTheSame = updatedUser.getEmail().compareTo(user.getEmail()) == 0;

        if (!emailInUse || emailInUse && emailIsTheSame || emailIsNull) {
            if (!usernameInUse || usernameInUse && usernameIsTheSame) {
                updatedUser.setId(user.getId());
                updatedUser.setPassword(user.getPassword());
                repository.save(updatedUser);
                return new MessageResponse("User updated successfully!");
            } else {
                throw new IllegalArgumentException("Error: Username is already taken!");
            }
        } else {
            throw new IllegalArgumentException("Error: Email is already in use!");
        }
    }

    public MessageResponse updateCurrentUser(UserDetailsImpl userDetails, User updatedUser) {
        User user = get(userDetails.getId());
        updatedUser.setId(user.getId());
        updatedUser.setRole(user.getRole());
        updatedUser.setPassword(user.getPassword());
        repository.save(updatedUser);
        return new MessageResponse("User updated successfully");
    }

    public MessageResponse updateCurrentUserPassword(UserDetailsImpl userDetails,
            Map<String, Object> payload) {

        User user = get(userDetails.getId());
        if (encoder.matches((CharSequence) payload.get("oldPassword"), user.getPassword())
                && payload.get("newPassword").equals(payload.get("confirmedPassword"))) {
            user.setPassword(encoder.encode((CharSequence) payload.get("newPassword")));
            repository.save(user);
            return new MessageResponse("User updated successfully");
        } else {
            throw new IllegalArgumentException("Unexpected user credentials");
        }
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            return new JwtResponse(jwt);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials.");
        } catch (DisabledException e) {
            throw new DisabledException("Account disabled.");
        } catch (LockedException e) {
            throw new LockedException("Account locked.");
        }
    }

    public MessageResponse registerUser(SignupRequest signUpRequest) {
        if (repository.existsByUsername(signUpRequest.getUsername())) {
            throw new IllegalArgumentException("Error: Username is already taken!");
        }

        if (signUpRequest.getEmail() != null) {
            if (repository.existsByEmail(signUpRequest.getEmail())) {
                throw new IllegalArgumentException("Error: Email is already in use!");
            }
        } else {
            signUpRequest.setEmail("");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        user.setRole(ERole.ROLE_VEIWER);
        repository.save(user);

        return new MessageResponse("User registered successfully!");
    }
}
