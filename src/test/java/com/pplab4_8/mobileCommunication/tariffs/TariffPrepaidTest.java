package com.pplab4_8.mobileCommunication.tariffs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TariffPrepaidTest {

    private TariffPrepaid tariff;

    @BeforeEach
    void setUp() {
        tariff = new TariffPrepaid("Test Tariff", 50.0f, 100, 10.0f);
    }

    @Test
    void testGetters() {
        assertEquals("Test Tariff", tariff.getName());
        assertEquals(50.0f, tariff.getMonthlyFee());
        assertEquals(100, tariff.getCallMinutesCount());
        assertEquals(10.0f, tariff.getInternetGigabytesCount());
        assertEquals(0, tariff.getSubscribersCount());
    }

    @Test
    void testSetters() {
        tariff.setName("New Name");
        tariff.setMonthlyFee(60.0f);
        tariff.setCallMinutesCount(200);
        tariff.setInternetGigabytesCount(15.0f);

        assertEquals("New Name", tariff.getName());
        assertEquals(60.0f, tariff.getMonthlyFee());
        assertEquals(200, tariff.getCallMinutesCount());
        assertEquals(15.0f, tariff.getInternetGigabytesCount());
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

    @Test
    void testToString() {
        String expected = "Тариф: Test Tariff, Місячна плата: 50,00, Хвилини дзвінків: 100, Гігабайти інтернету: 10,00, Кількість клієнтів: 0";
        assertEquals(expected, tariff.toString());
    }
}