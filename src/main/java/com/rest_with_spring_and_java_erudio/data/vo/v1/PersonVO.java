package com.rest_with_spring_and_java_erudio.data.vo.v1;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
@JsonPropertyOrder({"key","firstName","lastName","address","gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
    @JsonProperty("id")
    private Long key;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonVO(Long id, String firstName, String lastName, String address, String gender) {
        this.key = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public PersonVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
