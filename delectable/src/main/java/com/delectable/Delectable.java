package com.delectable;

import com.delectable.util.storage.StorageProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Delectable{

	public static void main(String[] args) {
		SpringApplication.run(Delectable.class, args);
	}

}
