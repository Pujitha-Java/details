package Hr.management.details.service.Impl;



import Hr.management.details.entity.EmployeeEntity;
import Hr.management.details.model.Employee;
import Hr.management.details.repository.EmployeeRepository;
import Hr.management.details.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private  EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(this::ToEmployee).collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> findById(int id) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(id);
        return employeeEntityOptional.map(this::ToEmployee);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeEntity employeeEntity = ToEmployeeEntity(employee);
        employeeRepository.save(employeeEntity);
        employee.setId(employeeEntity.getId());
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        EmployeeEntity employeeEntity = ToEmployeeEntity(employee);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {

        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllByOrderByExperience() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByOrderByExperience();
        return employeeEntities.stream().map(this::ToEmployee).collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeHighestExperience() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByOrderByExperienceDesc();
        if ((employeeEntities == null) || employeeEntities.isEmpty()) {
            return null;
        }
        EmployeeEntity highestExperienceEmployee = employeeEntities.get(0);
        for (EmployeeEntity employee : employeeEntities) {
            if (employee.getExperience() > highestExperienceEmployee.getExperience()) {
                highestExperienceEmployee = employee;
            }
        }
        return  ToEmployee(highestExperienceEmployee);
    }


    @Override
    public Employee getEmployeeLowestExperience() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByOrderByExperienceAsc();
        if ((employeeEntities == null) || employeeEntities.isEmpty()) {
            return null;
        }
        EmployeeEntity lowestExperienceEmployee = employeeEntities.get(0);
        for (EmployeeEntity employee : employeeEntities) {
            if (employee.getExperience() < lowestExperienceEmployee.getExperience()) {
                lowestExperienceEmployee = employee;
            }
        }
            return  ToEmployee(lowestExperienceEmployee);
        }

    private Employee ToEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setName(employeeEntity.getName());
        employee.setAge(employeeEntity.getAge());
        employee.setOrganization(employeeEntity.getOrganization());
        employee.setType(employeeEntity.getType());
        employee.setExperience(employeeEntity.getExperience());
        return employee;
    }

    private EmployeeEntity ToEmployeeEntity(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employee.getId());
        employeeEntity.setName(employee.getName());
        employeeEntity.setAge(employee.getAge());
        employeeEntity.setOrganization(employee.getOrganization());
        employeeEntity.setType(employee.getType());
        employeeEntity.setExperience(employee.getExperience());
        return employeeEntity;
    }
}



