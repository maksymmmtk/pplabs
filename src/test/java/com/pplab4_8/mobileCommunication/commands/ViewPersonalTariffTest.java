package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import com.pplab4_8.mobileCommunication.users.MobileSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewPersonalTariffTest {
    private MobileSubscriber subscriber;
    private ByteArrayOutputStream outputStream;
    private ViewPersonalTariff viewPersonalTariff;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        subscriber = new MobileSubscriber("John Doe", 18);
        viewPersonalTariff = new ViewPersonalTariff(subscriber);
    }

    @Test
    void testViewPersonalTariffWhenTariffExists() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 100, 300, 20);
        subscriber.setSilentMode(true); 
        subscriber.setTariff(tariff);
        
        viewPersonalTariff.execute();
        
        String expectedOutput = "Ваш тариф:\n" + tariff.toString();
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    void testViewPersonalTariffWhenNoTariffExists() {
        viewPersonalTariff.execute();
        
        String expectedOutput = "Тариф не встановлено.";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    void testGetName() {
        assertEquals("Переглянути особистий тариф", viewPersonalTariff.getName());
    }
}