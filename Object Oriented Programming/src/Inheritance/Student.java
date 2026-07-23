class Student extends Person {
    private String course;
    private String level;

    public Student(String name, int age, String course, String level) {
        super(name, age);
        this.course = course;
        this.level = level;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Course: " + course + ", Level: " + level);
    }
}