package org.example;

import java.util.function.*;

import static java.lang.System.out;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        out.print("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            out.println("i = " + i);
        }

        // Functional Interface Example
        //Supplier
        Supplier<Double> randomSupplier = Math::random;
        System.out.println(randomSupplier.get()); // Prints a random number

        //Consumer
        Consumer<String> printer = System.out::println;
        printer.accept("Hello, Java!"); // Prints "Hello, Java!"

        //BiConsumer
        BiConsumer<String, Integer> printAge = (name, age) ->
                System.out.println(name + " is " + age + " years old");
        printAge.accept("Alice", 30); // Prints "Alice is 30 years old"

        // Function
        Function<String, Integer> lengthFunction = String::length;
        System.out.println(lengthFunction.apply("Java")); // Prints 4

        //BiFunction
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println(add.apply(5, 10)); // Prints 15

        // Predicate
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test("")); // Prints true
        System.out.println(isEmpty.test("Java")); // Prints false

        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println(isEven.test(4)); // Prints true
        System.out.println(isEven.test(5)); // Prints false

        //BiPredicate
        BiPredicate<String, String> startsWith = String::startsWith;
        System.out.println(startsWith.test("Java", "Ja")); // Prints true
        System.out.println(startsWith.test("Java", "A")); // Prints false

        BiPredicate<String, Integer> checkLength = (str, len) -> str.length() == len;
        System.out.println(checkLength.test("Java", 4)); // Prints true

        UnaryOperator<Integer> square = x -> x * x;
        System.out.println(square.apply(5)); // Prints 25
        System.out.println(square.apply(10)); // Prints 100
        // BinaryOperator
        BinaryOperator<Integer> addBinary = (a, b) -> a + b;
        System.out.println(addBinary.apply(5, 10)); // Prints 15
        System.out.println(addBinary.apply(20, 30)); // Prints 50


        /*
        Summary Table
        Interface	                    Arguments	        Returns	        Example Use Case
        ---------------------------------------------------------------
        Supplier<T>	            0	                        T	                    Generate values (e.g., random numbers)
        Consumer<T>	        1	                        void	                Print/log values
        BiConsumer<T, U>	2	                        void	                Print/log pairs of values
        Function<T, R>	        1	                        R	                    Convert one type to another
        BiFunction<T, U, R>	2	                        R	                    Combine two inputs into one output
        Predicate<T>	            1	                        boolean	        Check conditions (e.g., filtering)
        BiPredicate<T, U>	    2	                        boolean	        Compare two values
        UnaryOperator<T>	1	                        T	                    Modify values (e.g., square a number)
        BinaryOperator<T>	2	                        T	                    Combine two values of the same type
        ---------------------------------------------------------------

         */



    }
}