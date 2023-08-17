package Hr.management.details.controller;
import Hr.management.details.entity.EmployeeEntity;
import Hr.management.details.model.Employee;
import Hr.management.details.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


    @RestController
    @RequestMapping("/employees")
    public class EmployeeController {
        @Autowired
        private EmployeeService employeeService;


        @GetMapping
        public List<Employee> findAllEmployees() {

            return employeeService.findAll();
        }

        @GetMapping("/{id}")
        public Employee findEmployeeById(@PathVariable int id) {

            return employeeService.findById(id);
        }

        @PostMapping
        public Employee saveEmployee(@RequestBody Employee employee) {

            return employeeService.saveEmployee(employee);
        }



        @PutMapping("/{id}")
        public Employee updateEmployee(
                @PathVariable int id,
                @RequestBody Employee employeeModel) {
            return  employeeService.updateEmployee(id, employeeModel);
        }
        @DeleteMapping("/{id}")
        public void deleteEmployee(@PathVariable int id) {

            employeeService.deleteEmployee(id);
        }

        @GetMapping("/sorted")
        public List<Employee> findAllEmployeesSortedByExperience() {
            return employeeService.findAllByOrderByExperience();
        }

        @GetMapping("/highest-experience")
        public Employee getEmployeeHighestExperience() {

            return employeeService.getEmployeeHighestExperience();
        }

        @GetMapping("/lowest-experience")
        public Employee getEmployeeLowestExperience() {

            return employeeService.getEmployeeLowestExperience();
        }

          @PostMapping("/upload-file")
        public List<EmployeeEntity> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
            return employeeService.uploadFile(file);
        }

        //second highest. use sort.

    }



