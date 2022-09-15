package pl.alski.springboot.thymeleafdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.alski.springboot.thymeleafdemo.dao.EmployeeRepository;
import pl.alski.springboot.thymeleafdemo.entity.Employee;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    //constructor Injection of EmployeeDAO
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }


    @Override
//    @Transactional no needed since JpaRepository provides this functionality
    public List<Employee> findAll() {

        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
//    @Transactional no needed since JpaRepository provides this functionality
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return theEmployee;
    }

    @Override
    //    @Transactional no needed since JpaRepository provides this functionality
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
//    @Transactional no needed since JpaRepository provides this functionality
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);

    }
}
