package com.example.bilabonnmenteksamensprojekt.controllers;

import com.example.bilabonnmenteksamensprojekt.models.customers.Customer;

import com.example.bilabonnmenteksamensprojekt.models.users.User;
import com.example.bilabonnmenteksamensprojekt.services.CustomerService;
import com.example.bilabonnmenteksamensprojekt.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    UserService authenticationService;

    @GetMapping("/customers")
    public String getCustomers(HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            List<Customer> customerList = customerService.getCustomers();
            model.addAttribute("customers", customerList);
            return "customers/customers";
        }
        else {
            return "redirect:/?location=customers";
        }
    }
    @GetMapping("/customers/{customerId}")
    public String getCustomerDetails(HttpSession session, Model model, @PathVariable Integer customerId) {
        if (session.getAttribute("authenticated") != null && ((boolean) session.getAttribute("authenticated"))) {
            if (authenticationService.userHasRight((String)session.getAttribute("authorizedUsername"), "ViewCustomerDetails")) {
                Customer customer = customerService.getCustomerById(customerId);
                model.addAttribute("customer", customer);
                return "customers/customerDetails";
            }
            else {
                return "redirect:/authError";
            }
        }
        else {
            return "redirect:/?location=customers/" + customerId;
        }
    }


    @GetMapping ("/createCustomer")
    public String createCustomer (HttpSession session, Model model){
        if (session.getAttribute("authenticated") != null &&((boolean) session.getAttribute("authenticated"))) {
            User user = (User)session.getAttribute("userData");
            model.addAttribute("UserID", user.getUserId());
            return "/customers/createCustomer";
        }
        else {
            return "redirect:/?location=forside";
        }
    }

}
