package com.pplab3.droid;

import com.pplab3.droid.menu.Menu;

/**
 * Головний клас програми, який відповідає за запуск та ініціалізацію меню.
 */
public class Main {
    /**
     * Створює екземпляр меню та викликає метод для його відображення.
     *
     * @param args аргументи командного рядка (не використовуються в цій реалізації)
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.showMenu();
    }
}