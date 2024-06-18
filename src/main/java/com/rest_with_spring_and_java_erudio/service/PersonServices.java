package com.rest_with_spring_and_java_erudio.service;

import com.rest_with_spring_and_java_erudio.domain.entity.Person;
import com.rest_with_spring_and_java_erudio.repository.PersonRespository;
import com.rest_with_spring_and_java_erudio.web.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    @Autowired
    private PersonRespository personRespository;
    private final AtomicLong count = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(Long id) {
        logger.info("finding person");
        Person person = personRespository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with id {%s} not found", id))
        );
        return person;
    }

    public List<Person> findAll() {
        logger.info("finding all persons");
        return personRespository.findAll();
    }

    public Person create(Person person) {
        Person entity = personRespository.save(person);
        return entity;
    }

    public Person update(Long id, Person person) {
        Person entity = personRespository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user of you want update not found")
        );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return personRespository.save(entity);
    }

    public Person delete(Long id) {
        Person entity = personRespository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user of you want delete not found")
        );
        logger.info("Deleting person");
        personRespository.delete(entity);
        return entity;
    }


}
