package uz.pdp.lesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByHomeNumber(String homeNumber);
}
