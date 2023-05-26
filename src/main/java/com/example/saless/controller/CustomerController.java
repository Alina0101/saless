package com.example.saless.controller;

import com.example.saless.models.Customer;
import com.example.saless.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");

        Iterable<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);

        return "home";
    }


    @GetMapping("/add")
    public String addNewCustomer(Model model) {
        return "add";
    }

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public String addCustomer(@RequestParam String name
            , @RequestParam String email, @RequestParam String address, Model model) {
        Customer n = new Customer(name, email, address);
        customerRepository.save(n);
        return "redirect:/";
    }

    @GetMapping("/customer/{id}")
    public String getCustomerDetails(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return "redirect:/";
        }
        model.addAttribute("customer", customer);
        return "customer_info";
    }


    @PostMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "redirect:/";
    }
}
