package uz.pdp.lesson1.service;

import org.springframework.stereotype.Service;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.entity.Company;
import uz.pdp.lesson1.payload.AddressDto;
import uz.pdp.lesson1.payload.CompanyDto;
import uz.pdp.lesson1.payload.Response;
import uz.pdp.lesson1.repository.AddressRepository;
import uz.pdp.lesson1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    final CompanyRepository companyRepository;
    final AddressRepository addressRepository;

    public CompanyService(CompanyRepository companyRepository, AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }


    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public Company getOneCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new Company();
        }
        return optionalCompany.get();
    }

    public Response add(CompanyDto companyDto) {
        boolean exists = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (exists){
            return new Response("This address have in db", false);
        }
        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new Response("Address id not found", false);
        }
        Address addressDb = optionalAddress.get();

        company.setAddress(addressDb);

        companyRepository.save(company);

        return new Response("Address saved", true);
    }

    public Response update(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent()){
            return new Response("Address id not found", false);
        }

        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()){
            return new Response("Address id not found", false);
        }
        Address address = optionalAddress.get();

        company.setAddress(address);

        companyRepository.save(company);

        return new Response("Company updated", true);
    }

    public Response delete(Integer id) {
        try{
            companyRepository.deleteById(id);
            return new Response("Company deleted", true);
        }catch (Exception e){
            return new Response("Company id not found", false);
        }
    }
}
