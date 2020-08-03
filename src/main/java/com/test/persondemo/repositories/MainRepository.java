package com.test.persondemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.persondemo.models.Person;

@Repository
public interface MainRepository extends JpaRepository<Person, Integer> {

}
