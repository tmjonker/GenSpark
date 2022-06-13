package net.genspark.restaurantbackend.repositories;

import net.genspark.restaurantbackend.entities.purchase.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
}
