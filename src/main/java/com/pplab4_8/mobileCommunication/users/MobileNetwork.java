package com.pplab4_8.mobileCommunication.users;

import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MobileNetwork {

    private static final Logger logger = LogManager.getLogger(MobileNetwork.class);

    private String name;
    private ArrayList<TariffPrepaid> tariffs;

    public MobileNetwork() {
        this.name = "";
        this.tariffs = new ArrayList<>();
        logger.info("Створено нову мобільну мережу з іменем за замовчуванням.");
    }

    public MobileNetwork(String name, ArrayList<TariffPrepaid> tariffs) {
        this.name = name;
        this.tariffs = tariffs != null ? tariffs : new ArrayList<>();
        logger.info("Створено мобільну мережу '{}', кількість тарифів: {}", name, this.tariffs.size());
    }

    public String getName() {
        logger.debug("Отримано назву мережі: '{}'.", name);
        return name;
    }

    public void setName(String name) {
        logger.info("Змінено назву мережі з '{}' на '{}'.", this.name, name);
        this.name = name;
    }

    public List<TariffPrepaid> getTariffs() {
        logger.debug("Отримано список тарифів мережі '{}', кількість тарифів: {}.", name, tariffs.size());
        return tariffs;
    }

    public void setTariffs(ArrayList<TariffPrepaid> tariffs) {
        logger.info("Оновлено список тарифів для мережі '{}'. Кількість нових тарифів: {}.", name, tariffs.size());
        this.tariffs = tariffs;
    }

    public TariffPrepaid getTariffByName(String name) {
        logger.debug("Пошук тарифу з ім'ям '{}' у мережі '{}'.", name, this.name);
        for (TariffPrepaid tariff : tariffs) {
            if (tariff.getName().equals(name)) {
                logger.info("Знайдено тариф '{}' у мережі '{}'.", name, this.name);
                return tariff;
            }
        }
        logger.warn("Тариф '{}' не знайдено у мережі '{}'.", name, this.name);
        return null;
    }

    public void addTariff(TariffPrepaid tariff) {
        tariffs.add(tariff);
        logger.info("Додано новий тариф '{}' до мережі '{}'. Загальна кількість тарифів: {}.", tariff.getName(), name, tariffs.size());
    }

    public void removeTariff(TariffPrepaid tariff) {
        if (tariffs.remove(tariff)) {
            logger.info("Видалено тариф '{}' з мережі '{}'. Загальна кількість тарифів: {}.", tariff.getName(), name, tariffs.size());
        } else {
            logger.warn("Спроба видалення тарифу '{}' з мережі '{}', але тариф не знайдено.", tariff.getName(), name);
        }
    }
}