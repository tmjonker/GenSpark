package net.genspark.restaurantbackend.controllers;

import net.genspark.restaurantbackend.entities.reward.Reward;
import net.genspark.restaurantbackend.services.RewardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RewardController {

    private RewardService rewardService;

    public RewardController(RewardService rewardService) {

        this.rewardService = rewardService;
    }

    @GetMapping("/rewards")
    public List<Reward> getRewards(@RequestBody Map<String, String> usernameMap) {

        return rewardService.getRewards(usernameMap.get("username"));
    }
}
