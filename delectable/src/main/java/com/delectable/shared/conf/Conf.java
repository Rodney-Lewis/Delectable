package com.delectable.shared.conf;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Conf {

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
    private final Long epochCreationDate;

    @NotNull
    private Long epochUpdateDate;

    public Conf() {
        epochCreationDate = new Date().getTime();
    }

    public Conf(@NotNull String name, @NotNull String value, @NotNull String type) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.revision = 1;
        this.epochCreationDate = new Date().getTime();
        this.epochUpdateDate = epochCreationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public Long getEpochCreationDate() {
        return epochCreationDate;
    }

    public Long getEpochUpdateDate() {
        return epochUpdateDate;
    }

    public void setEpochUpdateDate(Long epochUpdateDate) {
        this.epochUpdateDate = epochUpdateDate;
    }

}
