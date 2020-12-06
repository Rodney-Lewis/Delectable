package com.delectable.userauth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.delectable.userauth.models.ERole;
import com.delectable.userauth.models.Role;
import com.delectable.userauth.models.User;
import com.delectable.userauth.payload.request.LoginRequest;
import com.delectable.userauth.payload.request.SignupRequest;
import com.delectable.userauth.repository.RoleRepository;
import com.delectable.userauth.repository.UserRepository;
import com.delectable.util.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAuthUtil {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    void setupRoles() {
        List<Role> roles = new ArrayList<Role>();

        for (ERole e : ERole.values()) {
            roles.add(new Role(e));
        }
        roleRepository.saveAll(roles);
    }

    SignupRequest createValidSignupRequest() {
        String email = UUIDHelper.generateUUID(5) + "@gmail.com";
        String username = UUIDHelper.generateUUID(8);
        String password = UUIDHelper.generateUUID(8);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail(email);
        signupRequest.setUsername(username);
        signupRequest.setPassword(password);
        return signupRequest;
    }

    SignupRequest createInvalidSignupRequest() {
        String email = UUIDHelper.generateUUID(5) + "@gmail.com";
        String username = UUIDHelper.generateUUID(5);
        String password = UUIDHelper.generateUUID(5);

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail(email);
        signupRequest.setUsername(username);
        signupRequest.setPassword(password);
        return signupRequest;
    }

    LoginRequest createLoginRequest(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        return loginRequest;
    }

    LoginRequest createValidUserLoginRequest(Set<Role> roles) {
        String email = UUIDHelper.generateUUID(5) + "@gmail.com";
        String username = UUIDHelper.generateUUID(8);
        String password = UUIDHelper.generateUUID(8);
        User user = new User(username, email, password);
        user.setRoles(roles);
        userRepository.save(user);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        return loginRequest;
    }

}
