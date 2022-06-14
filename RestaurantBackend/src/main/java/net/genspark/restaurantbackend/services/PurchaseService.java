package net.genspark.restaurantbackend.services;

import net.genspark.restaurantbackend.entities.menu.MenuItem;
import net.genspark.restaurantbackend.entities.purchase.Purchase;
import net.genspark.restaurantbackend.entities.user.User;
import net.genspark.restaurantbackend.purchaserequest.PurchaseRequest;
import net.genspark.restaurantbackend.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    PurchaseRepository purchaseRepository;
    CustomUserDetailsService userDetailsService;
    RewardService rewardService;
    MenuService menuService;

    public PurchaseService(PurchaseRepository purchaseRepository, CustomUserDetailsService userDetailsService,
                           RewardService rewardService, MenuService menuService) {

        this.purchaseRepository = purchaseRepository;
        this.userDetailsService = userDetailsService;
        this.rewardService = rewardService;
        this.menuService = menuService;
    }

    public Purchase addPurchase(PurchaseRequest purchaseRequest) {

        User user = (User) userDetailsService.loadUserByUsername(purchaseRequest.getUsername());
        MenuItem menuItem = menuService.getMenuItemById(purchaseRequest.getMenuId());
        Purchase purchase = new Purchase(purchaseRequest.getDate(), purchaseRequest.getPrice());
        purchase.addMenuItem(menuItem);

        user.addPurchase(purchase);

        double totalPurchases = user.getPurchases().stream().map(Purchase::getPrice).reduce(0.0, (a, b) -> a + b);

        if (totalPurchases > 150.00) {
            user.addReward(rewardService.getReward(3));
        } else if (totalPurchases > 100.00) {
            user.addReward(rewardService.getReward(2));
        } else if (totalPurchases > 50.00) {
            user.addReward(rewardService.getReward(1));
        }

        purchaseRepository.save(purchase);

        userDetailsService.saveUser(user);

        return purchase;
    }
}
