public class Student extends Person {

    private String id;
    private String course;
    private int year;
    private String phone;

    public Student(String id, String name, String course,
            int year, String phone) {

        super(name);

        this.id = id;
        this.course = course;
        this.year = year;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getCourse() {
        return course;
    }

    public int getYear() {
        return year;
    }

    public String getPhone() {
        return phone;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void displayDetails() {

        System.out.println("Student ID: " + id);
        System.out.println("Name: " + getName());
        System.out.println("Course: " + course);
        System.out.println("Year: " + year);
        System.out.println("Phone: " + phone);
    }
}