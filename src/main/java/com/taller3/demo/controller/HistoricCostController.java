package com.taller3.demo.controller;

import com.taller3.demo.dao.ProductDaoImp;
import com.taller3.demo.dao.ProductcosthistoryDaoImp;
import com.taller3.demo.model.prod.Productcosthistory;
import com.taller3.demo.services.ProductServiceImp;
import com.taller3.demo.services.ProductcosthistoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Optional;

@Controller
public class HistoricCostController {

    @Autowired
    private ProductcosthistoryDaoImp daoProductCost;
    @Autowired
    private ProductcosthistoryServiceImp productcosthistoryServiceImp;

    @Autowired
    private ProductDaoImp daoProduct;
    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("/historiccosts/")
    public String products(Model model) {
        model.addAttribute("historiccosts", daoProductCost.getAll());
        return "/historicCosts/index";
    }

    @GetMapping("/historiccosts/add/")
    public String productAddScreen(Model model) {
        model.addAttribute(new Productcosthistory());
        model.addAttribute("products", daoProduct.getAll());
        return "/historiccosts/add";
    }

    @PostMapping("/historiccosts/add/")
    public String productAdd(@Valid @ModelAttribute Productcosthistory productcosthistory, BindingResult bindingResult,
                             Model model, @RequestParam(value = "action", required = true) String action) {

        String ret = "redirect:/historiccosts/";

        if (!action.equals("Cancel")) {
            if (!bindingResult.hasErrors()) {
                productcosthistoryServiceImp.saveProductcosthistory(productcosthistory);
            } else {
                bindingResult.getAllErrors().forEach(t ->
                        System.out.println(t)
                );

                ret = "/historiccosts/add";
                model.addAttribute(new Productcosthistory());
                model.addAttribute("products", daoProduct.getAll());
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

    // ----------------- EDIT -----------------

    @GetMapping("/historiccosts/edit/{id}")
    public String editHistoriccostScreen(@PathVariable("id") Integer id, Model model) {

        Optional<Productcosthistory> find = daoProductCost.findById(id);
        if (find.isEmpty())
            throw new IllegalArgumentException("Invalid Id:" + id);
        model.addAttribute("productcosthistory", find.get());
        model.addAttribute("products", daoProduct.getAll());
        return "/historiccosts/edit";
    }

    @PostMapping("/historiccosts/edit/{id}")
    public String editHistoriccosts(@Valid @ModelAttribute Productcosthistory productcosthistory, BindingResult bindingResult, Model model, @PathVariable("id") Integer id,
                                    @RequestParam(value = "action", required = true) String action, String enddatelb, String standardcost) {


        String dir = "redirect:/historiccosts/";

        if (!action.equals("Cancel")) {

            productcosthistoryServiceImp.editProductcosthistory(productcosthistory, id);
        }
        return dir;
    }

}
