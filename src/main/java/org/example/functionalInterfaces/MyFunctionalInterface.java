package org.example.functionalInterfaces;

@FunctionalInterface
public interface MyFunctionalInterface {

    // Abstract method in a functional interface
    void doSomething();

    // Static method in a functional interface
    public static void printMessage() {
        System.out.println("Hello from method reference!");
    }

    // Default method in a functional interface
    default void defaultMethod() {
        System.out.println("This is a default method in the functional interface.");
    }


}
