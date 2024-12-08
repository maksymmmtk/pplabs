package com.pplab4_8.mobileCommunication.service;

import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

public class MobileNetworkService {

    private static final Logger logger = LogManager.getLogger(MobileNetworkService.class);

    private MobileNetwork network;

    public MobileNetworkService(MobileNetwork network) {
        this.network = network;
        logger.info("Створено сервіс мобільної мережі для мережі '{}'", network.getName());
    }

    public MobileNetwork getNetwork() {
        logger.debug("Отримано мережу '{}'", network.getName());
        return network;
    }

    public void addTariff(TariffPrepaid tariff) {
        network.addTariff(tariff);
        logger.info("Додано тариф '{}' до мережі '{}'", tariff.getName(), network.getName());
    }

    public void editTariff(TariffPrepaid oldTariff, TariffPrepaid updatedTariff) {
        network.removeTariff(oldTariff);
        network.addTariff(updatedTariff);
        logger.info("Оновлено тариф у мережі '{}'. Старий тариф: '{}', Новий тариф: '{}'", network.getName(), oldTariff.getName(), updatedTariff.getName());
    }

    public void removeTariff(TariffPrepaid tariff) {
        network.removeTariff(tariff);
        logger.info("Видалено тариф '{}' з мережі '{}'", tariff.getName(), network.getName());
    }

    public List<TariffPrepaid> viewTariffs() {
        List<TariffPrepaid> tariffs = network.getTariffs();
        logger.debug("Отримано список тарифів для мережі '{}'. Кількість тарифів: {}", network.getName(), tariffs.size());
        return tariffs;
    }

    public TariffPrepaid searchTariffByName(String name) {
        TariffPrepaid tariff = network.getTariffByName(name);
        if (tariff != null) {
            logger.info("Знайдено тариф '{}' у мережі '{}'", name, network.getName());
        } else {
            logger.warn("Тариф '{}' не знайдено у мережі '{}'", name, network.getName());
        }
        return tariff;
    }

    public TariffPrepaid searchTariffsByRange(float minFee, float maxFee) {
        TariffPrepaid tariff = network.getTariffs().stream()
                .filter(t -> t.getMonthlyFee() >= minFee && t.getMonthlyFee() <= maxFee)
                .findFirst()
                .orElse(null);

        if (tariff != null) {
            logger.info("Знайдено тариф у діапазоні від {} до {} у мережі '{}'. Тариф: '{}'", minFee, maxFee, network.getName(), tariff.getName());
        } else {
            logger.warn("Не знайдено тариф у діапазоні від {} до {} у мережі '{}'", minFee, maxFee, network.getName());
        }

        return tariff;
    }

    public int calculateTotalClients() {
        int totalClients = network.getTariffs().stream().mapToInt(TariffPrepaid::getSubscribersCount).sum();
        logger.info("Загальна кількість клієнтів у мережі '{}': {}", network.getName(), totalClients);
        return totalClients;
    }

    public void sortTariffsByFee() {
        network.getTariffs().sort(Comparator.comparingDouble(TariffPrepaid::getMonthlyFee));
        logger.info("Відсортовано тарифи у мережі '{}' за місячною платою у зростаючому порядку", network.getName());
    }
}