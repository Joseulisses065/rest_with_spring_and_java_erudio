package com.rest_with_spring_and_java_erudio.repository;
import com.rest_with_spring_and_java_erudio.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
}
