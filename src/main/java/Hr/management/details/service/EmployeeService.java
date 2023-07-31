package Hr.management.details.service;


import Hr.management.details.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
        List<Employee> findAll();

        Optional<Employee> findById(int id);

        Employee saveEmployee(Employee employee);

        Employee updateEmployee(Employee employee);

        void deleteEmployee(int id);

        List<Employee> findAllByOrderByExperience();

        Employee getEmployeeHighestExperience();

        Employee getEmployeeLowestExperience();
    }

