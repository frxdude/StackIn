package com.cs319.stack_in;

import com.cs319.stack_in.entity.Role;
import com.cs319.stack_in.entity.User;
import com.cs319.stack_in.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.UUID;

@SpringBootApplication
public class StackInApplication {

	UserRepository userRepository;
	PasswordEncoder encoder;
	Environment env;

	@Autowired
	public StackInApplication(UserRepository userRepository, PasswordEncoder encoder, Environment env) {
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(StackInApplication.class, args);
	}



}
