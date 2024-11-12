import java.util.Scanner;

public class Main {

    /**
     * Головний метод програми.
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        int n;
        int firstValue = 2;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість чисел Люка: ");
        n = scanner.nextInt();
        LukeNumber[] lukeNumber = new LukeNumber[n];

        // Створення n чисел Люка
        for (int i = 0; i<n; i++) {
            if (i == 0) {
                lukeNumber[i] = new LukeNumber(firstValue);
            }
            else if (i == 1) {
                lukeNumber[i] = new LukeNumber(lukeNumber[0].getValue()-1);
            }
            else {
                lukeNumber[i] = new LukeNumber(lukeNumber[i-1].getValue()+lukeNumber[i-2].getValue());
            }
        }

        // Вивід в консоль n чисел Люка
        System.out.println("Числа Люка:");
        for (int i = 0; i<n; i++) {
            lukeNumber[i].dislplayLuke();
        }

        // Перевірка та вивід чисел Люка, які можна записати у формі w^3+1
        System.out.println("\nЧисла люка які можна записати у формі w^3+1:");
        for (int i = 0; i<n; i++) {
            if (Math.pow(lukeNumber[i].getValue()-1, 1./3)%1 == 0) {
                lukeNumber[i].dislplayLuke();
            }
        }
    }
}