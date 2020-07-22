package com.rhb.persondemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhb.persondemo.models.Person;

public interface MainRepository extends JpaRepository<Person, Integer>{
	

}
