package com.delectable.userauth;

import org.springframework.stereotype.Component;
import com.delectable.config.ConfigurationService;
import com.delectable.config.EConf;
import com.delectable.unit.EDefaultUnits;
import com.delectable.unit.Unit;
import com.delectable.unit.UnitRepository;
import com.delectable.userauth.models.ERole;
import com.delectable.userauth.models.User;
import com.delectable.userauth.repository.UserRepository;
import com.delectable.util.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
class UserConfiguration {
    @Autowired
    ConfigurationService configurationService;

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
        if (!configurationService.doesConfigurationExist(EConf.JWT_SECRET)) {
            configurationService.persistConfiguration(EConf.JWT_SECRET,
                    UUIDHelper.generateUUID(128));
        }
    }

    private void persistDefaultAdminUser() {
        if (!configurationService.doesConfigurationExist(EConf.SUPER_USER_GENERATED)) {
            String email = "replaceme@admin.com";
            String username = "Admin1";
            String password = "Admin1";
            User user = new User(username, email, encoder.encode(password));
            user.setRole(ERole.ROLE_SUPER_USER);
            userRepository.save(user);
            configurationService.persistConfiguration(EConf.SUPER_USER_GENERATED, "true");
        }
    }
}
