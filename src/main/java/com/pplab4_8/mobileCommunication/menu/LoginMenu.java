package com.pplab4_8.mobileCommunication.menu;

import com.pplab4_8.mobileCommunication.commands.Command;
import com.pplab4_8.mobileCommunication.commands.*;
import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.users.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Меню входу в систему для мобільних операторів і абонентів.
 * Забезпечує вибір ролі (оператор або абонент), авторизацію і доступ до відповідних функцій.
 */
public class LoginMenu implements Command {

    private final Map<Map<String, String>, MobileNetworkService> services;
    private final Map<Map<String, String>, MobileSubscriber> subscribers;
    private final Map<String, String> serviceAccounts = new HashMap<>();
    private final Map<String, String> subscriberAccounts = new HashMap<>();
    private final Scanner scanner;

    /**
     * Конструктор класу LoginMenu.
     *
     * @param services    мапа облікових записів операторів і їхніх сервісів.
     * @param subscribers мапа облікових записів абонентів і їхніх профілів.
     * @param inputStream вхідний потік для вводу користувача.
     */
    public LoginMenu(Map<Map<String, String>, MobileNetworkService> services, Map<Map<String, String>, MobileSubscriber> subscribers, InputStream inputStream) {
        this.services = services;
        this.subscribers = subscribers;
        this.scanner = new Scanner(inputStream);

        for (Map<String, String> key : services.keySet()) {
            this.serviceAccounts.putAll(key);
        }
        for (Map<String, String> key : subscribers.keySet()) {
            this.subscriberAccounts.putAll(key);
        }
    }

    /**
     * Виконує вхід у систему, надаючи вибір між оператором і абонентом.
     */
    @Override
    public void execute() {
        while (true) {
            int role = chooseRole();
            if (role == 1) {
                MobileNetworkService service = loginService(serviceAccounts);
                if (service != null) {
                    handleService(service);
                    break;
                }
            } else if (role == 2) {
                MobileSubscriber subscriber = loginSubscriber(subscriberAccounts);
                if (subscriber != null) {
                    handleSubscriber(subscriber);
                    break;
                }
            }
        }
    }

    /**
     * Пропонує користувачу вибір ролі: оператор або абонент.
     *
     * @return вибрана роль (1 для оператора, 2 для абонента).
     */
    public int chooseRole() {
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
     * Авторизація мобільного оператора.
     *
     * @param accounts мапа облікових записів операторів.
     * @return сервіс мобільного оператора або {@code null}, якщо авторизація неуспішна.
     */
    public MobileNetworkService loginService(Map<String, String> accounts) {
        System.out.print("\nВведіть ім'я мобільного оператора: ");
        String username = scanner.nextLine().toLowerCase();
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            System.out.println("Вхід успішний.");
            return services.get(Map.of(username, password));
        }
        System.out.println("Невірне ім'я або пароль. Спробуйте ще раз.");
        return null;
    }

    /**
     * Авторизація абонента.
     *
     * @param accounts мапа облікових записів абонентів.
     * @return профіль абонента або {@code null}, якщо авторизація неуспішна.
     */
    public MobileSubscriber loginSubscriber(Map<String, String> accounts) {
        System.out.print("\nВведіть ім'я абонента: ");
        String username = scanner.nextLine().toLowerCase();
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            System.out.println("Вхід успішний.");
            return subscribers.get(Map.of(username, password));
        }
        System.out.println("Невірне ім'я або пароль. Спробуйте ще раз.");
        return null;
    }

    /**
     * Обробка меню для мобільного оператора після успішного входу.
     *
     * @param service сервіс мобільного оператора.
     */
    public void handleService(MobileNetworkService service) {
        System.out.printf("\nВи увійшли як мобільний оператор %s. Доступні дії:", service.getNetwork().getName());

        Map<Integer, Command> commands = Map.of(
                1, new AddTariff(service),
                2, new EditTariff(service),
                3, new RemoveTariff(service),
                4, new ViewTariffs(service)
        );

        MainMenu menu = new MainMenu(commands, 1, scanner);
        menu.execute();
    }

    /**
     * Обробка меню для абонента після успішного входу.
     *
     * @param subscriber профіль абонента.
     */
    public void handleSubscriber(MobileSubscriber subscriber) {
        System.out.printf("\nВи увійшли як абонент %s. ", subscriber.getName());
        while (true) {
            System.out.println("Доступні оператори:");
            for (MobileNetworkService service : services.values()) {
                System.out.println(service.getNetwork().getName());
            }
            System.out.print("Введіть назву оператора (0 для виходу): ");
            String operatorName = scanner.nextLine().toLowerCase();

            if (operatorName.equals("0")) {
                break;
            }

            for (MobileNetworkService service : services.values()) {
                if (service.getNetwork().getName().toLowerCase().equals(operatorName.toLowerCase())) {
                    System.out.printf("\nВи обрали мобільного оператора %s. Доступні дії:", service.getNetwork().getName());

                    subscriber.setNetworkService(service);
                    Map<Integer, Command> commands = Map.of(
                            1, new SearchTariffsByRange(subscriber, subscriber.getNetworkService()),
                            2, new ViewTariffs(subscriber.getNetworkService()),
                            3, new ViewPersonalTariff(subscriber)
                    );

                    MainMenu menu = new MainMenu(commands, 2, scanner);
                    menu.execute();
                    break;
                }
            }
        }
    }
}