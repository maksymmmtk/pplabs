package com.pplab4_8.moblileCommunication;

import com.pplab4_8.moblileCommunication.menu.Menu;
import com.pplab4_8.moblileCommunication.users.MobileNetworkOperator;
import com.pplab4_8.moblileCommunication.command.*;
import com.pplab4_8.moblileCommunication.users.MobileSubscriber;
import java.util.Scanner;

/**
 * Клас Main є точкою входу в програму, де користувач вибирає свою роль
 * (мобільний оператор або абонент) та взаємодіє з відповідними командами.
 */
public class Main {

    /**
     * Метод для вибору ролі користувача (мобільний оператор або абонент)
     * та відображення відповідного меню для виконання операцій.
     */
    public static void chooseRole() {
        // Створюємо об'єкти для мобільного оператора та абонента
        MobileNetworkOperator operator = new MobileNetworkOperator("Kyivstar");
        MobileSubscriber subscriber = new MobileSubscriber("Maksym", 19);
        subscriber.setOperator(operator);

        // Створюємо об'єкт сканера для введення користувачем даних
        Scanner scanner = new Scanner(System.in);

        // Виводимо меню вибору ролі
        System.out.println("Виберіть свою роль:");
        System.out.println("1. Мобільний оператор");
        System.out.println("2. Абонент");
        System.out.print("Введіть номер опції: ");

        // Зчитуємо вибір користувача
        int role = scanner.nextInt();

        // Якщо вибрано мобільного оператора
        if (role == 1) {
            // Створюємо меню для мобільного оператора з відповідними командами
            Menu menu = new Menu(
                    new addTariffCommand(operator),     // Додавання тарифу
                    new editTariffCommand(operator),    // Редагування тарифу
                    new removeTariffCommand(operator),  // Видалення тарифу
                    new viewTariffsCommand(operator));  // Перегляд тарифів
            menu.showOperatorMenu();  // Відображаємо меню оператора

            // Якщо вибрано абонента
        } else if (role == 2) {
            // Створюємо меню для абонента з відповідними командами
            Menu menu = new Menu(
                    new viewPersonalTariffCommand(subscriber), // Перегляд особистого тарифу
                    new viewTariffsCommand(subscriber.getOperator()),          // Перегляд тарифів
                    new searchTariffCommand(subscriber.getOperator()));        // Пошук тарифу
            menu.showSubscriberMenu();  // Відображаємо меню абонента
        } else {
            // Якщо вибрана некоректна опція
            System.out.println("Невірна опція. Спробуйте ще раз.");
        }
    }

    /**
     * Метод main є точкою входу в програму.
     * Викликає метод chooseRole для початку взаємодії користувача.
     */
    public static void main(String[] args) {
        chooseRole();  // Викликаємо метод для вибору ролі
    }
}