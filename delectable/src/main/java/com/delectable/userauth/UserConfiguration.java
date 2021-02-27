package com.delectable.userauth;


import com.delectable.shared.UUIDHelper;
import com.delectable.shared.conf.ConfService;
import com.delectable.shared.conf.EConf;
import com.delectable.userauth.models.ERole;
import com.delectable.userauth.models.User;
import com.delectable.userauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class UserConfiguration {
    @Autowired
    ConfService confService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @EventListener(ApplicationStartedEvent.class)
    public void setUpUserAuth() {
        persistJwtSecret();
        persistDefaultAdminUser();
    }


    private void persistJwtSecret() {
        if (!confService.doesConfigurationExist(EConf.JWT_SECRET)) {
            confService.persistConfiguration(EConf.JWT_SECRET,
                    UUIDHelper.generateUUID(128));
        }
    }

    private void persistDefaultAdminUser() {
        if (!confService.doesConfigurationExist(EConf.SUPER_USER_GENERATED)) {
            String email = "replaceme@admin.com";
            String username = "Admin1";
            String password = "Admin1";
            User user = new User(username, email, encoder.encode(password));
            user.setRole(ERole.ROLE_SUPER_USER);
            userRepository.save(user);
            confService.persistConfiguration(EConf.SUPER_USER_GENERATED, "true");
        }
    }
}
