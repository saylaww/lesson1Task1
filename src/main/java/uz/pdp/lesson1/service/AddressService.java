package uz.pdp.lesson1.service;

import org.springframework.stereotype.Service;
import uz.pdp.lesson1.entity.Address;
import uz.pdp.lesson1.payload.AddressDto;
import uz.pdp.lesson1.payload.Response;
import uz.pdp.lesson1.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Address getOneAddress(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new Address();
        }
        return optionalAddress.get();
    }

    public Response add(AddressDto addressDto) {
        boolean exists = addressRepository.existsByHomeNumber(addressDto.getHomeNumber());
        if (exists){
            return new Response("This address have in db", false);
        }
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());

        addressRepository.save(address);

        return new Response("Address saved", true);
    }

    public Response update(Integer id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()){
            return new Response("Address id not found", false);
        }
        Address addressDb = optionalAddress.get();
        addressDb.setStreet(addressDto.getStreet());
        addressDb.setHomeNumber(addressDto.getHomeNumber());

        addressRepository.save(addressDb);
        return new Response("Address updated", true);
    }

    public Response delete(Integer id) {
        try{
            addressRepository.deleteById(id);
            return new Response("Address deleted", true);
        }catch (Exception e){
            return new Response("Address id not found", false);
        }
    }
}
