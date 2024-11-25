package com.pplab4_8.moblileCommunication;

import com.pplab4_8.moblileCommunication.command.Command;
import com.pplab4_8.moblileCommunication.menu.LoginMenu;
import com.pplab4_8.moblileCommunication.tariffs.*;
import com.pplab4_8.moblileCommunication.users.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Головний клас програми, що ініціалізує мобільні мережі та абонентів,
 * а також виконує головне меню для авторизації користувачів.
 */
public class Main {
    public static void main(String[] args) {

        // Створення мобільних мереж з тарифами
        MobileNetworkService service1 = new MobileNetworkService("Kyivstar",
                new ArrayList<>(List.of(
                        new TariffPrepaid("Безлімітний", 120, 500, 150.0f),
                        new TariffContract("Преміум Контракт", 150, 1000, 300.0f, "Безкоштовний роумінг")
                ))
        );
        MobileNetworkService service2 = new MobileNetworkService("Vodafone",
                new ArrayList<>(List.of(
                        new TariffPrepaid("Максимальний", 110, 600, 200.0f),
                        new TariffContract("Контракт Бізнес", 130, 1200, 350.0f, "Пріоритетна підтримка")
                ))
        );
        MobileNetworkService service3 = new MobileNetworkService("Lifecell",
                new ArrayList<>(List.of(
                        new TariffPrepaid("Зручний", 125, 400, 130.0f),
                        new TariffContract("Сімейний Контракт", 160, 800, 250.0f, "Додатковий номер безкоштовно")
                ))
        );

        // Створення абонентів і прив'язка до мобільних мереж і тарифів
        MobileSubscriber subscriber1 = new MobileSubscriber("Maksym", 30, "0951234567", service1, service1.getTariff("Безлімітний"));
        MobileSubscriber subscriber2 = new MobileSubscriber("Olga", 25, "0937654321", service2, service2.getTariff("Контракт Бізнес"));

        // Створення мапи для мобільних мереж та їх авторизаційних даних
        Map<Map<String, String>, MobileNetworkService> services = Map.of(
                Map.of("kyivstar", "1234"), service1,
                Map.of("vodafone", "5678"), service2,
                Map.of("lifecell", "91011"), service3
        );

        // Створення мапи для абонентів та їх авторизаційних даних
        Map<Map<String, String>, MobileSubscriber> subscribers = Map.of(
                Map.of("maksym", "password1"), subscriber1,
                Map.of("olga", "password2"), subscriber2
        );

        // Створення об'єкта для меню авторизації
        Command loginMenu = new LoginMenu(services, subscribers);

        // Виконання меню авторизації
        loginMenu.execute();
    }
}