/**
 * Клас представляє студента з основними атрибутами.
 */
public class Student {

    // Оголошення змінних в приватних полях
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private String faculty;
    private String course;
    private String group;

    /**
     * Конструктор за замовчуванням, який ініціалізує всі поля значеннями за замовчуванням.
     */
    public Student() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.patronymic = "";
        this.dateOfBirth = "";
        this.address = "";
        this.phoneNumber = "";
        this.faculty = "";
        this.course = "";
        this.group = "";
    }

    /**
     * Конструктор з параметрами для ініціалізації всіх полів.
     *
     * @param id Ідентифікатор студента
     * @param firstName Ім'я студента
     * @param lastName Прізвище студента
     * @param patronymic По батькові студента
     * @param dateOfBirth Дата народження студента
     * @param address Адреса студента
     * @param phoneNumber Номер телефону студента
     * @param faculty Факультет студента
     * @param course Курс студента
     * @param group Група студента
     */
    public Student(int id, String firstName, String lastName, String patronymic, String dateOfBirth, String address, String phoneNumber, String faculty, String course, String group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    /**
     * Встановлює ідентифікатор студента.
     *
     * @param id Новий ідентифікатор студента
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Встановлює ім'я студента.
     *
     * @param firstName Нове ім'я студента
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Встановлює прізвище студента.
     *
     * @param lastName Нове прізвище студента
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Встановлює по батькові студента.
     *
     * @param patronymic Нове по батькові студента
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Встановлює дату народження студента.
     *
     * @param dateOfBirth Нова дата народження студента
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Встановлює адресу студента.
     *
     * @param address Нова адреса студента
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Встановлює номер телефону студента.
     *
     * @param phoneNumber Новий номер телефону студента
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Встановлює факультет студента.
     *
     * @param faculty Новий факультет студента
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * Встановлює курс студента.
     *
     * @param course Новий курс студента
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Встановлює групу студента.
     *
     * @param group Нова група студента
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Повертає ідентифікатор студента.
     *
     * @return Ідентифікатор студента
     */
    public int getId() {
        return id;
    }

    /**
     * Повертає ім'я студента.
     *
     * @return Ім'я студента
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Повертає прізвище студента.
     *
     * @return Прізвище студента
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Повертає по батькові студента.
     *
     * @return По батькові студента
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Повертає дату народження студента.
     *
     * @return Дата народження студента
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Повертає адресу студента.
     *
     * @return Адреса студента
     */
    public String getAddress() {
        return address;
    }

    /**
     * Повертає номер телефону студента.
     *
     * @return Номер телефону студента
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Повертає факультет студента.
     *
     * @return Факультет студента
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Повертає курс студента.
     *
     * @return Курс студента
     */
    public String getCourse() {
        return course;
    }

    /**
     * Повертає групу студента.
     *
     * @return Група студента
     */
    public String getGroup() {
        return group;
    }

    /**
     * Повертає рядкове представлення об'єкта Student.
     *
     * @return Рядкове представлення об'єкта Student
     */
    public String toString() {
        return "Student{id='" + id + "', firstName='" + firstName + "', lastName='" + lastName + "', patronymic='" + patronymic + "', dateOfBirth='" + dateOfBirth + "', address='" + address + "', phoneNumber='" + phoneNumber + "', faculty='" + faculty + "', course='" + course + "', group='" + group + "'}";
    }
}