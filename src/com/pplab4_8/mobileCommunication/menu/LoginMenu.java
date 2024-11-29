package com.pplab4_8.mobileCommunication.menu;

import com.pplab4_8.mobileCommunication.commands.Command;
import com.pplab4_8.mobileCommunication.commands.*;
import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.users.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Клас для реалізації меню входу, яке дозволяє користувачам увійти як мобільний оператор або абонент.
 * Реалізує інтерфейс Command.
 */
public class LoginMenu implements Command {

    private final Map<Map<String, String>, MobileNetworkService> services; // Сервіси мобільного оператора
    private final Map<Map<String, String>, MobileSubscriber> subscribers; // Абоненти
    private final Map<String, String> serviceAccounts = new HashMap<>(); // Облікові записи операторів
    private final Map<String, String> subscriberAccounts = new HashMap<>(); // Облікові записи абонентів
    private final Scanner scanner; // Скэнер для введення користувача

    /**
     * Конструктор для ініціалізації меню входу з сервісами та абонентами.
     *
     * @param services карти сервісів мобільного оператора
     * @param subscribers карти абонентів
     */
    public LoginMenu(Map<Map<String, String>, MobileNetworkService> services, Map<Map<String, String>, MobileSubscriber> subscribers) {
        this.services = services;
        this.subscribers = subscribers;
        this.scanner = new Scanner(System.in);

        // Ініціалізуємо облікові записи для операторів
        for (Map<String, String> key : services.keySet()) {
            this.serviceAccounts.putAll(key);
        }

        // Ініціалізуємо облікові записи для абонентів
        for (Map<String, String> key : subscribers.keySet()) {
            this.subscriberAccounts.putAll(key);
        }
    }

    /**
     * Метод виконання команди входу до меню.
     * Користувач вибирає роль (мобільний оператор або абонент) і входить в систему.
     */
    @Override
    public void execute() {
        while (true) {
            int role = chooseRole(); // Вибір ролі користувача
            if (role == 1) {
                // Вхід як мобільний оператор
                MobileNetworkService service = loginService(serviceAccounts);
                if (service != null) {
                    handleService(service); // Обробка дій для оператора
                    break;
                }
            } else if (role == 2) {
                // Вхід як абонент
                MobileSubscriber subscriber = loginSubscriber(subscriberAccounts);
                if (subscriber != null) {
                    handleSubscriber(subscriber); // Обробка дій для абонента
                    break;
                }
            }
        }
    }

    /**
     * Метод вибору ролі (оператор або абонент).
     *
     * @return роль користувача: 1 для оператора, 2 для абонента
     */
    private int chooseRole() {
        while (true) {
            System.out.println("\nВхід:");
            System.out.println("1. Увійти як мобільний оператор");
            System.out.println("2. Увійти як абонент");
            System.out.print("Виберіть роль: ");
            if (scanner.hasNextInt()) {
                int role = scanner.nextInt();
                if (role == 1 || role == 2) {
                    scanner.nextLine(); // Споживаємо символ нового рядка
                    return role;
                }
            }
            scanner.nextLine(); // Споживаємо символ нового рядка при некоректному введенні
            System.out.println("Невірний вибір. Спробуйте ще раз.");
        }
    }

    /**
     * Метод для входу як мобільний оператор.
     *
     * @param accounts карти з обліковими записами операторів
     * @return сервіс оператора, якщо вхід успішний, або null
     */
    private MobileNetworkService loginService(Map<String, String> accounts) {
        System.out.print("\nВведіть ім'я мобільного оператора: ");
        String username = scanner.nextLine().toLowerCase(); // Введення ім'я оператора
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine(); // Введення пароля

        // Перевірка відповідності ім'я і пароль
        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            System.out.println("Вхід успішний.");
            return services.get(Map.of(username, password)); // Повертає відповідний сервіс
        }
        System.out.println("Невірне ім'я або пароль. Спробуйте ще раз.");
        return null; // Невірний вхід
    }

    /**
     * Метод для входу як абонент.
     *
     * @param accounts карти з обліковими записами абонентів
     * @return абонент, якщо вхід успішний, або null
     */
    private MobileSubscriber loginSubscriber(Map<String, String> accounts) {
        System.out.print("\nВведіть ім'я абонента: ");
        String username = scanner.nextLine().toLowerCase(); // Введення ім'я абонента
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine(); // Введення пароля

        // Перевірка відповідності ім'я і пароль
        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            System.out.println("Вхід успішний.");
            return subscribers.get(Map.of(username, password)); // Повертає відповідного абонента
        }
        System.out.println("Невірне ім'я або пароль. Спробуйте ще раз.");
        return null; // Невірний вхід
    }

    /**
     * Метод для обробки дій мобільного оператора після успішного входу.
     *
     * @param service сервіс мобільного оператора
     */
    private void handleService(MobileNetworkService service) {
        System.out.printf("\nВи увійшли як мобільний оператор %s. Доступні дії:", service.getNetwork().getName());

        // Команди для мобільного оператора
        Map<Integer, Command> commands = Map.of(
                1, new AddTariff(service),
                2, new EditTariff(service),
                3, new RemoveTariff(service),
                4, new ViewTariffs(service)
        );

        // Викликаємо меню для мобільного оператора
        MainMenu menu = new MainMenu(commands, 1);
        menu.execute();
    }

    /**
     * Метод для обробки дій абонента після успішного входу.
     *
     * @param subscriber абонент, який увійшов в систему
     */
    private void handleSubscriber(MobileSubscriber subscriber) {
        System.out.printf("\nВи увійшли як абонент %s. ", subscriber.getName());

        while (true) {
            System.out.println("Доступні оператори:");
            // Виводимо список операторів
            for (MobileNetworkService service : services.values()) {
                System.out.println(service.getNetwork().getName());
            }
            System.out.print("Введіть назву оператора (0 для виходу): ");
            String operatorName = scanner.nextLine().toLowerCase();

            if(operatorName.equals("0")) {
                break; // Вихід з меню
            }

            // Знаходимо вибраного оператора
            for (MobileNetworkService service : services.values()) {
                if (service.getNetwork().getName().toLowerCase().equals(operatorName)) {
                    System.out.printf("\nВи обрали мобільного оператора %s. Доступні дії:", service.getNetwork().getName());

                    subscriber.setNetworkService(service); // Підключаємо абонента до вибраного оператора
                    Map<Integer, Command> commands = Map.of(
                            1, new SearchTariffsByRange(subscriber, subscriber.getNetworkService()),
                            2, new ViewTariffs(subscriber.getNetworkService()),
                            3, new ViewPersonalTariff(subscriber)
                    );

                    // Викликаємо меню для абонента
                    MainMenu menu = new MainMenu(commands, 2);
                    menu.execute();
                    break;
                }
            }
        }
    }
}