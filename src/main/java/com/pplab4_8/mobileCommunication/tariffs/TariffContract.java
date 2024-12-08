package com.pplab4_8.mobileCommunication.tariffs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TariffContract extends TariffPrepaid {

    private static final Logger logger = LogManager.getLogger(TariffContract.class);

    private String additionalServices;

    public TariffContract(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount, String additionalServices) {
        super(name, monthlyFee, callMinutesCount, internetGigabytesCount);
        this.additionalServices = additionalServices;
        logger.info("Створено новий контрактний тариф: ім'я = '{}', місячна плата = {}, хвилини дзвінків = {}, гігабайти інтернету = {}, додаткові послуги = '{}'",
                name, monthlyFee, callMinutesCount, internetGigabytesCount, additionalServices);
    }

    public String getAdditionalServices() {
        logger.debug("Отримано додаткові послуги тарифу '{}': '{}'", getName(), additionalServices);
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        logger.info("Змінено додаткові послуги тарифу '{}': з '{}' на '{}'", getName(), this.additionalServices, additionalServices);
        this.additionalServices = additionalServices;
    }

    @Override
    public String toString() {
        logger.debug("Виклик toString для контрактного тарифу '{}'", getName());
        return super.toString() + String.format(", Додаткові послуги: %s", additionalServices);
    }
}