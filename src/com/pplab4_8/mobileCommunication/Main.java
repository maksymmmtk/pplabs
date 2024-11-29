package com.pplab4_8.mobileCommunication;

import com.pplab4_8.mobileCommunication.commands.Command;
import com.pplab4_8.mobileCommunication.menu.LoginMenu;
import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Створення мобільних мереж з тарифами для кожної мережі
        MobileNetwork kyivstar = new MobileNetwork("Kyivstar",
                new ArrayList<>(List.of(
                        // Додавання тарифів до мережі Kyivstar
                        new TariffPrepaid("Безлімітний", 120, 500, 150.0f),
                        new TariffContract("Преміум Контракт", 150, 1000, 300.0f, "Безкоштовний роумінг")
                ))
        );

        MobileNetwork vodafone = new MobileNetwork("Vodafone",
                new ArrayList<>(List.of(
                        // Додавання тарифів до мережі Vodafone
                        new TariffPrepaid("Максимальний", 110, 600, 200.0f),
                        new TariffContract("Контракт Бізнес", 130, 1200, 350.0f, "Пріоритетна підтримка")
                ))
        );

        MobileNetwork lifecell = new MobileNetwork("Lifecell",
                new ArrayList<>(List.of(
                        // Додавання тарифів до мережі Lifecell
                        new TariffPrepaid("Зручний", 125, 400, 130.0f),
                        new TariffContract("Сімейний Контракт", 160, 800, 250.0f, "Додатковий номер безкоштовно")
                ))
        );

        // Створення обслуговуючих сервісів для кожної мобільної мережі
        MobileNetworkService kyivstarService = new MobileNetworkService(kyivstar);
        MobileNetworkService vodafoneService = new MobileNetworkService(vodafone);
        MobileNetworkService lifecellService = new MobileNetworkService(lifecell);

        // Створення абонентів для кожної мережі з відповідними тарифами
        MobileSubscriber subscriber1 = new MobileSubscriber("Maksym", 30, "0951234567", kyivstar, kyivstar.getTariffByName("Безлімітний"));
        MobileSubscriber subscriber2 = new MobileSubscriber("Olga", 25, "0937654321", vodafone, vodafone.getTariffByName("Контракт Бізнес"));

        // Мапа для зберігання сервісів мобільних мереж з їхніми обліковими даними
        Map<Map<String, String>, MobileNetworkService> services = Map.of(
                Map.of("kyivstar", "1234"), kyivstarService, // kyivstar обслуговується через пароль 1234
                Map.of("vodafone", "5678"), vodafoneService, // vodafone обслуговується через пароль 5678
                Map.of("lifecell", "91011"), lifecellService // lifecell обслуговується через пароль 91011
        );

        // Мапа для зберігання абонентів і їхніх облікових даних
        Map<Map<String, String>, MobileSubscriber> subscribers = Map.of(
                Map.of("maksym", "password1"), subscriber1, // Абонент Maksym з паролем password1
                Map.of("olga", "password2"), subscriber2 // Абонент Olga з паролем password2
        );

        // Створення об'єкта меню входу (LoginMenu), що містить сервіси та абонентів
        Command loginMenu = new LoginMenu(services, subscribers);

        // Виконання меню входу
        loginMenu.execute();
    }
}