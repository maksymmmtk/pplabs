package com.pplab4_8.mobileCommunication;

import com.pplab4_8.mobileCommunication.commands.Command;
import com.pplab4_8.mobileCommunication.menu.LoginMenu;
import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.*;

/**
 * Головний клас програми мобільної комунікації.
 * Цей клас відповідає за ініціалізацію програми, створення мобільних мереж, тарифів,
 * абонентів та обробку виконання меню входу.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * Головна точка входу в програму.
     *
     * @param args Аргументи командного рядка
     */
    public static void main(String[] args) {
        // Налаштування властивостей для роботи з електронною поштою
        System.setProperty("mail.smtp.starttls.enable", "true");
        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        logger.info("Програма стартує...");

        // Виконання основної логіки програми
        executeApp(System.in);
        logger.info("Програма завершила виконання.");
    }

    /**
     * Виконує основну логіку програми.
     * Ініціалізує мобільні мережі, тарифи, абонентів та запускає меню входу.
     *
     * @param inputStream Вхідний потік для взаємодії з користувачем
     */
    public static void executeApp(InputStream inputStream) {
        logger.info("Ініціалізація мобільних мереж, тарифів та абонентів...");

        // Ініціалізація мобільних мереж з тарифами
        MobileNetwork kyivstar = new MobileNetwork("Kyivstar",
                new ArrayList<>(List.of(
                        new TariffPrepaid("Безлімітний", 120, 500, 150.0f),
                        new TariffContract("Преміум Контракт", 150, 1000, 300.0f, "Безкоштовний роумінг")
                ))
        );
        logger.info("Мобільна мережа Kyivstar успішно ініціалізована.");

        MobileNetwork vodafone = new MobileNetwork("Vodafone",
                new ArrayList<>(List.of(
                        new TariffPrepaid("Максимальний", 110, 600, 200.0f),
                        new TariffContract("Контракт Бізнес", 130, 1200, 350.0f, "Пріоритетна підтримка")
                ))
        );
        logger.info("Мобільна мережа Vodafone успішно ініціалізована.");

        MobileNetwork lifecell = new MobileNetwork("Lifecell",
                new ArrayList<>(List.of(
                        new TariffPrepaid("Зручний", 125, 400, 130.0f),
                        new TariffContract("Сімейний Контракт", 160, 800, 250.0f, "Додатковий номер безкоштовно")
                ))
        );
        logger.info("Мобільна мережа Lifecell успішно ініціалізована.");

        // Створення сервісів мобільних мереж
        MobileNetworkService kyivstarService = new MobileNetworkService(kyivstar);
        MobileNetworkService vodafoneService = new MobileNetworkService(vodafone);
        MobileNetworkService lifecellService = new MobileNetworkService(lifecell);

        logger.info("Сервіси мобільних мереж успішно створено.");

        // Ініціалізація абонентів
        MobileSubscriber subscriber1 = new MobileSubscriber("Maksym", 30, "0951234567", kyivstarService, kyivstar.getTariffByName("Безлімітний"));
        MobileSubscriber subscriber2 = new MobileSubscriber("Olga", 25, "0937654321", vodafoneService, vodafone.getTariffByName("Контракт Бізнес"));

        logger.info("Абоненти успішно ініціалізовані.");

        // Створення карт для сервісів та абонентів
        Map<Map<String, String>, MobileNetworkService> services = Map.of(
                Map.of("kyivstar", "1234"), kyivstarService,
                Map.of("vodafone", "5678"), vodafoneService,
                Map.of("lifecell", "91011"), lifecellService
        );

        Map<Map<String, String>, MobileSubscriber> subscribers = Map.of(
                Map.of("maksym", "password1"), subscriber1,
                Map.of("olga", "password2"), subscriber2
        );

        logger.info("Меню входу створюється...");

        // Запуск меню входу
        Command loginMenu = new LoginMenu(services, subscribers, inputStream);
        loginMenu.execute();
        logger.info("Меню входу успішно виконано.");
    }
}