package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveTariffTest {
    private MobileNetwork network;
    private MobileNetworkService service;
    private ByteArrayOutputStream outputStream;
    private RemoveTariff removeTariff;

    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        removeTariff = new RemoveTariff(service);
    }

    @Test
    void testRemoveExistingTariff() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 100, 300, 10);
        service.addTariff(tariff);
        
        String userInput = "Standard\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        
        removeTariff.execute();
        
        assertEquals(0, network.getTariffs().size());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Тариф успішно видалено."));
    }

    @Test
    void testRemoveNonExistingTariff() {
        String userInput = "NonExistingTariff\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        
        removeTariff.execute();
        
        assertEquals(0, network.getTariffs().size());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Тариф не знайдено."));
    }

    @Test
    void testRemoveTariffWithEmptyInput() {
        String userInput = "\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        
        removeTariff.execute();
        
        assertEquals(0, network.getTariffs().size());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Тариф не знайдено."));
    }

    @Test
    void testGetName() {
        assertEquals("Видалити тариф", removeTariff.getName());
    }
}