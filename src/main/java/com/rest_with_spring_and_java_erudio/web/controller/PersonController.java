package com.rest_with_spring_and_java_erudio.web.controller;

import com.rest_with_spring_and_java_erudio.data.vo.v1.PersonVO;
import com.rest_with_spring_and_java_erudio.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/persons")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO getById(@PathVariable Long id) {
        return services.findById(id);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonVO> getAll() {
        return services.findAll();
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO update(@PathVariable Long id, @RequestBody PersonVO person) {
        return services.update(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO create(@RequestBody PersonVO person) {
        return services.create(person);
    }


}
