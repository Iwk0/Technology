package com.technology.controller;

import com.google.gson.Gson;
import com.technology.model.Employee;
import com.technology.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView home() {
        List employees = employeeRepository.findAll();
        return new ModelAndView("employee/new", "employee", new Employee());
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String registration(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return new Gson().toJson(employee);
    }
}