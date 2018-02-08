package com.nisum.subscription.service;

import com.nisum.subscription.model.Subscription;
import com.nisum.subscription.repository.SubscriptionRepository;
import com.nisum.subscription.util.SubscriptionStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SubscriptionServiceTest extends BaseServiceTest {
    @Autowired
    private SubscriptionService service;
    @MockBean
    private SubscriptionRepository repository;

    private Subscription subscription;

    @Before
    public void before() {
        subscription = new Subscription();
        subscription.setId("1");
        subscription.setClientId("MACYS");
        subscription.setCustomerId("CUSTOMER1");
        subscription.setProductId("PRODUCT1");
        subscription.setFrequency(3);
        subscription.setStartDate(new Date(2016, 12, 11).getTime());
        subscription.setEndDate(new Date(2017, 12, 11).getTime());
        subscription.setDeliveryDate(25);
        subscription.setSkipDelivery(false);
        subscription.setStatus(SubscriptionStatus.ACTIVE);

        Subscription sub1 = new Subscription();
        sub1.setId("2");
        sub1.setClientId("MACYS");
        sub1.setCustomerId("CUSTOMER2");
        sub1.setProductId("PRODUCT2");
        sub1.setFrequency(3);
        sub1.setStartDate(new Date(2015, 11, 11).getTime());
        sub1.setEndDate(new Date(2017, 11, 11).getTime());
        sub1.setDeliveryDate(14);
        sub1.setSkipDelivery(false);
        sub1.setStatus(SubscriptionStatus.ACTIVE);

        Subscription saved = new Subscription();
        saved.setClientId("MACYS");
        saved.setCustomerId("CUSTOMER1");
        saved.setProductId("PRODUCT1");
        saved.setFrequency(3);
        saved.setStartDate(new Date(2016, 12, 11).getTime());
        saved.setEndDate(new Date(2017, 12, 11).getTime());
        saved.setDeliveryDate(25);
        saved.setSkipDelivery(false);
        saved.setStatus(SubscriptionStatus.ACTIVE);

        Subscription skippedDelivery = new Subscription();
        skippedDelivery.setId("3");
        skippedDelivery.setClientId("MACYS");
        skippedDelivery.setCustomerId("CUSTOMER1");
        skippedDelivery.setProductId("PRODUCT1");
        skippedDelivery.setFrequency(3);
        skippedDelivery.setStartDate(new Date(2016, 12, 11).getTime());
        skippedDelivery.setEndDate(new Date(2017, 12, 11).getTime());
        skippedDelivery.setDeliveryDate(25);
        skippedDelivery.setSkipDelivery(true);
        skippedDelivery.setStatus(SubscriptionStatus.ACTIVE);

        Subscription inactiveStatus = new Subscription();
        inactiveStatus.setId("4");
        inactiveStatus.setClientId("MACYS");
        inactiveStatus.setCustomerId("CUSTOMER1");
        inactiveStatus.setProductId("PRODUCT1");
        inactiveStatus.setFrequency(3);
        inactiveStatus.setStartDate(new Date(2016, 12, 11).getTime());
        inactiveStatus.setEndDate(new Date(2017, 12, 11).getTime());
        inactiveStatus.setDeliveryDate(25);
        inactiveStatus.setSkipDelivery(true);
        inactiveStatus.setStatus(SubscriptionStatus.INACTIVE);

        List<Subscription> subscriptionList = Arrays.asList(subscription, inactiveStatus, saved);

        Mockito.when(repository.getSubscription("1")).thenReturn(subscription);
        Mockito.when(repository.getSubscription("2")).thenReturn(sub1);
        Mockito.when(repository.getSubscription("3")).thenReturn(skippedDelivery);
        Mockito.when(repository.getSubscription("4")).thenReturn(inactiveStatus);
        Mockito.when(repository.getSubscription("MACYS", "CUSTOMER1")).thenReturn(subscription);
        Mockito.when(repository.fetchSubscriptions()).thenReturn(subscriptionList);
        Mockito.when(repository.save(saved)).thenReturn(sub1);
    }

    @Test
    public void testSubscriptionForEligibleProduct() {
        boolean isEligible = service.isEligibleProduct("1","PRODUCT1");
        Assert.assertTrue(isEligible);
    }

    @Test
    public void testSubscriptionForNonEligibleProduct() {
        boolean isEligible = service.isEligibleProduct("1","PRODUCT3");
        Assert.assertFalse(isEligible);
    }

    @Test
    public void testAddNewSubscription() {
        Subscription save = new Subscription();
        save.setClientId("MACYS");
        save.setCustomerId("CUSTOMER1");
        save.setProductId("PRODUCT1");
        save.setFrequency(3);
        save.setStartDate(new Date(2016, 12, 11).getTime());
        save.setEndDate(new Date(2017, 12, 11).getTime());
        save.setDeliveryDate(25);
        save.setSkipDelivery(false);
        save.setStatus(SubscriptionStatus.ACTIVE);

        Subscription savedSubscription = service.save(save);
        Assert.assertEquals(savedSubscription, subscription);
    }

    @Test
    public void testFetchSubscriptionsWithSkippedDelivery() {
        List<Subscription> skippedDeliveries = service.skippedDeliveries(new Date(2017, 11, 14).getTime());

        Subscription skippedDelivery = new Subscription();
        skippedDelivery.setClientId("MACYS");
        skippedDelivery.setCustomerId("CUSTOMER1");
        skippedDelivery.setProductId("PRODUCT1");
        skippedDelivery.setFrequency(3);
        skippedDelivery.setStartDate(new Date(2016, 12, 11).getTime());
        skippedDelivery.setEndDate(new Date(2017, 12, 11).getTime());
        skippedDelivery.setDeliveryDate(25);
        skippedDelivery.setSkipDelivery(true);
        skippedDelivery.setStatus(SubscriptionStatus.ACTIVE);

        List<Subscription> testSkippedDeliveries = Arrays.asList(skippedDelivery);
        Assert.assertEquals(skippedDeliveries, testSkippedDeliveries);
    }

    @Test
    public void testFetchSubscriptionStatusActive() {
        SubscriptionStatus status = service.status("1");
        Assert.assertEquals(SubscriptionStatus.ACTIVE, status);
    }

    @Test
    public void testFetchSubscriptionStatusInactive() {
        SubscriptionStatus status = service.status("4");
        Assert.assertEquals(SubscriptionStatus.INACTIVE, status);
    }

    @Test
    public void testModifySubscriptionStatus() {
        service.changeStatus("4", SubscriptionStatus.CANCELLED);
        //TODO: need to complete.
    }
}
