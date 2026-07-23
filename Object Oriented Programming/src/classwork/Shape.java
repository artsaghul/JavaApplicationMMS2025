class Shape {
    public void draw() {
        System.out.println("Drawing a generic shape");
    }
}
// Main class to run the code
public class Main {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(), new Rectangle(), new Triangle() };
        
        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}
