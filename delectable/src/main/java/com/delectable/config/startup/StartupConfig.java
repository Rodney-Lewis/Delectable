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
import com.delectable.userauth.models.Role;
import com.delectable.userauth.models.User;
import com.delectable.userauth.repository.RoleRepository;
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
    RoleRepository roleRepository;

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    UnitService unitService;

    @Autowired
    PasswordEncoder encoder;

    @EventListener(ApplicationStartedEvent.class)
    public void setUpUserAuth() {
        persistJwtSecret();
        persistDefaultUserRoles();
        persistDefaultAdminUser();
    }

    @EventListener(ApplicationStartedEvent.class)
    public void setUpDefaultUnits() {
        if (!configurationService.existsByName(EConf.DEFAULT_UNITS_GENERATED.getName()))
            for (EDefaultUnits unit : EDefaultUnits.values()) {
                unitService.save(new Unit(unit.getName(), unit.getAbbreviation()));
            }
        Configuration config = new Configuration(EConf.DEFAULT_UNITS_GENERATED.getName(),
                "true", EConf.DEFAULT_UNITS_GENERATED.getType());
        configurationService.save(config);
    }

    private void persistJwtSecret() {
        if (!configurationService.existsByName(EConf.JWT_SECRET.getName())) {
            Configuration config = new Configuration(EConf.JWT_SECRET.getName(),
                    UUIDHelper.generateUUID(128), EConf.JWT_SECRET.getType());
            configurationService.save(config);
        }

    }

    private void persistDefaultUserRoles() {
        if (!configurationService.existsByName(EConf.ROLES_GENERATED.getName())) {
            List<Role> roles = new ArrayList<Role>();

            for (ERole e : ERole.values()) {
                roles.add(new Role(e));
            }
            roleRepository.saveAll(roles);

            Configuration config = new Configuration(EConf.ROLES_GENERATED.getName(), "true",
                    EConf.ROLES_GENERATED.getType());
            configurationService.save(config);
        }

    }

    private void persistDefaultAdminUser() {
        if (!configurationService.existsByName(EConf.SUPER_USER_GENERATED.getName())) {
            String email = "Admin@email.com";
            String username = "Admin";
            String password = "Admin";
            User user = new User(username, email, encoder.encode(password));

            Set<Role> roles = new HashSet<Role>();
            Role adminRole = roleRepository.findByName(ERole.ADMIN).get();
            roles.add(adminRole);

            user.setRoles(roles);
            userRepository.save(user);

            Configuration config = new Configuration(EConf.SUPER_USER_GENERATED.getName(), "true",
                    EConf.SUPER_USER_GENERATED.getType());
            configurationService.save(config);
        }
    }
}
