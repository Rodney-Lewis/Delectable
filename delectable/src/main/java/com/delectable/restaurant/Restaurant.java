package com.delectable.restaurant;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import com.delectable.shared.crud.CRUSoftDeleteEntity;

@Entity
public class Restaurant implements CRUSoftDeleteEntity<Restaurant> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name can not be null")
    private String name;
    private String address;
    private String addressNumber;
    private String city;
    private String state;
    private String postalCode;
    private String phoneNumber;
    private String website;
    private boolean carryOut;
    private boolean delivery;
    private boolean glutenFreeOptions;
    private String imageSource;
    private boolean deleted;

	@ElementCollection
	private List<String> tags = new ArrayList<String>();

    public Restaurant() {
        this.deleted = false;
    }

    public Restaurant(@NotNull(message = "Name can not be null") String name, String address,
            String addressNumber, String city, String state, String postalCode, String phoneNumber,
            String website, boolean carryOut, boolean delivery, boolean glutenFreeOptions, String imageSource, boolean deleted, List<String> tags) {
        this.name = name;
        this.address = address;
        this.addressNumber = addressNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.carryOut = carryOut;
        this.delivery = delivery;
        this.glutenFreeOptions = glutenFreeOptions;
        this.imageSource = imageSource;
        this.deleted = false;
        this.tags = tags;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isCarryOut() {
        return carryOut;
    }

    public void setCarryOut(boolean carryOut) {
        this.carryOut = carryOut;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
    
    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isGlutenFreeOptions() {
        return glutenFreeOptions;
    }

    public void setGlutenFreeOptions(boolean glutenFreeOptions) {
        this.glutenFreeOptions = glutenFreeOptions;
    }
    
}
