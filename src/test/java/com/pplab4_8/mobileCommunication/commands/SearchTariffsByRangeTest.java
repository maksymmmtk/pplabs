package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;
import com.pplab4_8.mobileCommunication.users.MobileSubscriber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTariffsByRangeTest {
    private MobileNetwork network;
    private MobileNetworkService service;
    private MobileSubscriber subscriber;
    private ByteArrayOutputStream outputStream;
    private SearchTariffsByRange searchTariffsByRange;

    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);
        subscriber = new MobileSubscriber("John Doe", 18);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        searchTariffsByRange = new SearchTariffsByRange(subscriber, service);
    }

    @Test
    void testFindTariffWithinRangeAndSubscribe() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 150, 300, 20);
        service.addTariff(tariff);
        
        String userInput = "100\n200\n1\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        searchTariffsByRange.execute();
        
        assertEquals(tariff, subscriber.getTariff());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Знайдено тариф:"));
        assertEquals(true, output.contains("Підключити цей тариф? (1 - так, 2 - ні):"));
    }

    @Test
    void testFindTariffWithinRangeButDoNotSubscribe() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 150, 300, 20);
        service.addTariff(tariff);
        
        String userInput = "100\n200\n2\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        
        searchTariffsByRange.execute();

        assertEquals(null, subscriber.getTariff());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Знайдено тариф:"));
        assertEquals(true, output.contains("Тариф не змінено."));
    }

    @Test
    void testNoTariffFoundWithinRange() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 300, 300, 20);
        service.addTariff(tariff);

        String userInput = "100\n200\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        searchTariffsByRange.execute();
        
        assertEquals(null, subscriber.getTariff());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Тариф у заданому діапазоні не знайдено."));
    }

    @Test
    void testInvalidInputForRange() {
        String userInput = "abc\n200\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        try {
            searchTariffsByRange.execute();
        } catch (Exception e) {
            assertEquals(true, e instanceof java.util.InputMismatchException);
        }
    }

    @Test
    void testGetName() {
        assertEquals("Знайти тариф", searchTariffsByRange.getName());
    }
}