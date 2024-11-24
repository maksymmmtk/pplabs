package com.pplab4_8.moblileCommunication.menu;

import com.pplab4_8.moblileCommunication.command.Command;
import com.pplab4_8.moblileCommunication.command.*;
import com.pplab4_8.moblileCommunication.users.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginMenu implements Command {

    private final Map<Map<String, String>, MobileNetworkService> services;
    private final Map<Map<String, String>, MobileSubscriber> subscribers;
    private final Map<String, String> serviceAccounts = new HashMap<>();
    private final Map<String, String> subscriberAccounts = new HashMap<>();
    private final Scanner scanner;

    public LoginMenu(Map<Map<String, String>, MobileNetworkService> services, Map<Map<String, String>, MobileSubscriber> subscribers) {
        this.services = services;
        this.subscribers = subscribers;
        this.scanner = new Scanner(System.in);

        for (Map<String, String> key : services.keySet()) {
            this.serviceAccounts.putAll(key);
        }
        for (Map<String, String> key : subscribers.keySet()) {
            this.subscriberAccounts.putAll(key);
        }
    }

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

    private MobileNetworkService loginService(Map<String, String> accounts) {
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

    private MobileSubscriber loginSubscriber(Map<String, String> accounts) {
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

    private void handleService(MobileNetworkService service) {
        System.out.printf("\nВи увійшли як мобільний оператор %s. Доступні дії:", service.getName());

        Map<Integer, Command> commands = Map.of(
                1, new AddTariff(service),
                2, new EditTariff(service),
                3, new RemoveTariff(service),
                4, new ViewTariffs(service)
        );

        MainMenu menu = new MainMenu(commands, 1);
        menu.execute();
    }

    private void handleSubscriber(MobileSubscriber subscriber) {
        System.out.printf("\nВи увійшли як абонент %s. ", subscriber.getName());
        while (true) {
            System.out.println("Доступні оператори:");
            for (MobileNetworkService service : services.values()) {
                System.out.println(service.getName());
            }
            System.out.print("Введіть назву оператора (0 для виходу): ");
            String operatorName = scanner.nextLine().toLowerCase();

            if(operatorName.equals("0")) {
                break;
            }

            for (MobileNetworkService service : services.values()) {
                if (service.getName().toLowerCase().equals(operatorName.toLowerCase())) {
                    System.out.printf("\nВи обрали мобільного оператора %s. Доступні дії:", service.getName());

                    subscriber.setService(service);
                    Map<Integer, Command> commands = Map.of(
                            1, new SearchTariff(subscriber.getService()),
                            2, new ViewTariffs(subscriber.getService()),
                            3, new ViewPersonalTariff(subscriber)
                    );

                    MainMenu menu = new MainMenu(commands, 2);
                    menu.execute();
                    break;
                }
            }
        }
    }
}