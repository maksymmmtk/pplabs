package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AddTariffTest {
    private MobileNetwork network;
    private MobileNetworkService service;
    private AddTariff addTariff;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);
        addTariff = new AddTariff(service);
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testAddPrepaidTariff() {
        String userInput = "1\nPrepaid Plan\n100\n200\n5\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        addTariff.execute();

        assertEquals(1, network.getTariffs().size());
        TariffPrepaid addedTariff = (TariffPrepaid) network.getTariffs().get(0);
        assertEquals("Prepaid Plan", addedTariff.getName());
        assertEquals(100, addedTariff.getMonthlyFee());
        assertEquals(200, addedTariff.getCallMinutesCount());
        assertEquals(5, addedTariff.getInternetGigabytesCount());
        assertTrue(outputStream.toString().contains("Передплачений тариф успішно додано."));
    }

    @Test
    void testAddContractTariff() {
        String userInput = "2\nContract Plan\n150\n300\n10\nAdditional Services\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        addTariff.execute();

        assertEquals(1, network.getTariffs().size());
        TariffContract addedTariff = (TariffContract) network.getTariffs().get(0);
        assertEquals("Contract Plan", addedTariff.getName());
        assertEquals(150, addedTariff.getMonthlyFee());
        assertEquals(300, addedTariff.getCallMinutesCount());
        assertEquals(10, addedTariff.getInternetGigabytesCount());
        assertEquals("Additional Services", addedTariff.getAdditionalServices());
        assertTrue(outputStream.toString().contains("Контрактний тариф успішно додано."));
    }

    @Test
    void testInvalidChoice() {
        String userInput = "3\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        addTariff.execute();

        assertEquals(0, network.getTariffs().size());
        assertTrue(outputStream.toString().contains("Невірний вибір. Тариф не додано."));
    }

    @Test
    void testGetName() {
        // Створюємо екземпляр MobileNetworkService та передаємо його в AddTariff
        MobileNetwork network = new MobileNetwork();
        MobileNetworkService service = new MobileNetworkService(network);
        AddTariff addTariff = new AddTariff(service);

        // Перевіряємо, чи повертає метод getName() правильне значення
        assertEquals("Додати тариф", addTariff.getName());
    }
}