/**
 * Клас представляє число Люка.
 */
public class LukeNumber {
    private static int static_index = -1;
    private int index;
    private long value;

    /**
     * Конструктор для створення числа Люка.
     * @param value значення числа Люка
     */
    public LukeNumber(long value) {
        this.static_index++;
        this.index = static_index;
        this.value = value;
    }

    /**
     * Повертає індекс числа Люка.
     * @return індекс числа Люка
     */
    public int getIndex() {
        return index;
    }

    /**
     * Повертає значення числа Люка.
     * @return значення числа Люка
     */
    public long getValue() {
        return value;
    }

    /**
     * Виводить число Люка у форматі "Luke {index}: {value}".
     */
    public void dislplayLuke() {
        System.out.println("Luke " + index + ": " + value);
    }
}