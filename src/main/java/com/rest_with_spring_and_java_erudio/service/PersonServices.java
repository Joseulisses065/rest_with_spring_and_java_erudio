package com.rest_with_spring_and_java_erudio.service;

import com.rest_with_spring_and_java_erudio.data.mapper.VOModelMapper;
import com.rest_with_spring_and_java_erudio.data.vo.v1.PersonVO;
import com.rest_with_spring_and_java_erudio.domain.entity.Person;
import com.rest_with_spring_and_java_erudio.repository.PersonRepository;
import com.rest_with_spring_and_java_erudio.web.exception.EntityNotFoundException;
import com.rest_with_spring_and_java_erudio.web.exception.RequiredObjectIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    @Autowired
    private PersonRepository personRespository;
    private final AtomicLong count = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public PersonVO findById(Long id) {
        logger.info("finding person");
        Person person = personRespository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("User with id {%s} not found", id))
        );
        return VOModelMapper.parseObject(person, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("finding all persons");
        return VOModelMapper.parseListObjects(personRespository.findAll(),PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        if (person == null)throw new RequiredObjectIsNullException();
        Person entity = personRespository.save(VOModelMapper.parseObject(person, Person.class));
        return VOModelMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO update(Long id, PersonVO person) {
        if (person == null)throw new RequiredObjectIsNullException();
        Person entity = personRespository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user of you want update not found")
        );
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        Person personResponse =  personRespository.save(VOModelMapper.parseObject(entity,Person.class));
        return VOModelMapper.parseObject(personResponse,PersonVO.class);
    }

    public PersonVO delete(Long id) {
        Person entity = personRespository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user of you want delete not found")
        );
        logger.info("Deleting person");
        personRespository.delete(entity);
        return VOModelMapper.parseObject(entity,PersonVO.class);
    }

}
