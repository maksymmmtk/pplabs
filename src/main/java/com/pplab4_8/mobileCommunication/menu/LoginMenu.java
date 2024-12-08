package com.pplab4_8.mobileCommunication.menu;

import com.pplab4_8.mobileCommunication.commands.Command;
import com.pplab4_8.mobileCommunication.commands.*;
import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.users.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginMenu implements Command {

    private static final Logger logger = LogManager.getLogger(LoginMenu.class);

    private final Map<Map<String, String>, MobileNetworkService> services;
    private final Map<Map<String, String>, MobileSubscriber> subscribers;
    private final Map<String, String> serviceAccounts = new HashMap<>();
    private final Map<String, String> subscriberAccounts = new HashMap<>();
    private final Scanner scanner;

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

        logger.info("LoginMenu ініціалізовано з {} операторами та {} абонентами.", services.size(), subscribers.size());
    }

    @Override
    public void execute() {
        logger.info("Виконання LoginMenu розпочато.");
        while (true) {
            int role = chooseRole();
            if (role == 1) {
                logger.info("Користувач обрав роль: мобільний оператор.");
                MobileNetworkService service = loginService(serviceAccounts);
                if (service != null) {
                    logger.info("Вхід мобільного оператора '{}' успішний.", service.getNetwork().getName());
                    handleService(service);
                    break;
                } else {
                    logger.warn("Невдала спроба входу як мобільний оператор.");
                }
            } else if (role == 2) {
                logger.info("Користувач обрав роль: абонент.");
                MobileSubscriber subscriber = loginSubscriber(subscriberAccounts);
                if (subscriber != null) {
                    logger.info("Вхід абонента '{}' успішний.", subscriber.getName());
                    handleSubscriber(subscriber);
                    break;
                } else {
                    logger.warn("Невдала спроба входу як абонент.");
                }
            }
        }
        logger.info("Виконання LoginMenu завершено.");
    }

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

    public MobileNetworkService loginService(Map<String, String> accounts) {
        System.out.print("\nВведіть ім'я мобільного оператора: ");
        String username = scanner.nextLine().toLowerCase();
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            logger.info("Успішний вхід мобільного оператора: '{}'.", username);
            return services.get(Map.of(username, password));
        }
        logger.warn("Невдалий вхід мобільного оператора: '{}'. Невірні облікові дані.", username);
        System.out.println("Невірне ім'я або пароль. Спробуйте ще раз.");
        return null;
    }

    public MobileSubscriber loginSubscriber(Map<String, String> accounts) {
        System.out.print("\nВведіть ім'я абонента: ");
        String username = scanner.nextLine().toLowerCase();
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            logger.info("Успішний вхід абонента: '{}'.", username);
            return subscribers.get(Map.of(username, password));
        }
        logger.warn("Невдалий вхід абонента: '{}'. Невірні облікові дані.", username);
        System.out.println("Невірне ім'я або пароль. Спробуйте ще раз.");
        return null;
    }

    public void handleService(MobileNetworkService service) {
        logger.info("Обробка мобільного оператора: '{}'.", service.getNetwork().getName());
        System.out.printf("\nВи увійшли як мобільний оператор %s. Доступні дії:", service.getNetwork().getName());

        Map<Integer, Command> commands = Map.of(
                1, new AddTariff(service),
                2, new EditTariff(service),
                3, new RemoveTariff(service),
                4, new ViewTariffs(service)
        );

        MainMenu menu = new MainMenu(commands, 1, scanner);
        menu.execute();
        logger.info("Робота з мобільним оператором завершена: '{}'.", service.getNetwork().getName());
    }

    public void handleSubscriber(MobileSubscriber subscriber) {
        logger.info("Обробка абонента: '{}'.", subscriber.getName());
        System.out.printf("\nВи увійшли як абонент %s. ", subscriber.getName());
        while (true) {
            System.out.println("Доступні оператори:");
            for (MobileNetworkService service : services.values()) {
                System.out.println(service.getNetwork().getName());
            }
            System.out.print("Введіть назву оператора (0 для виходу): ");
            String operatorName = scanner.nextLine().toLowerCase();

            if (operatorName.equals("0")) {
                logger.info("Абонент '{}' завершив роботу.", subscriber.getName());
                break;
            }

            for (MobileNetworkService service : services.values()) {
                if (service.getNetwork().getName().toLowerCase().equals(operatorName.toLowerCase())) {
                    logger.info("Абонент '{}' обрав мобільного оператора '{}'.", subscriber.getName(), service.getNetwork().getName());
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