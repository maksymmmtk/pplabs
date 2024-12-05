package com.pplab4_8.mobileCommunication.users;

import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import java.util.ArrayList;
import java.util.List;

public class MobileNetwork {
    private String name;
    private ArrayList<TariffPrepaid> tariffs;

    public MobileNetwork() {
        this.name = "";
        this.tariffs = new ArrayList<>();
    }

    public MobileNetwork(String name, ArrayList<TariffPrepaid> tariffs) {
        this.name = name;
        this.tariffs = tariffs != null ? tariffs : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TariffPrepaid> getTariffs() {
        return tariffs;
    }

    public void setTariffs(ArrayList<TariffPrepaid> tariffs) {
        this.tariffs = tariffs;
    }

    public TariffPrepaid getTariffByName(String name) {
        for (TariffPrepaid tariff : tariffs) {
            if (tariff.getName().equals(name)) {
                return tariff;
            }
        }
        return null;
    }

    public void addTariff(TariffPrepaid tariff) {
        tariffs.add(tariff);
    }

    public void removeTariff(TariffPrepaid tariff) {
        tariffs.remove(tariff);
    }
}