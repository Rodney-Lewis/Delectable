package com.delectable.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Autowired
    ConfigurationRepository configurationRepository;

    public <T> void persistConfiguration(EConf conf, T value) {
        Configuration config = new Configuration(conf.getName(),
                String.valueOf(value), conf.getType());
        configurationRepository.save(config);
    }

    public boolean doesConfigurationExist(EConf conf) {
        return configurationRepository.existsByName(conf.getName());
    }

}
