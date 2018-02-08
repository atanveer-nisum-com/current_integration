package com.nisum.subscription.repository;

import com.nisum.subscription.model.Subscription;

import java.util.List;

public interface SubscriptionRepository {

    List<Subscription> fetchSubscriptions();
    Subscription getSubscription(String subscriptionId);
    Subscription getSubscription(String clientId, String customerId);
    Subscription save(Subscription subscription);
}
