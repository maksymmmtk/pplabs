package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EditTariffTest {
    private MobileNetwork network;
    private MobileNetworkService service;
    private EditTariff editTariff;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);
        editTariff = new EditTariff(service);
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testEditTariffSuccessForPrepaid() {
        TariffPrepaid tariff = new TariffPrepaid("Basic", 100, 200, 5);
        service.addTariff(tariff);

        String userInput = "Basic\nPremium\n150\n300\n10\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        try {
            editTariff.execute();
        } finally {
            System.setIn(System.in); 
        }

        assertEquals(1, network.getTariffs().size());
        TariffPrepaid updatedTariff = network.getTariffs().get(0);
        assertEquals("Premium", updatedTariff.getName());
        assertEquals(150, updatedTariff.getMonthlyFee());
        assertEquals(300, updatedTariff.getCallMinutesCount());
        assertEquals(10, updatedTariff.getInternetGigabytesCount());
        assertTrue(outputStream.toString().contains("Тариф успішно оновлено."));
    }

    @Test
    void testEditTariffSuccessForContract() {
        TariffContract tariff = new TariffContract("Contract Basic", 200, 400, 15, "Basic Services");
        service.addTariff(tariff);

        String userInput = "Contract Basic\nContract Premium\n250\n500\n20\nPremium Services\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        editTariff.execute();

        assertEquals(1, network.getTariffs().size());
        TariffContract updatedTariff = (TariffContract) network.getTariffs().get(0);
        assertEquals("Contract Premium", updatedTariff.getName());
        assertEquals(250, updatedTariff.getMonthlyFee());
        assertEquals(500, updatedTariff.getCallMinutesCount());
        assertEquals(20, updatedTariff.getInternetGigabytesCount());
        assertEquals("Premium Services", updatedTariff.getAdditionalServices());
        assertTrue(outputStream.toString().contains("Тариф успішно оновлено."));
    }

    @Test
    void testEditTariffWithEmptyFields() {
        TariffPrepaid tariff = new TariffPrepaid("Basic", 100, 200, 5);
        service.addTariff(tariff);

        String userInput = "Basic\n\n0\n0\n0\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        editTariff.execute();

        assertEquals(1, network.getTariffs().size());
        TariffPrepaid updatedTariff = network.getTariffs().get(0);
        assertEquals("Basic", updatedTariff.getName());
        assertEquals(100, updatedTariff.getMonthlyFee());
        assertEquals(200, updatedTariff.getCallMinutesCount());
        assertEquals(5, updatedTariff.getInternetGigabytesCount());
        assertTrue(outputStream.toString().contains("Тариф успішно оновлено."));
    }

    @Test
    void testEditNonExistingTariff() {
        String userInput = "NonExistentTariff\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        try {
            editTariff.execute();
        } finally {
            System.setIn(System.in);
        }

        assertEquals(0, network.getTariffs().size());
        assertTrue(outputStream.toString().contains("Тариф не знайдено."));
    }

    @Test
    void testEditTariffWithInvalidInput() {
        TariffPrepaid tariff = new TariffPrepaid("Basic", 100, 200, 5);
        service.addTariff(tariff);

        String userInput = "Basic\n\ninvalid\n0\ninvalid\n0\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        editTariff.execute();

        assertEquals(1, network.getTariffs().size());
        TariffPrepaid unchangedTariff = network.getTariffs().get(0);
        assertEquals("Basic", unchangedTariff.getName());
        assertEquals(100, unchangedTariff.getMonthlyFee());
        assertEquals(200, unchangedTariff.getCallMinutesCount());
        assertEquals(5, unchangedTariff.getInternetGigabytesCount());
        assertTrue(outputStream.toString().contains("Тариф успішно оновлено."));
    }

    @Test
    void testGetName() {
        MobileNetwork network = new MobileNetwork();
        MobileNetworkService service = new MobileNetworkService(network);
        EditTariff editTariff = new EditTariff(service);
        
        assertEquals("Редагувати тариф", editTariff.getName());
    }
}