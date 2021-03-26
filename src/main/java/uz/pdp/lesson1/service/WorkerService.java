package uz.pdp.lesson1.service;

import org.springframework.stereotype.Service;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.entity.Department;
import uz.pdp.lesson1.entity.Worker;
import uz.pdp.lesson1.payload.AddressDto;
import uz.pdp.lesson1.payload.Response;
import uz.pdp.lesson1.payload.WorkerDto;
import uz.pdp.lesson1.repository.AddressRepository;
import uz.pdp.lesson1.repository.DepartmentRepository;
import uz.pdp.lesson1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    final WorkerRepository workerRepository;
    final AddressRepository addressRepository;
    final DepartmentRepository departmentRepository;

    public WorkerService(WorkerRepository workerRepository, AddressRepository addressRepository, DepartmentRepository departmentRepository) {
        this.workerRepository = workerRepository;
        this.addressRepository = addressRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getOneWorker(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()){
            return new Worker();
        }
        return optionalWorker.get();
    }

    public Response add(WorkerDto workerDto) {
        boolean exists = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (exists){
            return new Response("This phoneNumber have in db", false);
        }
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());

//        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
//        if (!optionalAddress.isPresent()){
//            return new Response("Address id not found", false);
//        }
//        Address address = optionalAddress.get();

        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());

        Address savedAddress = addressRepository.save(address);

        worker.setAddress(savedAddress);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()){
            return new Response("Department id not found", false);
        }
        Department department = optionalDepartment.get();

        worker.setDepartment(department);

        workerRepository.save(worker);

        return new Response("Worker saved", true);
    }

    public Response update(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent()){
            return new Response("Worker id not found", false);
        }

        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());

//        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
//        if (!optionalAddress.isPresent()){
//            return new Response("Address id not found", false);
//        }
//        Address address = optionalAddress.get();

        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());

        Address savedAddress = addressRepository.save(address);

        worker.setAddress(savedAddress);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()){
            return new Response("Department id not found", false);
        }
        Department department = optionalDepartment.get();
        worker.setDepartment(department);

        workerRepository.save(worker);

        return new Response("Worker updated", true);
    }

    public Response delete(Integer id) {
        try{
            workerRepository.deleteById(id);
            return new Response("Worker deleted", true);
        }catch (Exception e){
            return new Response("Worker id not found", false);
        }
    }
}
