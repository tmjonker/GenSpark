package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.address.Address;
import net.genspark.restaurantbackend.repositories.AddressRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {

        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {

        return addressRepository.save(address);
    }

    public Address findAddress(Address address) {

        return addressRepository.findByAddress1(address.getAddress1()).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public boolean addressExists(Address address) {

        List<Address> addressList = Streamable.of(addressRepository.findAll()).toList();

        for (Address add : addressList) {

            if (add.getAddress1().equals(address.getAddress1()) && add.getZipCode() == address.getZipCode())
                return true;
        }

        return false;
    }
}
