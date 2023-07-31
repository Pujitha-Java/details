package Hr.management.details.repository;
        import Hr.management.details.entity.EmployeeEntity;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    List<EmployeeEntity> findAllByOrderByExperience();

    List<EmployeeEntity> findAllByOrderByExperienceDesc();

    List<EmployeeEntity> findAllByOrderByExperienceAsc();


}
