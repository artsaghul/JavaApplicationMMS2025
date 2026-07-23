class Vehicle {
    public void move() {
        System.out.println("Vehicle is moving");
    }
}

class Car extends Vehicle {
    @Override
    public void move() {
        System.out.println("Car is driving on the road");
    }
}

class Bicycle extends Vehicle {
    @Override
    public void move() {
        System.out.println("Bicycle is pedaling");
    }
}

class Airplane extends Vehicle {
    @Override
    public void move() {
        System.out.println("Airplane is flying in the sky");
    }
}