package com.delectable.config.startup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.delectable.config.Configuration;
import com.delectable.config.ConfigurationService;
import com.delectable.config.EConf;
import com.delectable.unit.EDefaultUnits;
import com.delectable.unit.Unit;
import com.delectable.unit.UnitService;
import com.delectable.userauth.models.ERole;
import com.delectable.userauth.models.User;
import com.delectable.userauth.repository.UserRepository;
import com.delectable.util.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    UnitService unitService;

    @Autowired
    PasswordEncoder encoder;

    @EventListener(ApplicationStartedEvent.class)
    public void setUpUserAuth() {
        persistJwtSecret();
        persistDefaultAdminUser();
    }

    @EventListener(ApplicationStartedEvent.class)
    public void setUpDefaultUnits() {
        if (!configurationService.existsByName(EConf.DEFAULT_UNITS_GENERATED.getName())) {
            for (EDefaultUnits unit : EDefaultUnits.values()) {
                unitService.save(new Unit(unit.getName(), unit.getAbbreviation()));
            }
            Configuration config = new Configuration(EConf.DEFAULT_UNITS_GENERATED.getName(),
                    "true", EConf.DEFAULT_UNITS_GENERATED.getType());
            configurationService.save(config);
        }
    }

    private void persistJwtSecret() {
        if (!configurationService.existsByName(EConf.JWT_SECRET.getName())) {
            Configuration config = new Configuration(EConf.JWT_SECRET.getName(),
                    UUIDHelper.generateUUID(128), EConf.JWT_SECRET.getType());
            configurationService.save(config);
        }

    }

    private void persistDefaultAdminUser() {
        if (!configurationService.existsByName(EConf.SUPER_USER_GENERATED.getName())) {
            String email = "replaceme@admin.com";
            String username = "Admin1";
            String password = "Admin1";
            User user = new User(username, email, encoder.encode(password));
            user.setRole(ERole.ROLE_SUPER_USER);
            userRepository.save(user);

            Configuration config = new Configuration(EConf.SUPER_USER_GENERATED.getName(), "true",
                    EConf.SUPER_USER_GENERATED.getType());
            configurationService.save(config);
        }
    }
}
