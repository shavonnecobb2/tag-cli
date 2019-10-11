package com.improving.tagcli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TagCliApplication implements CommandLineRunner {

	private final DatabaseManagementThingy databaseManagementThingy;

	public TagCliApplication(DatabaseManagementThingy databaseManagementThingy) {
		this.databaseManagementThingy = databaseManagementThingy;
	}

	public static void main(String[] args) {
		SpringApplication.run(TagCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		databaseManagementThingy.execute();
	}
}
