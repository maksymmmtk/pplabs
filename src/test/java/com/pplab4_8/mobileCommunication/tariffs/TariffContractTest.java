package com.pplab4_8.mobileCommunication.tariffs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TariffContractTest {

    private TariffContract tariff;

    @BeforeEach
    void setUp() {
        tariff = new TariffContract("Contract Tariff", 100.0f, 200, 20.0f, "International Calls");
    }

    @Test
    void testGettersAndSetters() {
        assertEquals("Contract Tariff", tariff.getName());
        assertEquals(100.0f, tariff.getMonthlyFee());
        assertEquals(200, tariff.getCallMinutesCount());
        assertEquals(20.0f, tariff.getInternetGigabytesCount());
        assertEquals("International Calls", tariff.getAdditionalServices());

        tariff.setName("Updated Tariff");
        tariff.setMonthlyFee(120.0f);
        tariff.setCallMinutesCount(250);
        tariff.setInternetGigabytesCount(25.0f);
        tariff.setAdditionalServices("Unlimited Data");

        assertEquals("Updated Tariff", tariff.getName());
        assertEquals(120.0f, tariff.getMonthlyFee());
        assertEquals(250, tariff.getCallMinutesCount());
        assertEquals(25.0f, tariff.getInternetGigabytesCount());
        assertEquals("Unlimited Data", tariff.getAdditionalServices());
    }

    @Test
    void testToString() {
        String expected = "Тариф: Contract Tariff, Місячна плата: 100,00, Хвилини дзвінків: 200, Гігабайти інтернету: 20,00, Кількість клієнтів: 0, Додаткові послуги: International Calls";
        assertEquals(expected, tariff.toString());
    }

    @Test
    void testAddSubscriber() {
        tariff.addSubscriber();
        assertEquals(1, tariff.getSubscribersCount());
    }

    @Test
    void testSubSubscriber() {
        tariff.addSubscriber(); 
        tariff.subSubscriber(); 
        assertEquals(0, tariff.getSubscribersCount());
    }
}