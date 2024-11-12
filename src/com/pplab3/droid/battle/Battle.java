package com.pplab3.droid.battle;

import com.pplab3.droid.droids.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас Battle відповідає за проведення бою між двома командами дроїдів.
 */
public class Battle {
    private List<Droid> team1 = new ArrayList<>();
    private List<Droid> team2 = new ArrayList<>();

    /**
     * Конструктор класу Battle.
     *
     * @param team1 Перша команда дроїдів.
     * @param team2 Друга команда дроїдів.
     */
    public Battle(List<Droid> team1, List<Droid> team2) {
        this.team1.addAll(team1);
        this.team2.addAll(team2);
    }

    /**
     * Метод для початку бою між двома командами.
     * Виводить статистику команд та бойовий журнал.
     * Бій триває, поки одна з команд не буде повністю знищена.
     */
    public void start() {
        while (!team1.isEmpty() && !team2.isEmpty()) {
            System.out.println("\nСтатистика 1 команди:");
            for (Droid droid : team1) {
                if (droid instanceof BattleDroid) {
                    System.out.println(droid.getName() + " - Здоров'я: " + droid.getHealth() + ", Пошкодження по противнику: " + droid.getDamage());
                } else if (droid instanceof HealerDroid) {
                    System.out.println(droid.getName() + " - Здоров'я: " + droid.getHealth() + ", Пошкодження по противнику: " + droid.getDamage() + ", Кількість очків здоров'я для зцілення: " + droid.getHealAmount());
                }
            }

            System.out.println("Статистика 2 команди:");
            for (Droid droid : team2) {
                System.out.println(droid.toString());
            }

            System.out.println("\nБойовий журнал:");

            for (Droid droid : team1) {
                int i1 = (int) (Math.random() * team1.size());
                int i2 = (int) (Math.random() * team2.size());
                if (i1 < team1.size() && droid instanceof HealerDroid) {
                    ((HealerDroid) droid).heal(team1.get(i1));
                }
                if (i2 < team2.size()) {
                    droid.attack(team2.get(i2));
                    if (team2.get(i2).getHealth() <= 0) {
                        team2.remove(i2);
                    }
                }
            }

            for (Droid droid : team2) {
                int i2 = (int) (Math.random() * team1.size());
                int i1 = (int) (Math.random() * team1.size());
                if (i2 < team2.size() && droid instanceof HealerDroid) {
                    ((HealerDroid) droid).heal(team2.get(i2));
                }
                if (i1 < team1.size()) {
                    droid.attack(team1.get(i1));
                    if (team1.get(i1).getHealth() <= 0) {
                        team1.remove(i1);
                    }
                }
            }
        }
        if (!team1.isEmpty()) {
            System.out.println("\nКоманда 1 перемогла!\n");
        } else {
            System.out.println("\nКоманда 2 перемогла!\n");
        }
    }
}