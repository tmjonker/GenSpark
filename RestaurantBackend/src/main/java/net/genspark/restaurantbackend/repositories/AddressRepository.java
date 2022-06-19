package net.genspark.restaurantbackend.repositories;

import net.genspark.restaurantbackend.entities.address.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    Optional<Address> findByAddress1(String address1);
}
