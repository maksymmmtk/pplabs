package com.pplab4_8.moblileCommunication;

import com.pplab4_8.moblileCommunication.command.Command;
import com.pplab4_8.moblileCommunication.menu.LoginMenu;
import com.pplab4_8.moblileCommunication.tariffs.*;
import com.pplab4_8.moblileCommunication.users.*;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Створення об'єкта мобільного оператора Kyivstar та додавання тарифів
        MobileNetworkService service1 = new MobileNetworkService("Kyivstar",
                List.of(
                        new TariffPrepaid("Безлімітний", 1000, 500, 150.0f), // Тариф для передплачених послуг
                        new TariffContract("Преміум Контракт", 2000, 1000, 300.0f, "Безкоштовний роумінг") // Контрактний тариф з додатковими перевагами
                )
        );

        // Створення об'єкта мобільного оператора Vodafone та додавання тарифів
        MobileNetworkService service2 = new MobileNetworkService("Vodafone",
                List.of(
                        new TariffPrepaid("Максимальний", 1200, 600, 200.0f), // Тариф для передплачених послуг
                        new TariffContract("Контракт Бізнес", 2500, 1200, 350.0f, "Пріоритетна підтримка") // Контрактний бізнес тариф з додатковими перевагами
                )
        );

        // Створення об'єкта мобільного оператора Lifecell та додавання тарифів
        MobileNetworkService service3 = new MobileNetworkService("Lifecell",
                List.of(
                        new TariffPrepaid("Зручний", 700, 400, 130.0f), // Тариф для передплачених послуг
                        new TariffContract("Сімейний Контракт", 1500, 800, 250.0f, "Додатковий номер безкоштовно") // Контрактний сімейний тариф з додатковими перевагами
                )
        );

        // Створення абонента Maksym і підключення його до тарифу Kyivstar "Безлімітний"
        MobileSubscriber subscriber1 = new MobileSubscriber("Maksym", 30, "0951234567", service1, service1.getTariff("Безлімітний"));

        // Створення абонента Olga і підключення її до тарифу Vodafone "Контракт Бізнес"
        MobileSubscriber subscriber2 = new MobileSubscriber("Olga", 25, "0937654321", service2, service2.getTariff("Контракт Бізнес"));

        // Створення мапи для мобільних операторів з їх обліковими даними (логін та пароль)
        Map<Map<String, String>, MobileNetworkService> services = Map.of(
                Map.of("kyivstar", "1234"), service1,
                Map.of("vodafone", "5678"), service2,
                Map.of("lifecell", "91011"), service3
        );

        // Створення мапи для абонентів з їх обліковими даними (логін та пароль)
        Map<Map<String, String>, MobileSubscriber> subscribers = Map.of(
                Map.of("maksym", "password1"), subscriber1,
                Map.of("olga", "password2"), subscriber2
        );

        // Створення меню входу
        Command loginMenu = new LoginMenu(services, subscribers);

        // Виконання меню входу
        loginMenu.execute();
    }
}