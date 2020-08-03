package com.test.persondemo.controllers;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.persondemo.models.Person;
import com.test.persondemo.repositories.MainRepository;
import com.test.persondemo.repositories.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private MainRepository mainRepository;

	@Autowired
	private PersonRepository personRepository;

	private Consumer<Person> deletePersonByFirstname = p -> mainRepository.deleteById(p.getPersonid());
	private BiFunction<Person, Person, Person> updateExistingRecordPerson = (person, ep) -> {
		BeanUtils.copyProperties(person, ep, "personid");
		mainRepository.saveAndFlush(ep);
		return ep;
	};

	@GetMapping
	public List<Person> list() {
		return mainRepository.findAll();
	}

	@GetMapping
	@RequestMapping("{firstname}")
	public List<Person> get(@PathVariable String firstname) {
		return personRepository.findByFirstname(firstname);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Person create(@RequestBody final Person person) {
		return mainRepository.saveAndFlush(person);
	}

	@RequestMapping(value = "{firstname}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String firstname) {
		List<Person> persons = personRepository.findByFirstname(firstname);
		persons.stream().forEach(deletePersonByFirstname);
	}

	@RequestMapping(value = "{firstname}", method = RequestMethod.PUT)
	public List<Person> update(@PathVariable String firstname, @RequestBody Person person) {
		List<Person> existingPersons = personRepository.findByFirstname(firstname);
		List<Person> persons = existingPersons.stream().map(u -> updateExistingRecordPerson.apply(person, u))
				.collect(Collectors.toList());

		return persons;
	}
}
