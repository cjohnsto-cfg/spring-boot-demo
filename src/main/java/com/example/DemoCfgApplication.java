package com.example;

import com.example.database.DatabaseAccessor;
import com.example.database.DatabaseAccessor.DatabaseValueHolder;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoCfgApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoCfgApplication.class, args);

		DatabaseAccessor dao = applicationContext.getBean(DatabaseAccessor.class);

		dao.add(Arrays.asList("One", "Two", "Three"));

		List<DatabaseValueHolder> data = dao.getData();

		System.out.println(data);
	}
}
