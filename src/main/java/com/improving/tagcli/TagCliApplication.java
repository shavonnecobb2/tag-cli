package com.improving.tagcli;

import com.improving.tagcli.database.NewSkoolDatabaseClient;
import com.improving.tagcli.database.OldSkoolDatabaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.crypto.Data;
import java.util.concurrent.ForkJoinPool;

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
		databaseManagementThingy.run();
	}
}
