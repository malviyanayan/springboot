package com.practice.onetoone.repository.onetomany;

import com.practice.onetoone.entity.onetomany.Auther;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AutherRepository extends JpaRepository<Auther, Integer> {

}
