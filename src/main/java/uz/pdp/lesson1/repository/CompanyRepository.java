package uz.pdp.lesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
//    boolean existsByHomeNumber(String homeNumber);
    boolean existsByCorpName(String corpName);
}
