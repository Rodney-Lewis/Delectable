package com.delectable.shared.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfService {

    @Autowired
    ConfRepository configurationRepository;

    public <T> void persistConfiguration(EConf conf, T value) {
        Conf config = new Conf(conf.getName(),
                String.valueOf(value), conf.getType());
        configurationRepository.save(config);
    }

    public boolean doesConfigurationExist(EConf conf) {
        return configurationRepository.existsByName(conf.getName());
    }

}
