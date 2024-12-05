package com.pplab4_8.mobileCommunication.users;

import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class MobileNetworkTest {
    private MobileNetwork mobileNetwork;
    private TariffPrepaid tariff1;
    private TariffPrepaid tariff2;

    @BeforeEach
    void setUp() {
        tariff1 = new TariffPrepaid("Tariff1", 50.0f, 100, 10.0f);
        tariff2 = new TariffPrepaid("Tariff2", 70.0f, 200, 20.0f);
        
        ArrayList<TariffPrepaid> tariffs = new ArrayList<>();
        tariffs.add(tariff1);
        tariffs.add(tariff2);
        mobileNetwork = new MobileNetwork("Network1", tariffs);
    }

    @Test
    void testGetName() {
        assertEquals("Network1", mobileNetwork.getName());
    }

    @Test
    void testSetName() {
        mobileNetwork.setName("New Network");
        assertEquals("New Network", mobileNetwork.getName());
    }

    @Test
    void testGetTariffs() {
        assertEquals(2, mobileNetwork.getTariffs().size());
    }

    @Test
    void testSetTariffs() {
        ArrayList<TariffPrepaid> newTariffs = new ArrayList<>();
        TariffPrepaid tariff3 = new TariffPrepaid("Tariff3", 60.0f, 150, 15.0f);
        newTariffs.add(tariff3);
        mobileNetwork.setTariffs(newTariffs);
        assertEquals(1, mobileNetwork.getTariffs().size());
        assertEquals("Tariff3", mobileNetwork.getTariffs().get(0).getName());
    }

    @Test
    void testGetTariffByName() {
        assertEquals(tariff1, mobileNetwork.getTariffByName("Tariff1"));
        assertNull(mobileNetwork.getTariffByName("NonExistentTariff"));
    }

    @Test
    void testAddTariff() {
        TariffPrepaid newTariff = new TariffPrepaid("Tariff3", 60.0f, 150, 15.0f);
        mobileNetwork.addTariff(newTariff);
        assertEquals(3, mobileNetwork.getTariffs().size());
        assertTrue(mobileNetwork.getTariffs().contains(newTariff));
    }

    @Test
    void testRemoveTariff() {
        mobileNetwork.removeTariff(tariff1);
        assertEquals(1, mobileNetwork.getTariffs().size());
        assertFalse(mobileNetwork.getTariffs().contains(tariff1));
    }
}