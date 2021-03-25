package uz.pdp.lesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
//    boolean existsByHomeNumber(String homeNumber);
    boolean existsByName(String name);
}
