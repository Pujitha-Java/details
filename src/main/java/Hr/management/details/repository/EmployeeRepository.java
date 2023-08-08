package Hr.management.details.repository;
        import Hr.management.details.entity.EmployeeEntity;
        import Hr.management.details.model.Employee;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.multipart.MultipartFile;

        import java.io.IOException;
        import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    List<EmployeeEntity> findAllByOrderByExperience();

    List<EmployeeEntity> findAllByOrderByExperienceDesc();

    List<EmployeeEntity> findAllByOrderByExperienceAsc();


}


