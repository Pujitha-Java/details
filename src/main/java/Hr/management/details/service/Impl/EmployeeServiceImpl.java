package Hr.management.details.service.Impl;



import Hr.management.details.entity.EmployeeEntity;
import Hr.management.details.model.Employee;
import Hr.management.details.model.EmployeeType;
import Hr.management.details.repository.EmployeeRepository;
import Hr.management.details.service.EmployeeService;
import jakarta.persistence.Enumerated;
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
            employees.add(toEmployee(entity));
        }
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Optional<EmployeeEntity> employeeEntities = employeeRepository.findById(id);
        if(employeeEntities.isPresent()){
            return toEmployee(employeeEntities.get());
        }
        return null;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        EmployeeEntity employeeEntity = toEmployeeEntity(employee);
        EmployeeEntity saved = employeeRepository.save(employeeEntity);
        return toEmployee(saved);

    }



    @Override
    public Employee updateEmployee(int id, Employee employee) {
            EmployeeEntity oldEmployee = employeeRepository.findById(id).orElse(null);
            if (oldEmployee != null) {
                // check if old employee name is not equal to new employee name
                if(!oldEmployee.getName().equals(employee.getName())){
                    oldEmployee.setName(employee.getName());
                }
                else {

                    oldEmployee.setName(employee.getName());
                    oldEmployee.setAge(employee.getAge());
                    oldEmployee.setOrganization(employee.getOrganization());
                    oldEmployee.setExperience(employee.getExperience());
                    EmployeeEntity updated = employeeRepository.save(oldEmployee);
                    return toEmployee(updated);
                }
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
            employees.add(toEmployee(entity));
        }
        return employees;
    }

    @Override
    public Employee getEmployeeHighestExperience() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByOrderByExperienceDesc();
        if ((employeeEntities == null) || employeeEntities.isEmpty()) {
            return null;
        }
        // 6,5,4
        EmployeeEntity highestExperienceEmployee = employeeEntities.get(0);
        return  toEmployee(highestExperienceEmployee);
    }

    @Override
    public Employee getEmployeeLowestExperience() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByOrderByExperienceAsc();
        if ((employeeEntities == null) || employeeEntities.isEmpty()) {
            return null;
        }
        //2,3,4
        EmployeeEntity lowestExperienceEmployee = employeeEntities.get(0);

        return toEmployee(lowestExperienceEmployee);
    }

    // get all employee. u will get list
    // use sorting and get the highest and lowest.
@Override
public Employee getSecondHighestExperience() {
    List<EmployeeEntity> employeeEntities = employeeRepository.findAllByOrderByExperienceDesc();
    EmployeeEntity SecondHighestExperience = null;
    if ((employeeEntities.size() >= 2)) {
        SecondHighestExperience = employeeEntities.get(1);
    }
    return toEmployee(SecondHighestExperience);
}
    @Override
    public Employee getSecondLowestExperience() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAllByOrderByExperienceAsc();
        EmployeeEntity SecondLowestExperience = null;
        if ((employeeEntities.size() >= 2)) {
            SecondLowestExperience = employeeEntities.get(1);
        }
        return toEmployee(SecondLowestExperience);
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
                String idStr = data[0];
                int id = Integer.parseInt(idStr);
                String name = data[1];
                String ageStr = data[2];
                int age = Integer.parseInt(ageStr);
                String organization=data[3];
                String typeStr=data[4];
                //convert the string to Enum EmployeeType
                //EmployeeType type= EmployeeType.valueOf(EmployeeType.class,typeStr);
                EmployeeType type = Enum.valueOf(EmployeeType.class,typeStr);
                String experienceStr=data[5];

                float experience=Float.parseFloat(experienceStr);
                EmployeeEntity employee = new EmployeeEntity(id, name, age,type,organization,experience);
                employees.add(employee);
            }
        }
        employeeRepository.saveAll(employees);
        return employees;
    }




    // method name should not start with capital letter.
    private Employee toEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setName(employeeEntity.getName());
        employee.setAge(employeeEntity.getAge());
        employee.setType(employeeEntity.getType());
        employee.setOrganization(employeeEntity.getOrganization());
        employee.setExperience(employeeEntity.getExperience());
        return employee;
    }

    private EmployeeEntity toEmployeeEntity(Employee employee) {
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





