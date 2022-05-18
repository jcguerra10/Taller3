package com.taller3.demo.controller;

import com.taller3.demo.dao.LocationDao;
import com.taller3.demo.dao.ProductDaoImp;
import com.taller3.demo.dao.ProductinventoryDaoImp;
import com.taller3.demo.model.prod.Productinventory;
import com.taller3.demo.services.LocationServiceImp;
import com.taller3.demo.services.ProductInventoryServiceImp;
import com.taller3.demo.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class InventoryController {

	@Autowired
	private ProductinventoryDaoImp daoProductInventory;
	@Autowired
	private ProductInventoryServiceImp productInventoryServiceImp;

	@Autowired
	private LocationDao daoLocation;
	@Autowired
	private LocationServiceImp locationServiceImp;

	@Autowired
	private ProductDaoImp daoProduct;
	@Autowired
	private ProductServiceImp productServiceImp;

	@GetMapping("/inventoryproduct/")
	public String products(Model model) {
		model.addAttribute("productsinventory", daoProductInventory.getAll());
		
		
		
		daoProductInventory.getAll().forEach(pi ->
		
		System.out.println("---" +pi.getLocation() == null)
				);
		return "/inventoryproduct/index";
	}

	@GetMapping("/inventoryproduct/add/")
	public String addInventoryScreen(Model model) {
		model.addAttribute("productinventory", new Productinventory());
		model.addAttribute("products", daoProduct.getAll());
		model.addAttribute("locations", daoLocation.getAll());
		return "/inventoryproduct/add";
	}

	@PostMapping("inventoryproduct/add/")
	public String addInventory(@Valid @ModelAttribute Productinventory productInventory, BindingResult bindingResult,
			Model model, @RequestParam(value = "action", required = true) String action, Integer quantity) {
		String ret = "redirect:/inventoryproduct/";
		
		if (!action.equals("Cancel")) {
			if (!bindingResult.hasErrors()) {
				System.out.println(productInventory.getProduct().getName());
				productInventoryServiceImp.saveProductInventory(productInventory);
			} else {
				ret = "/inventoryproduct/add";				
			}
		}
		return ret;
	}

	@GetMapping("/inventoryproduct/edit/{id}")
	public String editInventoryScreen(@Valid @ModelAttribute Productinventory productInventory,
			BindingResult bindingresult, @PathVariable("id") Integer id, Model model) {

		Productinventory pInventory = daoProductInventory.findById(id).get();

		model.addAttribute("inventoryproduct", pInventory);
		model.addAttribute("products", daoProduct.getAll());
		model.addAttribute("locations", daoLocation.getAll());
		return "/inventoryproduct/edit";
	}

	@PostMapping("/inventoryproduct/edit/{id}")
	public String editInventory(@Valid @ModelAttribute Productinventory productInventory, BindingResult bindingResult,
			Model model, @PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action) {
		String dir = "redirect:/inventoryproduct/";

		if (!action.equals("Cancel")) {
			if (!bindingResult.hasErrors()) {
				productInventoryServiceImp.editProductInventory(productInventory, id);
			} else {
				dir = "/inventoryproduct/add";
			}
		}
		return dir;
	}

}
