package com.pplab3.droid.droids;

/**
 * Клас HealerDroid представляє дроїда-цілителя, який може зцілювати інших дроїдів.
 */
public class HealerDroid extends Droid {

    /**
     * Конструктор класу HealerDroid.
     *
     * @param name       Ім'я дроїда.
     * @param health     Здоров'я дроїда.
     * @param damage     Шкода, яку дроїд може завдати.
     * @param healAmount Кількість здоров'я, яку дроїд може зцілити.
     */
    public HealerDroid(String name, int health, int damage, int healAmount) {
        super(name, health, damage, healAmount);
    }

    /**
     * Метод для зцілення союзного дроїда.
     *
     * @param ally Союзний дроїд, якого потрібно зцілити.
     */
    public void heal(Droid ally) {
        ally.takeHeal(this.healAmount);
        System.out.println(this.name + " зцілив " + ally.getName() + " на " + this.healAmount + " очків здоров'я.");
    }

    public String toString() {
        return name + " - Здоров'я: " + health + ", Пошкодження по противнику: " + damage + ", Кількість очків здоров'я для зцілення: " + healAmount;
    }
}