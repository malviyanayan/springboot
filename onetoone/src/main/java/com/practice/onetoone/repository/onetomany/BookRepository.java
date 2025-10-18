package com.practice.onetoone.repository.onetomany;

import com.practice.onetoone.entity.onetomany.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
