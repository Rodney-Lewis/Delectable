package com.delectable.conf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String value;

    @NotNull
    private String type;

    @NotNull
    private int revision;

    @NotNull
    private Long epochCreationDate;

    @NotNull
    private Long epochUpdateDate;
    
}