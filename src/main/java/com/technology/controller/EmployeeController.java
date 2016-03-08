package com.technology.controller;

import com.technology.model.Employee;
import com.technology.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("employee/new", "employee", new Employee());
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String registration(@Valid @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "ERROR";
        }

        employeeRepository.save(employee);

        return "SUCCESS";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView resultAsPage(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "initPage", defaultValue = "5") int initPage) {
        Pageable pageable = new PageRequest(page - 1, initPage, Sort.Direction.DESC, "id");
        ModelAndView modelAndView = new ModelAndView("/employee/list", "employees", employeeRepository.findAll(pageable));
        modelAndView.addObject("page", page);
        return modelAndView;
    }
}