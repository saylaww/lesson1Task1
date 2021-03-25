package uz.pdp.lesson1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.entity.Worker;
import uz.pdp.lesson1.payload.AddressDto;
import uz.pdp.lesson1.payload.Response;
import uz.pdp.lesson1.payload.WorkerDto;
import uz.pdp.lesson1.service.WorkerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }


    @GetMapping
    public ResponseEntity<List<Worker>> getWorkers(){
        List<Worker> workerList = workerService.getAllWorkers();
        return ResponseEntity.ok(workerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Integer id){
        Worker worker = workerService.getOneWorker(id);
        return ResponseEntity.ok(worker);
    }

    @PostMapping
    public ResponseEntity<Response> addWorker(@Valid @RequestBody WorkerDto workerDto){
        Response response = workerService.add(workerDto);

        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateWorker(@PathVariable Integer id,@Valid @RequestBody WorkerDto workerDto){
        Response response = workerService.update(id, workerDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteWorker(@PathVariable Integer id){
        Response response = workerService.delete(id);
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
