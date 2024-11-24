import java.util.Scanner;

/**
 * Головний клас програми.
 */
public class Main {

    /**
     * Метод для створення масиву студентів з попередньо визначеними даними.
     *
     * @return масив студентів.
     */
    public static Student[] inputStudent() {
        Student[] student = new Student[5];
        student[0] = new Student(1, "Анатолій", "Попенко", "Дмитрович", "05.12.2002", "м. Львів, вул. Лукаша, 3", "+380964278495", "ІБІС", "1", "БВ-11");
        student[1] = new Student(2, "Поліна", "Васильчук", "Євгеніївна", "03.06.2000", "м. Червноград, вул. Степана Бандери, 5", "+380674283485", "ІКНІ", "3", "СШІ-32");
        student[2] = new Student(3, "В'ячеслав", "Пономаренко", "Йосипович", "28.02.2005", "м. Стрий, вул. Богдана Хмельницького, 1", "+380652953602", "ІКНІ", "1", "ОІ-11СП");
        student[3] = new Student(4, "Ярослава", "Броваренко", "Андріївна", "15.04.2003", "м. Броди, вул. Івана Мазепи, 21", "+380974826537", "ІМІТ", "1", "АТ-11'");
        student[4] = new Student(5, "Лариса", "Василенко", "Василівна", "21.09.2004", "м. Червоноград, вул. Сокальська, 6", "+380937560383", "ІКНІ", "2", "ОІ-21");
        return student;
    }

    /**
     * Метод для створення масиву студентів з введенням даних користувачем.
     *
     * @param n кількість студентів.
     * @return масив студентів.
     */
    public static Student[] inputStudent(int n) {
        String firstName;
        String lastName;
        String patronymic;
        String dateOfBirth;
        String address;
        String phoneNumber;
        String faculty;
        String course;
        String group;
        Student[] student = new Student[n];
        for (int i = 0; i < n; i++) {
            Scanner scanner = new Scanner(System.in);
            student[i] = new Student();
            student[i].setId(i + 1);
            System.out.print("\nСтудент №" + (i + 1) + ":\nІм'я: ");
            firstName = scanner.nextLine();
            student[i].setFirstName(firstName);
            System.out.print("Прізвище: ");
            lastName = scanner.nextLine();
            student[i].setLastName(lastName);
            System.out.print("По батькові: ");
            patronymic = scanner.nextLine();
            student[i].setPatronymic(patronymic);
            System.out.print("Дата народження: ");
            dateOfBirth = scanner.nextLine();
            student[i].setDateOfBirth(dateOfBirth);
            System.out.print("Адреса: ");
            address = scanner.nextLine();
            student[i].setAddress(address);
            System.out.print("Номер телефону: ");
            phoneNumber = scanner.nextLine();
            student[i].setPhoneNumber(phoneNumber);
            System.out.print("Факультет: ");
            faculty = scanner.nextLine();
            student[i].setFaculty(faculty);
            System.out.print("Курс: ");
            course = scanner.nextLine();
            student[i].setCourse(course);
            System.out.print("Група: ");
            group = scanner.nextLine();
            student[i].setGroup(group);
        }
        return student;
    }

    /**
     * Метод для виведення студентів за факультетом.
     *
     * @param students масив студентів.
     * @param faculty  факультет для фільтрації.
     */
    public static void printStudentsByFaculty(Student[] students, String faculty) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].getFaculty().equals(faculty)) {
                System.out.println(students[i].toString());
            }
        }
    }

    /**
     * Метод для виведення студентів, які народилися після певного року.
     *
     * @param students масив студентів.
     * @param year     рік для фільтрації.
     */
    public static void printStudentsBornAfterYear(Student[] students, int year) {
        for (int i = 0; i < students.length; i++) {
            if (Integer.parseInt(students[i].getDateOfBirth().substring(6)) > year) {
                System.out.println(students[i].toString());
            }
        }
    }

    /**
     * Метод для виведення студентів за групою.
     *
     * @param students масив студентів.
     * @param group    група для фільтрації.
     */
    public static void printStudentsByGroup(Student[] students, String group) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].getGroup().equals(group)) {
                System.out.println(students[i].toString());
            }
        }
    }

    /**
     * Головний метод класу.
     *
     * @param args аогументи командного рядка.
     */
    public static void main(String[] args) {
        int n, year;
        String faculty, group;
        Scanner scanner = new Scanner(System.in);

       // Введення інформації про студентів з клавіатури
       // System.out.print("Введіть кількість об'єктів класу 'Студент': ");
       // n = scanner.nextInt();
       // scanner.nextLine();
       // Student[] student = inputStudent(n);

        // Створення масиву студентів та введення їх даних
        System.out.println("\nСписок студентів: ");
        Student[] student = inputStudent();
        for (int i = 0; i < student.length; i++) {
            System.out.println(student[i].toString());
        }

        // Введення факультету та виведення студентів цього факультету
        System.out.print("\nВведіть факультет: ");
        faculty = scanner.nextLine();
        printStudentsByFaculty(student, faculty);

        // Введення року народження та виведення студентів, які народилися після цього року
        System.out.print("\nВведіть рік народження: ");
        year = scanner.nextInt();
        scanner.nextLine();
        printStudentsBornAfterYear(student, year);

        // Введення групи та виведення студентів цієї групи
        System.out.print("\nВведіть групу: ");
        group = scanner.nextLine();
        printStudentsByGroup(student, group);
    }
}