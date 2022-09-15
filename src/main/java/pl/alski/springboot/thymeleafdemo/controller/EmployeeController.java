package pl.alski.springboot.thymeleafdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.alski.springboot.thymeleafdemo.entity.Employee;
import pl.alski.springboot.thymeleafdemo.service.EmployeeService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

private EmployeeService employeeService;

// Constructor Injection
    @Autowired
public EmployeeController(EmployeeService employeeService){
    this.employeeService = employeeService;
}

    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        List<Employee> theEmployees = employeeService.findAll();
    theModel.addAttribute("employees", theEmployees);
    return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        //create model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

        //save the Employee
        employeeService.save(theEmployee);

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {

        //get the employee from the service
        Employee theEmployee = employeeService.findById(theId);

        //set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", theEmployee);

        //send over to employee form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theId){
        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }




}
