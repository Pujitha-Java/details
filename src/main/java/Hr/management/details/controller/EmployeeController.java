package Hr.management.details.controller;
import Hr.management.details.model.Employee;
import Hr.management.details.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/employees")
    public class EmployeeController {
        private final EmployeeService employeeService;

        @Autowired
        public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        @GetMapping
        public List<Employee> findAllEmployees() {
            return employeeService.findAll();
        }

        @GetMapping("/{id}")
        public Optional<Employee> findEmployeeById(@PathVariable int id) {
            return employeeService.findById(id);
        }

        @PostMapping
        public Employee saveEmployee(@RequestBody Employee employee) {
            return employeeService.saveEmployee(employee);
        }

        @PutMapping("/{id}")
        public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
            employee.setId(id);
            return employeeService.updateEmployee(employee);
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
    }
