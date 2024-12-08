package com.pplab4_8.mobileCommunication.menu;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.users.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginMenuTest {
    private Map<Map<String, String>, MobileNetworkService> services;
    private Map<Map<String, String>, MobileSubscriber> subscribers;
    private Map<String, String> serviceAccounts;
    private Map<String, String> subscriberAccounts;
    private InputStream inputStream;

    @BeforeEach
    void setUp() {
        services = new HashMap<>();
        subscribers = new HashMap<>();
        serviceAccounts = new HashMap<>();
        subscriberAccounts = new HashMap<>();
        
        MobileNetwork network1 = new MobileNetwork("TestNetwork1", new ArrayList<>());
        MobileNetworkService networkService1 = new MobileNetworkService(network1);

        MobileNetwork network2 = new MobileNetwork("TestNetwork2", new ArrayList<>());
        MobileNetworkService networkService2 = new MobileNetworkService(network2);

        services.put(Map.of("operator1", "password1"), networkService1);
        services.put(Map.of("operator2", "password2"), networkService2);

        for (Map<String, String> key : services.keySet()) {
            serviceAccounts.putAll(key);
        }
        
        MobileSubscriber subscriber1 = new MobileSubscriber("Subscriber1", 18);
        MobileSubscriber subscriber2 = new MobileSubscriber("Subscriber2", 25);

        subscribers.put(Map.of("subscriber1", "password1"), subscriber1);
        subscribers.put(Map.of("subscriber2", "password2"), subscriber2);

        for (Map<String, String> key : subscribers.keySet()) {
            subscriberAccounts.putAll(key);
        }
    }

    @Test
    void testChooseRole_operatorSelected() {
        String userInput = "1\n"; 
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        int role = loginMenu.chooseRole();
        assertEquals(1, role, "Роль має бути '1' для мобільного оператора");
    }

    @Test
    void testChooseRole_subscriberSelected() {
        String userInput = "2\n";
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        int role = loginMenu.chooseRole();
        assertEquals(2, role, "Роль має бути '2' для абонента");
    }

    @Test
    void testLoginService_successfulLogin() {
        String userInput = "operator1\npassword1\n"; 
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        MobileNetworkService service = loginMenu.loginService(serviceAccounts);
        assertNotNull(service, "Вхід для оператора має бути успішним");
    }

    @Test
    void testLoginService_unsuccessfulLogin() {
        String userInput = "operator1\nwrongpassword\n";
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        MobileNetworkService service = loginMenu.loginService(serviceAccounts);
        assertNull(service, "Вхід для оператора має бути неуспішним при неправильному паролі");
    }

    @Test
    void testLoginSubscriber_successfulLogin() {
        String userInput = "subscriber1\npassword1\n";
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        MobileSubscriber subscriber = loginMenu.loginSubscriber(subscriberAccounts);
        assertNotNull(subscriber, "Вхід для абонента має бути успішним");
    }

    @Test
    void testLoginSubscriber_unsuccessfulLogin() {
        String userInput = "subscriber1\nwrongpassword\n";
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        MobileSubscriber subscriber = loginMenu.loginSubscriber(subscriberAccounts);
        assertNull(subscriber, "Вхід для абонента має бути неуспішним при неправильному паролі");
    }

    @Test
    void testExecute_operatorLogin() {
        String userInput = "1\noperator1\npassword1\n0\n"; 
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        loginMenu.execute(); 
    }

    @Test
    void testExecute_subscriberLogin() {
        String userInput = "2\nsubscriber1\npassword1\n0\n"; 
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        loginMenu.execute(); 
    }

    @Test
    void testHandleService() {
        String userInput = "0\n"; 
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        MobileNetworkService service = services.get(Map.of("operator1", "password1"));
        assertNotNull(service, "Сервіс має існувати");
        loginMenu.handleService(service); 
    }

    @Test
    void testHandleSubscriber() {
        String userInput = "TestNetwork\n0\n"; 
        inputStream = new ByteArrayInputStream(userInput.getBytes());
        LoginMenu loginMenu = new LoginMenu(services, subscribers, inputStream);
        MobileSubscriber subscriber = subscribers.get(Map.of("subscriber1", "password1"));
        assertNotNull(subscriber, "Абонент має існувати");
        loginMenu.handleSubscriber(subscriber); 
    }
}