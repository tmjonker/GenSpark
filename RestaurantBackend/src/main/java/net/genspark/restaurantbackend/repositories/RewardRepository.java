package net.genspark.restaurantbackend.repositories;

import net.genspark.restaurantbackend.entities.reward.Reward;
import org.springframework.data.repository.CrudRepository;

public interface RewardRepository extends CrudRepository<Reward, Integer> {
}
