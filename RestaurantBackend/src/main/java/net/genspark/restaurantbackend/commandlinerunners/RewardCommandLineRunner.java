package net.genspark.restaurantbackend.commandlinerunners;

import net.genspark.restaurantbackend.entities.reward.Reward;
import net.genspark.restaurantbackend.repositories.RewardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RewardCommandLineRunner implements CommandLineRunner {

    private RewardRepository rewardRepository;

    public RewardCommandLineRunner(RewardRepository rewardRepository) {

        this.rewardRepository = rewardRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!rewardRepository.existsById(1)) {

            Reward reward1 = new Reward("5% off", "Customer receives 5% off their purchase");
            Reward reward2 = new Reward("10% off", "Customer receives 10% off their purchase");
            Reward reward3 = new Reward("15% off", "Customer receives 15% off their purchase");

            rewardRepository.save(reward1);
            rewardRepository.save(reward2);
            rewardRepository.save(reward3);
        }
    }
}
