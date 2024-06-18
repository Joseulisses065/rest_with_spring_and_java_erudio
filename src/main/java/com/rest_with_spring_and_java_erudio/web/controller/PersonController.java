package com.rest_with_spring_and_java_erudio.web.controller;

import com.rest_with_spring_and_java_erudio.domain.entity.Person;
import com.rest_with_spring_and_java_erudio.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonServices services;

    @RequestMapping(value = "/{id}")
    public Person getById(@PathVariable Long id) {
        return services.findById(id);
    }

    @GetMapping
    public List<Person> getAll() {
        return services.findAll();
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable Long id, @RequestBody Person person) {
        return services.update(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        services.delete(id);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return services.create(person);
    }


}
