package net.genspark.restaurantbackend.entities.reward;

import javax.persistence.*;

@Entity
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "reward_name", nullable = false)
    private String rewardName;

    @Column(name = "reward_description", nullable = false)
    private String rewardDescription;

    public Reward(String rewardName, String rewardDescription) {
        this.rewardName = rewardName;
        this.rewardDescription = rewardDescription;
    }

    public Reward() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }

    public void setRewardDescription(String rewardDescription) {
        this.rewardDescription = rewardDescription;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "id=" + id +
                ", rewardName='" + rewardName + '\'' +
                ", rewardDescription='" + rewardDescription + '\'' +
                '}';
    }
}
