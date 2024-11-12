package com.pplab3.droid.droids;

/**
 * Клас BattleDroid представляє бойового дроїда, який спеціалізується на завданні шкоди.
 */
public class BattleDroid extends Droid {

    /**
     * Конструктор класу BattleDroid.
     *
     * @param name       Ім'я дроїда.
     * @param health     Здоров'я дроїда.
     * @param damage     Шкода, яку дроїд може завдати.
     * @param healAmount Кількість здоров'я, яку дроїд може зцілити (для сумісності з базовим класом).
     */
    public BattleDroid(String name, int health, int damage, int healAmount) {
        super(name, health, damage, healAmount);
    }

    public String toString() {
        return name + " - Здоров'я: " + health + ", Пошкодження по противнику: " + damage;
    }
}