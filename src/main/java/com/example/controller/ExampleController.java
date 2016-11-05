package com.example.controller;

import com.example.database.DatabaseAccessor;
import com.example.database.DatabaseAccessor.DatabaseValueHolder;
import com.example.domain.Form;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExampleController {

	private final DatabaseAccessor databaseAccessor;

	@Autowired
	public ExampleController(DatabaseAccessor databaseAccessor) {
		this.databaseAccessor = databaseAccessor;
	}

	// only GET requests to /apage make it to here
	@RequestMapping(value = "/apage", method = RequestMethod.GET)
	public String getFruit() {
		return "simple_page";	// Use template simple_page.html (DON'T ADD .html in this String!!!) in src/main/resources/templates
	}

	// Only POST requests to /posty make it here
	@RequestMapping(value = "/posty", method = RequestMethod.POST)
	public String handlePost(Form form) {

		databaseAccessor.add(Collections.singletonList(form.getTheParam()));

		return "simple_page";	// Use template simple_page.html (DON'T ADD .html in this String!!!) in src/main/resources/templates
	}

	@RequestMapping("/things")
	public String getTheThings(Model model) {
		List<DatabaseValueHolder> data = databaseAccessor.getData();

		model.addAttribute("myThings", data);	// the data List can be retrieved in the Thymeleaf template using key myThings

		return "database_values";	// Use template database_values.html (DON'T ADD .html in this String!!!) in src/main/resources/templates
	}
}
