package com.pplab3.droid.menu;

import com.pplab3.droid.droids.*;
import com.pplab3.droid.battle.Battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Клас Menu відповідає за взаємодію з користувачем та управління дроїдами і боями.
 */
public class Menu {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Відображає головне меню та обробляє вибір користувача.
     */
    public void showMenu() {
        while (true) {
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Запустити бій");
            System.out.println("4. Вийти з програми");
            System.out.print("\nВаш вибір: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startBattle();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    /**
     * Створює нового дроїда на основі вибору користувача.
     */
    private void createDroid() {
        System.out.println("Виберіть тип дроїда: 1. Бойовий дроїд 2. Дроїд-цілитель");
        int type = scanner.nextInt();
        System.out.println("Введіть ім'я дроїда:");
        String name = scanner.next();
        System.out.println("Введіть здоров'я дроїда:");
        int health = scanner.nextInt();
        System.out.println("Введіть шкоду дроїда:");
        int damage = scanner.nextInt();
        if (type == 1) {
            droids.add(new BattleDroid(name, health, damage, 0));
        } else if (type == 2) {
            System.out.println("Введіть кількість здоров'я для зцілення:");
            int healAmount = scanner.nextInt();
            droids.add(new HealerDroid(name, health, damage, healAmount));
        } else {
            System.out.println("Невірний вибір.");
        }
    }

    /**
     * Відображає список створених дроїдів.
     */
    private void showDroids() {
        System.out.println("\nСписок дроїдів: ");
        for (Droid droid : droids) {
            if (droid instanceof BattleDroid) {
                System.out.println(droid.getName() + " - Здоров'я: " + droid.getHealth() + ", Пошкодження по противнику: " + droid.getDamage());
            } else if (droid instanceof HealerDroid) {
                System.out.println(droid.getName() + " - Здоров'я: " + droid.getHealth() + ", Пошкодження по противнику: " + droid.getDamage() + ", Кількість очків здоров'я для зцілення: " + droid.getHealAmount());
            }
        }
    }

    /**
     * Запускає бій між двома командами дроїдів.
     */
    private void startBattle() {
        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        team1.add(new BattleDroid("Max", 200, 45, 0));
        team1.add(new BattleDroid("Chloe", 167, 57, 0));
        team1.add(new HealerDroid("Denis", 100, 15, 20));
        team2.add(new BattleDroid("Mike", 150, 70, 0));
        team2.add(new BattleDroid("Jenny", 190, 65, 0));
        team2.add(new HealerDroid("Cristian", 90, 10, 25));

        // Код для додавання дроїдів у команди з введенням користувача
//        System.out.println("Введіть кількість дроїдів 1 команди:");
//        int n1 = scanner.nextInt();
//        System.out.println("Введіть кількість дроїдів 2 команди:");
//        int n2 = scanner.nextInt();
//
//        System.out.println("Перша команда:");
//        for (int i = 0; i < n1; i++) {
//            System.out.println("Введіть ім'я "+ (i+1) +" дроїда:");
//            String name1 = scanner.next();
//
//            for (Droid droid : droids) {
//                if (droid.getName().equals(name1)) {
//                    team1.add(droid);
//                }
//            }
//        }
//
//        System.out.println("Друга команда:");
//        for (int i = 0; i < n2; i++) {
//            System.out.println("Введіть ім'я "+ (i+1) +" дроїда:");
//            String name2 = scanner.next();
//
//            for (Droid droid : droids) {
//                if (droid.getName().equals(name2)) {
//                    team2.add(droid);
//                }
//            }
//        }

        // Запуск бою між командами
        Battle b1 = new Battle(team1, team2);
        b1.start();
    }
}