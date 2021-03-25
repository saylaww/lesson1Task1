package uz.pdp.lesson1.service;

import org.springframework.stereotype.Service;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.entity.Company;
import uz.pdp.lesson1.entity.Department;
import uz.pdp.lesson1.payload.AddressDto;
import uz.pdp.lesson1.payload.DepartmentDto;
import uz.pdp.lesson1.payload.Response;
import uz.pdp.lesson1.repository.CompanyRepository;
import uz.pdp.lesson1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    final DepartmentRepository departmentRepository;
    final CompanyRepository companyRepository;

    public DepartmentService(DepartmentRepository departmentRepository, CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public Department getOneDepartment(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()){
            return new Department();
        }
        return optionalDepartment.get();
    }

    public Response add(DepartmentDto departmentDto) {
        boolean exists = departmentRepository.existsByName(departmentDto.getName());
        if (exists){
            return new Response("This Department have in db", false);
        }
        Department department = new Department();
        department.setName(departmentDto.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new Response("Company id not found", false);
        }
        Company company = optionalCompany.get();
        department.setCompany(company);

        departmentRepository.save(department);

        return new Response("Department saved", true);
    }

    public Response update(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()){
            return new Response("Department id not found", false);
        }
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new Response("Company id not found", false);
        }
        Company company = optionalCompany.get();

        department.setCompany(company);

        departmentRepository.save(department);

        return new Response("Department updated", true);
    }

    public Response delete(Integer id) {
        try{
            departmentRepository.deleteById(id);
            return new Response("Department deleted", true);
        }catch (Exception e){
            return new Response("Department id not found", false);
        }
    }
}
