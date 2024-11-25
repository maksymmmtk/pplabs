package com.pplab4_8.moblileCommunication.menu;

import com.pplab4_8.moblileCommunication.command.Command;
import com.pplab4_8.moblileCommunication.command.*;
import com.pplab4_8.moblileCommunication.users.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Клас LoginMenu реалізує інтерфейс Command і відповідає за логіку входу
 * користувача в систему, надаючи вибір між ролями мобільного оператора та абонента.
 */
public class LoginMenu implements Command {

    // Словники для зберігання даних облікових записів операторів і абонентів
    private final Map<Map<String, String>, MobileNetworkService> services;
    private final Map<Map<String, String>, MobileSubscriber> subscribers;

    // Мапи для облікових записів
    private final Map<String, String> serviceAccounts = new HashMap<>();
    private final Map<String, String> subscriberAccounts = new HashMap<>();

    // Сканер для введення даних користувача
    private final Scanner scanner;

    /**
     * Конструктор класу, ініціалізує карти для операторів, абонентів та їх облікових записів.
     * @param services карти для мобільних операторів
     * @param subscribers карти для абонентів
     */
    public LoginMenu(Map<Map<String, String>, MobileNetworkService> services, Map<Map<String, String>, MobileSubscriber> subscribers) {
        this.services = services;
        this.subscribers = subscribers;
        this.scanner = new Scanner(System.in);

        // Ініціалізація облікових записів операторів і абонентів
        for (Map<String, String> key : services.keySet()) {
            this.serviceAccounts.putAll(key);
        }
        for (Map<String, String> key : subscribers.keySet()) {
            this.subscriberAccounts.putAll(key);
        }
    }

    /**
     * Метод виконує логіку входу та вибір ролі (оператор або абонент),
     * а потім виконує відповідну команду для кожної ролі.
     */
    @Override
    public void execute() {
        while (true) {
            // Вибір ролі користувача (мобільний оператор або абонент)
            int role = chooseRole();
            if (role == 1) {
                // Вхід як мобільний оператор
                MobileNetworkService service = loginService(serviceAccounts);
                if (service != null) {
                    handleService(service);  // Обробка дій для мобільного оператора
                    break;
                }
            } else if (role == 2) {
                // Вхід як абонент
                MobileSubscriber subscriber = loginSubscriber(subscriberAccounts);
                if (subscriber != null) {
                    handleSubscriber(subscriber);  // Обробка дій для абонента
                    break;
                }
            }
        }
    }

    /**
     * Метод для вибору ролі користувача (оператор або абонент).
     * @return обрану роль (1 - оператор, 2 - абонент)
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
                    scanner.nextLine();
                    return role;
                }
            }
            scanner.nextLine();
            System.out.println("Невірний вибір. Спробуйте ще раз.");
        }
    }

    /**
     * Метод для входу як мобільний оператор, перевіряє облікові записи.
     * @param accounts карти з обліковими записами операторів
     * @return відповідний об'єкт MobileNetworkService або null, якщо дані неправильні
     */
    private MobileNetworkService loginService(Map<String, String> accounts) {
        System.out.print("\nВведіть ім'я мобільного оператора: ");
        String username = scanner.nextLine().toLowerCase();
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            System.out.println("Вхід успішний.");
            return services.get(Map.of(username, password));  // Повертаємо сервіс для оператора
        }
        System.out.println("Невірне ім'я або пароль. Спробуйте ще раз.");
        return null;
    }

    /**
     * Метод для входу як абонент, перевіряє облікові записи.
     * @param accounts карти з обліковими записами абонентів
     * @return відповідний об'єкт MobileSubscriber або null, якщо дані неправильні
     */
    private MobileSubscriber loginSubscriber(Map<String, String> accounts) {
        System.out.print("\nВведіть ім'я абонента: ");
        String username = scanner.nextLine().toLowerCase();
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            System.out.println("Вхід успішний.");
            return subscribers.get(Map.of(username, password));  // Повертаємо абонента
        }
        System.out.println("Невірне ім'я або пароль. Спробуйте ще раз.");
        return null;
    }

    /**
     * Обробка дій для мобільного оператора після успішного входу.
     * @param service мобільний оператор
     */
    private void handleService(MobileNetworkService service) {
        System.out.printf("\nВи увійшли як мобільний оператор %s. Доступні дії:", service.getName());

        // Створення команд для оператора
        Map<Integer, Command> commands = Map.of(
                1, new AddTariff(service),
                2, new EditTariff(service),
                3, new RemoveTariff(service),
                4, new ViewTariffs(service)
        );

        // Відкривається основне меню для оператора
        MainMenu menu = new MainMenu(commands, 1);
        menu.execute();
    }

    /**
     * Обробка дій для абонента після успішного входу.
     * @param subscriber мобільний абонент
     */
    private void handleSubscriber(MobileSubscriber subscriber) {
        System.out.printf("\nВи увійшли як абонент %s. ", subscriber.getName());
        while (true) {
            System.out.println("Доступні оператори:");
            for (MobileNetworkService service : services.values()) {
                System.out.println(service.getName());
            }
            System.out.print("Введіть назву оператора (0 для виходу): ");
            String operatorName = scanner.nextLine().toLowerCase();

            if (operatorName.equals("0")) {
                break;  // Вихід з меню
            }

            // Пошук і вибір оператора
            for (MobileNetworkService service : services.values()) {
                if (service.getName().toLowerCase().equals(operatorName.toLowerCase())) {
                    System.out.printf("\nВи обрали мобільного оператора %s. Доступні дії:", service.getName());

                    subscriber.setService(service);  // Встановлення вибраного оператора
                    Map<Integer, Command> commands = Map.of(
                            1, new SearchTariff(subscriber.getService()),
                            2, new ViewTariffs(subscriber.getService()),
                            3, new ViewPersonalTariff(subscriber)
                    );

                    // Відкривається основне меню для абонента
                    MainMenu menu = new MainMenu(commands, 2);
                    menu.execute();
                    break;
                }
            }
        }
    }
}