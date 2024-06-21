package com.rest_with_spring_and_java_erudio.web.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO getById(@PathVariable Long id) {
        PersonVO vo = services.findById(id);
        vo.add(linkTo(methodOn(PersonController.class).getById(vo.getKey())).withSelfRel());
        return vo;
    }


    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonVO> getAll() {
        List<PersonVO> vo =  services.findAll();
        vo.stream().forEach(personVO -> personVO.add(linkTo(methodOn(PersonController.class).getById(personVO.getKey())).withSelfRel()));
        return vo;
    }

    @PutMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO update(@PathVariable Long id, @RequestBody PersonVO person) {
        var vo = services.update(id, person);
        vo.add(linkTo(methodOn(PersonController.class).getById(vo.getKey())).withSelfRel());
        return vo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO create(@RequestBody PersonVO person) {
        var vo =  services.create(person);
        vo.add(linkTo(methodOn(PersonController.class).getById(vo.getKey())).withSelfRel());
    return vo;
    }


}
