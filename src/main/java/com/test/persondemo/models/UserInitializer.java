package com.test.persondemo.models;

import java.util.UUID;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.test.persondemo.repositories.MainRepository;
import com.test.persondemo.repositories.UserRepository;

@Component
public class UserInitializer implements SmartInitializingSingleton {
	private final MainRepository mainRepo;
	private final UserRepository users;
	private final PasswordEncoder passwordEncoder;

	public UserInitializer(MainRepository mainRepo, UserRepository users, PasswordEncoder passwordEncoder) {
		this.mainRepo = mainRepo;
		this.users = users;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("create user!");

		UUID userId1 = UUID.fromString("e730e37c-9021-4879-8aac-dc116f15ff97");
		if (!this.users.existsById(userId1)) {
			User user1 = new User(userId1, "user1", this.passwordEncoder.encode("user1"));
			user1.addAuthorities("READ");
			this.users.save(user1);
		}
		UUID userId2 = UUID.fromString("627aea71-379e-414b-bd06-851622f419a9");
		if (!this.users.existsById(userId2)) {
			User user2 = new User(userId2, "user2", this.passwordEncoder.encode("user2"));
			user2.addAuthorities("READ");
			user2.addAuthorities("WRITE");
			this.users.save(user2);
		}

		Person person = new Person();
		person.setAge(33);
		person.setEmail("test@test.com");
		person.setFirstname("test1");
		person.setLastname("test1-lastname");

		mainRepo.save(person);

	}
}
