package uz.pdp.lesson1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.entity.Company;
import uz.pdp.lesson1.payload.AddressDto;
import uz.pdp.lesson1.payload.CompanyDto;
import uz.pdp.lesson1.payload.Response;
import uz.pdp.lesson1.service.CompanyService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompany(){
        List<Company> companyList = companyService.getAllCompany();
        return ResponseEntity.ok(companyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id){
        Company company = companyService.getOneCompany(id);
        return ResponseEntity.ok(company);
    }

    @PostMapping
    public ResponseEntity<Response> addCompany(@Valid @RequestBody CompanyDto companyDto){
        Response response = companyService.add(companyDto);

        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCompany(@PathVariable Integer id,@Valid @RequestBody CompanyDto companyDto){
        Response response = companyService.update(id, companyDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCompany(@PathVariable Integer id){
        Response response = companyService.delete(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.NO_CONTENT:HttpStatus.CONFLICT).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
