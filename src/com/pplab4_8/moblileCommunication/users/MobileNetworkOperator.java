package com.pplab4_8.moblileCommunication.users;

import com.pplab4_8.moblileCommunication.tariffs.TariffPrepaid;

import java.util.ArrayList;

public class MobileNetworkOperator {
    private String name;
    private ArrayList<TariffPrepaid> tarrifs = new ArrayList<TariffPrepaid>();

    public MobileNetworkOperator(String name) {
        this.name = name;
    }
    public MobileNetworkOperator(String name, ArrayList<TariffPrepaid> list) {
        this.name = name;
        this.tarrifs = list;
    }

    public String getName() {
        return name;
    }
    // getList
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<TariffPrepaid> getList() {
        return tarrifs;
    }
    public void setList(ArrayList<TariffPrepaid> list) {
        this.tarrifs = list;
    }

    public void addTariff() {
//        tarrifs.add(t);
        System.out.println("Adding Tariff");
    }
    public void editTariff() {
    // Реалізація
        System.out.println("Editing Tariff");
    }
    public void removeTariff() {
//        tarrifs.remove(t);
        System.out.println("Removing Tariff");
    }
    public void viewTariffs() {
//        for (TariffPrepaid t : tarrifs) {
//            System.out.println(t);
//        }
        System.out.println("View Tariffs");
    }
    public void calculateTotalClients() {

    }
    public void sortTariffsByFee() {

    }
    public TariffPrepaid searchTariff() {
        return null;
    }
}
