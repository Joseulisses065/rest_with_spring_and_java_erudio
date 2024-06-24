package com.rest_with_spring_and_java_erudio.unittest.mapper.unittest.mokito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.rest_with_spring_and_java_erudio.data.vo.v1.PersonVO;
import com.rest_with_spring_and_java_erudio.domain.entity.Person;
import com.rest_with_spring_and_java_erudio.repository.PersonRepository;
import com.rest_with_spring_and_java_erudio.service.PersonServices;
import com.rest_with_spring_and_java_erudio.unittest.mapper.mocks.MockPerson;
import com.rest_with_spring_and_java_erudio.web.exception.RequiredObjectIsNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonServices services;
    @Mock
    private PersonRepository respository;


    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(respository.findById(1L)).thenReturn(Optional.of(person));

        var result = services.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Female", result.getGender());
        assertEquals("Last Name Test1", result.getLastName());
        //assertTrue(result.toString().);

    }

    @Test
    void create() {
        Person persisted = input.mockEntity(1);
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(respository.save(any(Person.class))).thenReturn(persisted);

        var result = services.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Female", result.getGender());
        assertEquals("Last Name Test1", result.getLastName());

    }

    @Test
    void testUpdate() {

        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);

        vo.setKey(1L);

        when(respository.findById(1L)).thenReturn(Optional.of(person));
        when(respository.save(person)).thenReturn(persisted);

        var result = services.update(1L, vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Female", result.getGender());
        assertEquals("Last Name Test1", result.getLastName());
    }

    @Test
    void testDelete() {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(respository.findById(1L)).thenReturn(Optional.of(person));

        services.delete(1L);
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    services.create(null);
                });
        String expecterMessag = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expecterMessag));


    }

    @Test
    void testUpdateeWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    services.update(null, null);
                });
        String expecterMessag = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expecterMessag));


    }

    @Test
    void testFindAll() {
        List<Person> persons = input.mockEntityList();

        when(respository.findAll()).thenReturn(persons);

        var result = services.findAll();
        assertNotNull(result);
        assertEquals(14,persons.size());
        var personOne = result.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Female", personOne.getGender());
        assertEquals("Last Name Test1", personOne.getLastName());

        var personFour = result.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Male", personFour.getGender());
        assertEquals("Last Name Test4", personFour.getLastName());

        var personSev = result.get(7);

        assertNotNull(personSev);
        assertNotNull(personSev.getKey());
        assertNotNull(personSev.getLinks());
        assertEquals("First Name Test7", personSev.getFirstName());
        assertEquals("Female", personSev.getGender());
        assertEquals("Last Name Test7", personSev.getLastName());
        //assertTrue(result.toString().);
    }

}
