package com.taller3.demo.controller;

import com.taller3.demo.dao.LocationDao;
import com.taller3.demo.model.prod.Location;
import com.taller3.demo.services.LocationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LocationController {

	@Autowired
	private LocationDao daoLocation;
	@Autowired
	private LocationServiceImp locationServiceImp;

	@GetMapping("/locations/")
	public String locationScreen(Model model) {
		model.addAttribute("locations", daoLocation.getAll());

		return "/locations/index";
	}

	@GetMapping("/locations/add/")
	public String locationAddScreen(Model model) {
		model.addAttribute(new Location());
		return "/locations/add";
	}

	@PostMapping("/locations/add")
	private String locationAdd(@Valid @ModelAttribute Location location, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		String ret = "redirect:/locations/";

		if (!action.equals("Cancel")) {
			if (!bindingResult.hasErrors()) {
				locationServiceImp.saveLocation(location);
			} else {
				ret = "/locations/add";
			}
		}

		return ret;
	}
	
	@GetMapping("/locations/edit/{id}")
	public String editLocationScreen(@PathVariable("id") Integer id, Model model) {
		Optional<Location> findEmployee = daoLocation.findById(id);
		if (findEmployee.isEmpty())
			throw new IllegalArgumentException("Invalid employee Id:" + id);
		model.addAttribute("location", findEmployee.get());
		return "locations/edit";
	}
	@PostMapping("/locations/edit/{id}")
	public String editLocation(@Valid @ModelAttribute Location location, BindingResult bindingResult, Model model,
			@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action) {
		
		String dir = "redirect:/locations/";

		if (!action.equals("Cancel")) {
			if (!bindingResult.hasErrors()) {			
				locationServiceImp.editLocation(location, id);
				model.addAttribute("locations", daoLocation.getAll());
			} else {
				model.addAttribute("location", location);
				dir = "locations/edit";
			}
		}
		return dir;
	}

}
