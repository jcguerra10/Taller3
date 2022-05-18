package com.taller3.demo.controller;

import com.taller3.demo.dao.ProductDaoImp;
import com.taller3.demo.dao.ProductcategoryDao;
import com.taller3.demo.dao.ProductsubcategoryDao;
import com.taller3.demo.model.prod.Product;
import com.taller3.demo.model.prod.Productcategory;
import com.taller3.demo.model.prod.Productsubcategory;
import com.taller3.demo.model.prod.UserApp;
import com.taller3.demo.services.ProductServiceImp;
import com.taller3.demo.services.ProductcategoryServiceImp;
import com.taller3.demo.services.ProductsubcategoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Optional;

@Controller
public class ProductController {

	@Autowired
	ProductDaoImp daoProduct;
	@Autowired
	ProductServiceImp productServiceImp;

	@Autowired
	ProductcategoryDao daoProductcategory;
	@Autowired
	ProductcategoryServiceImp productcategoryServiceImp;

	@Autowired
	ProductsubcategoryDao daoProductsubcategory;
	@Autowired
	ProductsubcategoryImp productsubcategoryImp;

	@GetMapping("/products/")
	public String showProducts(Model model, UserApp ua) {
		model.addAttribute("products", daoProduct.getAll());

		return "/products/index";
	}

	@GetMapping("/products/add/")
	public String productAddScreen(Model model) {
		model.addAttribute(new Product());
		model.addAttribute("subcategories", daoProductsubcategory.getAll());
		return "/products/add";
	}

	@PostMapping("/products/add/")
	public String productAdd(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action, String startdate, String enddate) {

		String ret = "redirect:/products/";

		if (!action.equals("Cancel")) {
			if (!bindingResult.hasErrors()) {

				productServiceImp.saveProduct(product);

			} else {
				ret = "/products/add";
			}
		}
		return ret;
	}

	private Timestamp Convert(String date) {
		String res = "";
		String[] splt = null;

		if (!date.isEmpty() || !date.isBlank()) {
			splt = date.split("T");

			res += splt[0];

			res += " " + splt[1] + ":00";
		}
		return Timestamp.valueOf(res);
	}

	// ----------------- CATEGORIES -----------------

	@GetMapping("/products/createcategories/")
	public String createCategoriesScreen(Model model) {

		return "/products/createcategories";
	}

	@PostMapping("/products/createcategories/")
	public String createCategories(Model model, String category, String subcategory) {

		Productcategory pc = new Productcategory();
		pc.setName(category);

		Productsubcategory psc = new Productsubcategory();
		psc.setName(subcategory);

		psc.setProductcategory(pc);

		productcategoryServiceImp.saveProductcategory(pc);
		productsubcategoryImp.saveProductsubcategory(psc);

		return "redirect:/products/add/";
	}

	// ----------------- EDIT -----------------

	@GetMapping("/products/edit/{id}")
	public String editProductScreen(@PathVariable("id") Integer id, Model model) {
		Optional<Product> find = daoProduct.findById(id);
		if (find.isEmpty())
			throw new IllegalArgumentException("Invalid Id:" + id);
		model.addAttribute("product", find.get());
		return "/products/edit";
	}

	@PostMapping("/products/edit/{id}")
	public String editProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model,
			@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action, String startdate, String enddate) {
		
		String dir = "redirect:/products/";

		if (!action.equals("Cancel")) {
			if (!bindingResult.hasErrors()) {

				productServiceImp.editProduct(product, id);
				model.addAttribute("products", daoProduct.getAll());
			} else {
				model.addAttribute("product", product);
				dir = "products/edit";
			}
		}
		return dir;
	}

}
