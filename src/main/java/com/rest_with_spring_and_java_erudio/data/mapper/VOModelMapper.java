package com.rest_with_spring_and_java_erudio.data.mapper;


import com.rest_with_spring_and_java_erudio.data.vo.v1.PersonVO;
import com.rest_with_spring_and_java_erudio.domain.entity.Person;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class VOModelMapper {
    private static ModelMapper mapper = new ModelMapper();
    static {
        mapper.createTypeMap(Person.class, PersonVO.class).addMapping(Person::getId, PersonVO::setKey);
        mapper.createTypeMap(PersonVO.class,Person.class).addMapping(PersonVO::getKey,Person::setId);
    }
    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>();
        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

}
