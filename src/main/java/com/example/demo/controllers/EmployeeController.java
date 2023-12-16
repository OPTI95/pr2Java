package com.example.demo.controllers;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping
    public String showAllEmployees(Model model) {
        List<Employee> employees = employeeDao.index();
        model.addAttribute("employees", employees);
        return "employee/employeeList";
    }

    @GetMapping("/{id}")
    public String showEmployeeDetails(@PathVariable Long id, Model model) {
        Employee employee = employeeDao.show(id);
        model.addAttribute("employee", employee);
        return "employee/employeeDetails";
    }

    @GetMapping("/new")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/employeeForm";
    }

    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeDao.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeDao.show(id);
        model.addAttribute("employee", employee);
        return "employee/editEmployeeForm";
    }

    @PostMapping("/{id}/edit")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee updatedEmployee) {
        employeeDao.update(id, updatedEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Long id) {
        employeeDao.delete(id);
        return "redirect:/employees";
    }
}
