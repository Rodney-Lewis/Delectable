package com.delectable.userauth;

import com.delectable.shared.UUIDHelper;
import com.delectable.userauth.models.ERole;
import com.delectable.userauth.models.User;
import com.delectable.userauth.payload.request.LoginRequest;
import com.delectable.userauth.payload.request.SignupRequest;
import com.delectable.userauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAuthUtil {

    @Autowired
    UserRepository userRepository;

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

    LoginRequest createValidUserLoginRequest(ERole role) {
        String email = UUIDHelper.generateUUID(5) + "@gmail.com";
        String username = UUIDHelper.generateUUID(8);
        String password = UUIDHelper.generateUUID(8);
        User user = new User(username, email, password);
        user.setRole(role);
        userRepository.save(user);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        return loginRequest;
    }

}
