package com.pplab4_8.mobileCommunication;

import com.pplab4_8.mobileCommunication.commands.Command;
import com.pplab4_8.mobileCommunication.menu.LoginMenu;
import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.*;

import java.io.InputStream;
import java.util.*;

/**
 * Головний клас програми для управління мобільними мережами та абонентами.
 * Містить методи для ініціалізації мобільних мереж, тарифів та абонентів,
 * а також для виконання додатку.
 */
public class Main {

    /**
     * Точка входу в програму. Викликає метод {@link #executeApp(InputStream)} для запуску додатку.
     *
     * @param args вхідні аргументи командного рядка (не використовуються в даному випадку)
     */
    public static void main(String[] args) {
        // Викликаємо метод для виконання додатку
        executeApp(System.in);
    }

    /**
     * Ініціалізує мобільні мережі, тарифи та абонентів, а також запускає меню входу.
     *
     * @param inputStream вхідний потік даних для сканера (зазвичай System.in)
     */
    public static void executeApp(InputStream inputStream) {
        // Ініціалізація мобільних мереж з тарифами
        MobileNetwork kyivstar = new MobileNetwork("Kyivstar",
                new ArrayList<>(List.of(
                        new TariffPrepaid("Безлімітний", 120, 500, 150.0f),
                        new TariffContract("Преміум Контракт", 150, 1000, 300.0f, "Безкоштовний роумінг")
                ))
        );

        MobileNetwork vodafone = new MobileNetwork("Vodafone",
                new ArrayList<>(List.of(
                        new TariffPrepaid("Максимальний", 110, 600, 200.0f),
                        new TariffContract("Контракт Бізнес", 130, 1200, 350.0f, "Пріоритетна підтримка")
                ))
        );

        MobileNetwork lifecell = new MobileNetwork("Lifecell",
                new ArrayList<>(List.of(
                        new TariffPrepaid("Зручний", 125, 400, 130.0f),
                        new TariffContract("Сімейний Контракт", 160, 800, 250.0f, "Додатковий номер безкоштовно")
                ))
        );

        // Ініціалізація сервісів для кожної мобільної мережі
        MobileNetworkService kyivstarService = new MobileNetworkService(kyivstar);
        MobileNetworkService vodafoneService = new MobileNetworkService(vodafone);
        MobileNetworkService lifecellService = new MobileNetworkService(lifecell);

        // Ініціалізація абонентів з прив'язкою до мереж та тарифів
        MobileSubscriber subscriber1 = new MobileSubscriber("Maksym", 30, "0951234567", kyivstarService, kyivstar.getTariffByName("Безлімітний"));
        MobileSubscriber subscriber2 = new MobileSubscriber("Olga", 25, "0937654321", vodafoneService, vodafone.getTariffByName("Контракт Бізнес"));

        // Карта для зберігання мобільних мереж з їхніми обліковими даними
        Map<Map<String, String>, MobileNetworkService> services = Map.of(
                Map.of("kyivstar", "1234"), kyivstarService,
                Map.of("vodafone", "5678"), vodafoneService,
                Map.of("lifecell", "91011"), lifecellService
        );

        // Карта для зберігання абонентів з їхніми обліковими даними
        Map<Map<String, String>, MobileSubscriber> subscribers = Map.of(
                Map.of("maksym", "password1"), subscriber1,
                Map.of("olga", "password2"), subscriber2
        );

        // Створення і виконання меню входу
        Command loginMenu = new LoginMenu(services, subscribers, inputStream);
        loginMenu.execute();
    }
}