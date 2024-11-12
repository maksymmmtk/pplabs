package com.pplab3.droid.droids;

/**
 * Абстрактний клас Droid представляє базову модель дроїда з основними характеристиками та методами.
 */
public abstract class Droid {
    protected String name;
    protected int health;
    protected int damage;
    protected int healAmount;

    /**
     * Конструктор класу Droid.
     *
     * @param name       Ім'я дроїда.
     * @param health     Здоров'я дроїда.
     * @param damage     Шкода, яку дроїд може завдати.
     * @param healAmount Кількість здоров'я, яку дроїд може зцілити.
     */
    public Droid(String name, int health, int damage, int healAmount) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.healAmount = healAmount;
    }

    /**
     * Повертає ім'я дроїда.
     *
     * @return Ім'я дроїда.
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає здоров'я дроїда.
     *
     * @return Здоров'я дроїда.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Повертає шкоду, яку дроїд може завдати.
     *
     * @return Шкода, яку дроїд може завдати.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Повертає кількість здоров'я, яку дроїд може зцілити.
     *
     * @return Кількість здоров'я, яку дроїд може зцілити.
     */
    public int getHealAmount() {
        return healAmount;
    }

    /**
     * Зцілює дроїда на вказану кількість здоров'я.
     *
     * @param heal Кількість здоров'я для зцілення.
     */
    protected void takeHeal(int heal) {
        this.health += heal;
    }

    /**
     * Завдає дроїду вказану кількість шкоди.
     *
     * @param damage Кількість шкоди.
     */
    protected void takeDamage(int damage) {
        this.health -= damage;
    }

    /**
     * Атакує цільового дроїда, завдаючи йому шкоди.
     *
     * @param target Цільовий дроїд.
     */
    public void attack(Droid target) {
        target.takeDamage(this.damage);
        System.out.println(this.name + " наніс " + target.getName() + " " + this.damage + " очків пошкодження.");
    }
}