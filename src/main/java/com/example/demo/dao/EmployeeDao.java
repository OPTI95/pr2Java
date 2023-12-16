package com.example.demo.dao;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDao {

    private static Long EMPLOYEE_COUNT = 0L;
    private List<Employee> employees;

    {
        employees = new ArrayList<>();

        employees.add(new Employee(++EMPLOYEE_COUNT, "John", "Doe", "Software Engineer"));
        employees.add(new Employee(++EMPLOYEE_COUNT, "Jane", "Smith", "Project Manager"));
    }

    public List<Employee> index() {
        return employees;
    }

    public Employee show(Long id) {
        return employees.stream().filter(employee -> employee.getId().equals(id)).findAny().orElse(null);
    }

    public void save(Employee employee) {
        employee.setId(++EMPLOYEE_COUNT);
        employees.add(employee);
    }

    public void update(Long id, Employee updatedEmployee) {
        Employee existingEmployee = show(id);
        if (existingEmployee != null) {
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setPosition(updatedEmployee.getPosition());
        }
    }

    public void delete(Long id) {
        employees.removeIf(employee -> employee.getId().equals(id));
    }
}
