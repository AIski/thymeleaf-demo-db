package pl.alski.springboot.thymeleafdemo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.alski.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

//@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //thats it, SpringData JPA does all the magic

    //add a method to sort by last name

    public List<Employee> findAllByOrderByLastNameAsc();
    //Spring Data jpa will parse the method name, looks for a specific format and pattern
   // creates appropiriate query behind the scenes
}
