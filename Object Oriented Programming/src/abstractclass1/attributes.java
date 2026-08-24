 // 2. Constructor to initialize attributes
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Concrete method
    public void displayDetails() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    // Abstract method
    public abstract void performDuty();
}
