package uz.pdp.lesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
//    boolean existsByHomeNumber(String homeNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
