package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.reward.Reward;
import net.genspark.restaurantbackend.entities.user.User;
import net.genspark.restaurantbackend.repositories.RewardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    private RewardRepository rewardRepository;
    private CustomUserDetailsService userDetailsService;

    public RewardService(RewardRepository rewardRepository, CustomUserDetailsService userDetailsService) {

        this.rewardRepository = rewardRepository;
        this.userDetailsService = userDetailsService;
    }

    public List<Reward> getRewards(String username) {

        User user = (User) userDetailsService.loadUserByUsername(username);

        return user.getRewards();
    }

    public Reward getReward(int id) {

        return rewardRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
    }

    public boolean existsById(int id) {

        return rewardRepository.existsById(id);
    }
}
