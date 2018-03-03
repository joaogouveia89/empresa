package com.empresa.jlvg89.empresa.models;

import java.io.Serializable;

/**
 * Created by jlvg89 on 02/03/18.
 */

public class Enterprise implements Serializable {
    private int id;
    private String email_enterprise;
    private String facebook;
    private String twitter;
    private String linkedin;
    private String phone;
    private boolean own_enterprise;
    private String enterprise_name;
    private String photo;
    private String description;
    private String city;
    private String country;
    private int value;
    private int share_price;
    private EnterpriseType enterprise_type;

    public Enterprise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail_enterprise() {
        return email_enterprise;
    }

    public void setEmail_enterprise(String email_enterprise) {
        this.email_enterprise = email_enterprise;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isOwn_enterprise() {
        return own_enterprise;
    }

    public void setOwn_enterprise(boolean own_enterprise) {
        this.own_enterprise = own_enterprise;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getShare_price() {
        return share_price;
    }

    public void setShare_price(int share_price) {
        this.share_price = share_price;
    }

    public EnterpriseType getEnterprise_type() {
        return enterprise_type;
    }

    public void setEnterprise_type(EnterpriseType enterprise_type) {
        this.enterprise_type = enterprise_type;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "id=" + id +
                ", email_enterprise='" + email_enterprise + '\'' +
                ", facebook='" + facebook + '\'' +
                ", twitter='" + twitter + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", phone='" + phone + '\'' +
                ", own_enterprise=" + own_enterprise +
                ", enterprise_name='" + enterprise_name + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", value=" + value +
                ", share_price=" + share_price +
                ", enterprise_type=" + enterprise_type +
                '}';
    }
}
