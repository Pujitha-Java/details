package Hr.management.details.service.Impl;



import Hr.management.details.entity.EmployeeEntity;
import Hr.management.details.model.Employee;
import Hr.management.details.repository.EmployeeRepository;
import Hr.management.details.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private  EmployeeRepository employeeRepository;


    @Override
    public List<Employee> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees=new ArrayList<>();
        for(EmployeeEntity entity:employeeEntities){
            employees.add(ToEmployee(entity));
        }
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Optional<EmployeeEntity> employeeEntities = employeeRepository.findById(id);
        if(employeeEntities.isPresent()){
            return ToEmployee(employeeEntities.get());
        }
        return null;
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
        return null;
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
            EmployeeEntity newEmployee = employeeRepository.findById(id).orElse(null);
            if (newEmployee != null) {
                newEmployee.setName(employee.getName());
                newEmployee.setAge(employee.getAge());
                newEmployee.setOrganization(employee.getOrganization());
                newEmployee.setType(employee.getType());
                newEmployee.setExperience(employee.getExperience());
                newEmployee = employeeRepository.save(newEmployee);
                return ToEmployee(newEmployee);
            }
            return null;
        }


    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllByOrderByExperience() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByOrderByExperience();
        List<Employee> employees=new ArrayList<>();
        for(EmployeeEntity entity:employeeEntities){
            employees.add(ToEmployee(entity));
        }
        return employees;
    }

    @Override
    public Employee getEmployeeHighestExperience() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByOrderByExperienceDesc();
        if ((employeeEntities == null) || employeeEntities.isEmpty()) {
            return null;
        }
        EmployeeEntity highestExperienceEmployee = employeeEntities.get(0);
        for (int i=1;i<employeeEntities.size();i++) {
            EmployeeEntity employee =employeeEntities.get(i);
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
        for (int i=1;i<employeeEntities.size();i++) {
            EmployeeEntity employee = employeeEntities.get(i);
            if (employee.getExperience() < lowestExperienceEmployee.getExperience()) {
                lowestExperienceEmployee = employee;
            }
        }
        return ToEmployee(lowestExperienceEmployee);
    }

    @Override
    public List<EmployeeEntity> uploadFile(MultipartFile file) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(file.getInputStream()));
        List<EmployeeEntity> employees = new ArrayList<>();
        br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 6) {
                String idStr = data[0].trim();
                String name = data[1].trim();
                String ageStr = data[2].trim();
                String type = data[3].trim();
                String organization=data[4].trim();
                String experienceStr=data[5].trim();
                int id = Integer.parseInt(idStr);
                int age = Integer.parseInt(ageStr);
                float experience=Float.parseFloat(experienceStr);
                EmployeeEntity employee = new EmployeeEntity(id, name, age,type,organization ,experience);
                employees.add(employee);
            }
        }
        employeeRepository.saveAll(employees);
        return employees;
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





