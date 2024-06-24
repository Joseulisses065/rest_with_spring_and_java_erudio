package com.rest_with_spring_and_java_erudio.repository;

import com.rest_with_spring_and_java_erudio.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
