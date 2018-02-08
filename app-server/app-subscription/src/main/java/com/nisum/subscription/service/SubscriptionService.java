package com.nisum.subscription.service;

import com.nisum.subscription.model.Subscription;
import com.nisum.subscription.util.SubscriptionStatus;

import java.util.List;

public interface SubscriptionService {

    Subscription save(Subscription subscription);
    List<Subscription> skippedDeliveries(Long timestamp);
    SubscriptionStatus status(String subscriptionId);
    Subscription changeStatus(String subscriptionId, SubscriptionStatus status);
    boolean isEligibleProduct(String subscriptionId, String productId);
}
