package org.example.functionalInterfaces;

@FunctionalInterface
public interface MyFunctionalInterface {
    void doSomething();

    public static void printMessage() {
        System.out.println("Hello from method reference!");
    }
    default void defaultMethod() {
        System.out.println("This is a default method in the functional interface.");
    }


}
