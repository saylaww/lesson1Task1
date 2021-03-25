package uz.pdp.lesson1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.payload.AddressDto;
import uz.pdp.lesson1.payload.Response;
import uz.pdp.lesson1.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses(){
        List<Address> addressList = addressService.getAllAddress();
        return ResponseEntity.ok(addressList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Integer id){
        Address oneAddress = addressService.getOneAddress(id);
        return ResponseEntity.ok(oneAddress);
    }

    @PostMapping
    public ResponseEntity<Response> addAddress(@Valid @RequestBody AddressDto addressDto){
        Response response = addressService.add(addressDto);

        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateAddress(@PathVariable Integer id,@Valid @RequestBody AddressDto addressDto){
        Response response = addressService.update(id, addressDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAddress(@PathVariable Integer id){
        Response response = addressService.delete(id);
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
