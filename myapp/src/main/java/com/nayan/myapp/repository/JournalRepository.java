package com.nayan.myapp.repository;

import com.nayan.myapp.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface JournalRepository extends JpaRepository<Journal, Long> {

}
