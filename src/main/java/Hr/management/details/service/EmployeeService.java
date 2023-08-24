package Hr.management.details.service;


import Hr.management.details.entity.EmployeeEntity;
import Hr.management.details.model.Employee;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface EmployeeService {
        List<Employee> findAll();

        Employee findById(int id);

        Employee saveEmployee(Employee employee);

        Employee updateEmployee(int id, Employee employee);

        void deleteEmployee(int id);

        List<Employee> findAllByOrderByExperience();

        Employee getEmployeeHighestExperience();

        Employee getEmployeeLowestExperience();

        // get all employee. u will get list
        // use sorting and get the highest and lowest.
        Employee getSecondHighestExperience();

        Employee getSecondLowestExperience();

        List<EmployeeEntity> uploadFile(MultipartFile file) throws IOException;
}



