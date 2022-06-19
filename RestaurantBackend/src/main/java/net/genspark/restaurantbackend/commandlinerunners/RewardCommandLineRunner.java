package net.genspark.restaurantbackend.commandlinerunners;

import net.genspark.restaurantbackend.entities.menu.MenuItem;
import net.genspark.restaurantbackend.entities.reward.Reward;
import net.genspark.restaurantbackend.repositories.RewardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RewardCommandLineRunner implements CommandLineRunner {

    private RewardRepository rewardRepository;

    public RewardCommandLineRunner(RewardRepository rewardRepository) {

        this.rewardRepository = rewardRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Reward> items = getRewardItems();

        for(Reward item : items) {
            if(!rewardRepository.existsById(item.getId()))
                rewardRepository.save(item);
        }

    }

    private List<Reward> getRewardItems() {

        List<Reward> items = new ArrayList<>();

        items.add(new Reward("5% off", "Customer receives 5% off their purchase", 50.00));
        items.add(new Reward("10% off", "Customer receives 10% off their purchase", 100.00));
        items.add(new Reward("15% off", "Customer receives 15% off their purchase", 150.00));

        return items;
    }
}
