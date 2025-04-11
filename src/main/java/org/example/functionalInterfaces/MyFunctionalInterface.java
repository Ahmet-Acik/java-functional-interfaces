package org.example.functionalInterfaces;

/**
 * A custom functional interface with a single abstract method.
 * This interface demonstrates the use of functional interfaces in Java,
 * along with static and default methods.
 */
@FunctionalInterface
public interface MyFunctionalInterface {

    /**
     * Abstract method to be implemented by the functional interface.
     * This method represents the core functionality of the interface.
     */
    void execute();

    /**
     * A static method in the functional interface.
     * This method can be called directly using the interface name.
     */
    static void printMessage() {
        System.out.println("Hello from method reference!");
    }

    /**
     * A default method in the functional interface.
     * This method provides a default implementation that can be overridden by implementing classes.
     */
    default void defaultMethod() {
        System.out.println("This is a default method in the functional interface.");
    }
}