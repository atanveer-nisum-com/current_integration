package com.nisum.promotion.test.services;

import com.nisum.promotion.model.OrderAmountDiscount;
import com.nisum.promotion.repository.OrderDiscountRepository;
import com.nisum.promotion.service.OrderDiscountService;
import com.nisum.promotion.service.impl.OrderDiscountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDiscountServiceTest extends BaseServiceTest {

    @Autowired
    private OrderDiscountService orderDiscountService;

    @MockBean
    private OrderDiscountRepository orderDiscountRepository;

    private static final Double ORDER_AMOUNT = 10d;
    private static final Double ORDER_DISCOUNT = 1d;
    private List<OrderAmountDiscount> orderDiscounts;

    @Configuration
    static class ContextConfiguration {

        @Bean
        OrderDiscountService orderDiscountService() {
            return new OrderDiscountServiceImpl();
        }
    }

    @Before
    public void setup() {
        orderDiscounts = new ArrayList<>();
    }

    @Test
    public void shouldReturnDiscountWhenOrderHasDiscount() {
        OrderAmountDiscount discount = new OrderAmountDiscount();
        discount.setDiscountValue(BigDecimal.ONE);
        discount.setFromAmount(discount.getDiscountValue());
        discount.setToAmount(discount.getDiscountValue());
        orderDiscounts.add(discount);

        Mockito.when(orderDiscountRepository.getAllActiveDiscounts()).thenReturn(orderDiscounts);

        assertEquals(orderDiscountService.getOrderDiscount(ORDER_AMOUNT),
                orderDiscounts.get(0).getDiscountValue().doubleValue(), 0);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldThrowExceptionWhenNoOrderDiscountsFound() {
        Mockito.when(orderDiscountRepository.getAllActiveDiscounts()).thenReturn(orderDiscounts);

        orderDiscountService.getOrderDiscount(ORDER_AMOUNT);
    }
}