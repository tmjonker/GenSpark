package net.genspark.restaurantbackend.controllers;

import net.genspark.restaurantbackend.entities.purchase.Purchase;
import net.genspark.restaurantbackend.purchaserequest.PurchaseRequest;
import net.genspark.restaurantbackend.services.PurchaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {

        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase")
    public Purchase purchaseItem(@RequestBody PurchaseRequest purchaseRequest) {

        return purchaseService.addPurchase(purchaseRequest);
    }
}
