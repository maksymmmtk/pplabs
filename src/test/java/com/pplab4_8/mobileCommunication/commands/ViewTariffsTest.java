package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewTariffsTest {
    private MobileNetwork network;
    private MobileNetworkService service;
    private ViewTariffs viewTariffs;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);
        viewTariffs = new ViewTariffs(service);
        
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testViewTariffsWithEmptyList() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));

        viewTariffs.execute();
        
        String expectedOutput = "Список тарифів порожній.";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    @Test
    void testViewTariffsWithItems() {
        network.addTariff(new TariffPrepaid("Basic", 10.0f, 100, 5.0f));
        network.addTariff(new TariffPrepaid("Premium", 20.0f, 200, 10.0f));
        
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));

        viewTariffs.execute();
        
        String output = outputStream.toString();
        assertEquals(true, output.contains("Список тарифів:"));
        assertEquals(true, output.contains("Basic"));
        assertEquals(true, output.contains("Premium"));
    }

    @Test
    void testCalculateTotalClients() {
        TariffPrepaid basic = new TariffPrepaid("Basic", 10.0f, 100, 5.0f);
        basic.addSubscriber();
        basic.addSubscriber();
        network.addTariff(basic);
        
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        viewTariffs.execute();
        
        String expectedOutput = "Клієнтів: 2";
        assertEquals(true, outputStream.toString().contains(expectedOutput));
    }

    @Test
    void testSortTariffsByFee() {
        network.addTariff(new TariffPrepaid("Basic", 10.0f, 100, 5.0f));
        network.addTariff(new TariffPrepaid("Premium", 20.0f, 200, 10.0f));
        
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        viewTariffs.execute();
        
        String expectedOutput = "Тарифи відсортовано.";
        assertEquals(true, outputStream.toString().contains(expectedOutput));

        List<TariffPrepaid> sortedTariffs = network.getTariffs();
        assertEquals("Basic", sortedTariffs.get(0).getName());
        assertEquals("Premium", sortedTariffs.get(1).getName());
    }

    @Test
    void testExitOption() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));

        viewTariffs.execute();
        
        String expectedOutput = "Завершено.";
        assertEquals(false, outputStream.toString().contains(expectedOutput));
    }

    @Test
    void testGetName() {
        assertEquals("Переглянути наявні тарифи", viewTariffs.getName());
    }
}