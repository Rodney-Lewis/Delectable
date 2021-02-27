package com.delectable.unit;

import com.delectable.config.ConfigurationService;
import com.delectable.config.EConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class UnitConfiguration {

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    UnitRepository unitRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void setUpDefaultUnits() {
        if (!configurationService.doesConfigurationExist(EConf.DEFAULT_UNITS_GENERATED)) {
            for (EDefaultUnits unit : EDefaultUnits.values()) {
                unitRepository.save(new Unit(unit.getName(), unit.getAbbreviation()));
            }
            configurationService.persistConfiguration(EConf.DEFAULT_UNITS_GENERATED, "true");
        }
    }
}
