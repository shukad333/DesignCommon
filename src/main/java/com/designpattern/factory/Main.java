package com.designpattern.factory;

// Step 1: Create the Product Interface
interface Animal {
    void speak();
}

// Step 2: Create Concrete Products
class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Meow!");
    }
}

// Step 3: Create Factory Class
class AnimalFactory {
    // Factory Method
    public static Animal getAnimal(String type) {
        if ("Dog".equalsIgnoreCase(type)) {
            return new Dog();
        } else if ("Cat".equalsIgnoreCase(type)) {
            return new Cat();
        }
        return null;
    }
}

// Step 4: Client code
public class Main {
    public static void main(String[] args) {
        // Create a Dog
        Animal dog = AnimalFactory.getAnimal("Dog");
        dog.speak();  // Output: Woof!

        // Create a Cat
        Animal cat = AnimalFactory.getAnimal("Cat");
        cat.speak();  // Output: Meow!
    }
}
