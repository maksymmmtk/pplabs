package com.pplab4_8.mobileCommunication.users;

import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MobileSubscriberTest {
    private MobileSubscriber subscriber;
    private TariffPrepaid tariff;

    @BeforeEach
    void setUp() {
        tariff = new TariffPrepaid("Basic Tariff", 50.0f, 100, 5.0f);
        subscriber = new MobileSubscriber("John Doe", 30, "123456789", null, tariff);
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", subscriber.getName(), "Ім'я абонента не співпало.");
    }

    @Test
    void testSetName() {
        subscriber.setName("Jane Doe");
        assertEquals("Jane Doe", subscriber.getName(), "Ім'я абонента не змінено.");
    }

    @Test
    void testGetAge() {
        assertEquals(30, subscriber.getAge(), "Вік абонента не співпав.");
    }

    @Test
    void testSetAge() {
        subscriber.setAge(35);
        assertEquals(35, subscriber.getAge(), "Вік абонента не змінено.");
    }

    @Test
    void testGetPhoneNumber() {
        assertEquals("123456789", subscriber.getPhoneNumber(), "Номер телефону не співпав.");
    }

    @Test
    void testSetPhoneNumber() {
        subscriber.setPhoneNumber("987654321");
        assertEquals("987654321", subscriber.getPhoneNumber(), "Номер телефону не змінено.");
    }

    @Test
    void testGetTariff() {
        assertEquals(tariff, subscriber.getTariff(), "Тариф не співпав.");
    }

    @Test
    void testSetTariff() {
        TariffPrepaid newTariff = new TariffPrepaid("Premium Tariff", 100.0f, 300, 10.0f);
        subscriber.setTariff(newTariff);

        assertEquals(newTariff, subscriber.getTariff(), "Тариф не змінився.");
    }

    @Test
    void testSetNullTariff() {
        subscriber.setTariff(null);
        assertNull(subscriber.getTariff(), "Тариф має бути null.");
    }

    @Test
    void testViewPersonalTariff() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        subscriber.viewPersonalTariff();

        String[] outputs = outputStream.toString().split("\n");
        assertTrue(outputs[1].contains("Тариф: Basic Tariff, Місячна плата: 50,00, Хвилини дзвінків: 100, Гігабайти інтернету: 5,00, Кількість клієнтів: 1"), "Перевірка на вивід особистого тарифу");
    }
}